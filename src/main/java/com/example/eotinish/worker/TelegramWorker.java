package com.example.eotinish.worker;

import com.example.eotinish.StoreService;
import com.example.eotinish.entity.AppUser;
import com.example.eotinish.service.EotinishMapper;
import com.example.eotinish.service.ExecutorService;
import com.example.eotinish.telegram.MyTelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Slf4j
@Service
public class TelegramWorker {
    @Autowired
    StoreService storeService;

    @Autowired
    EotinishMapper mapper;
    @Autowired
    ExecutorService executorService;
    @Autowired
    MyTelegramBot bot;


    @Scheduled(cron = "0 4 9,15 * * 0-5")
    public void sendInProgress(){
        executorService.findAll().stream().forEach(appUser -> {
            long count = storeService.getAllAppeals().stream().filter(appeal -> appeal.getMainExecutor().toUpperCase().equals(appUser.getFio())).count();
            if(count>0){
                String chatId = "";
                chatId = appUser.getChatId().toString();
                StringBuilder sb = new StringBuilder();
                sb.append(appUser.getFirstname());
                sb.append(" ");
                sb.append(appUser.getLastname());
                sb.append(", у вас на исполнении ");
                sb.append(count);
                sb.append(" обращений(я)");
                String text = sb.toString();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(text);

                try {
                    bot.execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        // сами обращения
        storeService.getAllAppeals().stream().filter(appeal -> appeal.getCurrentState().equals("IN_PROGRESS")).forEach(appeal -> {
            Optional<AppUser> appUser = executorService.findByFIO(appeal.getMainExecutor().toUpperCase());
            String chatId = "";
            if(appUser.isPresent()){
                chatId = appUser.get().getChatId().toString();
            } else {
                chatId = "5244146363";
            }
            String text = mapper.toTgMessage(appeal);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(text);

            try {
                bot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Scheduled(cron = "0 3 9,12,15,18,19 * * 0-5")
    public void sendNew(){
        // сами обращения
        storeService.getAllAppeals().stream().filter(appeal -> appeal.getCurrentState().equals("NEW")).forEach(appeal -> {
            String chatId = "5244146363";
            StringBuilder sb = new StringBuilder();
            sb.append("Новое обращение в");
            sb.append(appeal.getOrganizationNameRu());
            sb.append("\\n");
            sb.append(mapper.toTgMessage(appeal));
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(sb.toString());
            try {
                bot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
