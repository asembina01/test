package com.example.eotinish.worker;

import com.example.eotinish.EotinishGate;
import com.example.eotinish.StoreService;
import com.example.eotinish.entity.Appeal;
import com.example.eotinish.dto.req.AppealResponse;
import com.example.eotinish.service.EotinishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateWorker {
    private final StoreService storeService;
    private final EotinishGate eotinishGate;
    private final EotinishMapper mapper;


    @Scheduled(cron = "0 1 9,12,15,18,19 * * 0-5")
    public void updateNew() throws InterruptedException {
        eotinishGate.setNewAppeals();
        Thread.sleep(7000);
        if(storeService.getNewAppealResponse()!=null){
            List<Appeal> newAppeals = mapper.toEntityList(storeService.getNewAppealResponse());
            if(!newAppeals.isEmpty()){
                newAppeals.stream().forEach(appeal -> storeService.addAppeal(appeal));
            }
        }
        }

    @Scheduled(cron = "0 2 9,12,15,18 * * 0-5")
    public void updateInProgress() throws InterruptedException {
        eotinishGate.setAppealsInProgress();
        Thread.sleep(7000);
        if(storeService.getInProgressAppealResponse()!=null){
            List<Appeal> inProgressAppeals = mapper.toEntityList(storeService.getInProgressAppealResponse());
            if(!inProgressAppeals.isEmpty()){
                inProgressAppeals.stream().forEach(appeal -> storeService.addAppeal(appeal));
            }
        }
    }

}
