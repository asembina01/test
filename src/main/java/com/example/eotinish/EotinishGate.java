package com.example.eotinish;

import com.example.eotinish.dto.LoginReq;
import com.example.eotinish.dto.LoginResp;
import com.example.eotinish.dto.req.AppealResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class EotinishGate {
    private final StoreService storeService;
    private final RestTemplate restTemplate;

    public EotinishGate(StoreService storeService, RestTemplate restTemplate) {
        this.storeService = storeService;
        this.restTemplate = restTemplate;
        makeAccessToken();
    }

    @Scheduled(cron = "0 0 9,12,15,18,19 * * 0-5")
    public void makeAccessToken(){
        LoginReq loginReq = new LoginReq(storeService.getUsername(), storeService.getPassword());
        LoginResp loginResp = restTemplate.postForObject(storeService.getURL_LOGIN(), loginReq, LoginResp.class);
        storeService.setAccessToken(loginResp.getAccessToken());
    }


    public ResponseEntity<AppealResponse> getNewAppeals(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(storeService.getAccessToken());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<AppealResponse> response = restTemplate.exchange(storeService.getURL_NEW(), HttpMethod.GET, httpEntity, AppealResponse.class);
        return response;
    }


    public ResponseEntity<AppealResponse> getAppealsInProgress(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(storeService.getAccessToken());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<AppealResponse> response = restTemplate.exchange(storeService.getURL_IN_PROGRESS(), HttpMethod.GET, httpEntity, AppealResponse.class);
        return response;
    }


    public void setAppealsInProgress(){
        ResponseEntity<AppealResponse> response = getAppealsInProgress();
        if(response!=null && response.getBody()!=null){
            storeService.setInProgressAppealResponse(response.getBody());
        }
    }

    public void setNewAppeals(){
        ResponseEntity<AppealResponse> response = getNewAppeals();
        if(response!=null && response.getBody()!=null){
            storeService.setNewAppealResponse(response.getBody());
        }
    }
}
