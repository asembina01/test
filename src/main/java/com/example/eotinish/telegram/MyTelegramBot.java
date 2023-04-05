package com.example.eotinish.telegram;

import com.example.eotinish.StoreService;
import com.example.eotinish.entity.Ghost;
import com.example.eotinish.service.GhostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
@Component
@Slf4j
@RequiredArgsConstructor
public class MyTelegramBot extends TelegramLongPollingBot {
    private final BotConfig botConfig;

    private final StoreService storeService;

    private final GhostService ghostService;


    @Override
    public String getBotUsername() {
        return botConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(update.getMessage()!=null){
            User user = update.getMessage().getFrom();
            if(!ghostService.exists(user.getId())){
                Ghost ghost = new Ghost();
                ghost.setChatId(user.getId());
                ghost.setLastName(user.getLastName());
                ghost.setFirstName(user.getFirstName());
                ghost.setUserName(user.getUserName());
                ghostService.save(ghost);
                log.info("ChatId was added, message: "+update.getMessage());
            }
            log.info("Not guest, message: "+update.getMessage());
        } else {
            log.debug("Not message: "+update);
        }

        System.out.println(update);
        System.out.println(update.getMessage());
        System.out.println("!");
        /*if(!storeService.getChatIds().containsKey())
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText(update.getMessage().getText());
        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }*/
    }
}
