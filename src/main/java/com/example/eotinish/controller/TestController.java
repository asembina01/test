package com.example.eotinish.controller;

import com.example.eotinish.EotinishGate;
import com.example.eotinish.StoreService;
import com.example.eotinish.entity.Appeal;
import com.example.eotinish.dto.req.AppealResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final EotinishGate gate;

    private final StoreService storeService;



    @GetMapping("/new")
    public AppealResponse test2(){
        return storeService.getNewAppealResponse();
    }

    @GetMapping("/inProgress")
    public AppealResponse test3(){
        return storeService.getInProgressAppealResponse();
    }

    /*@GetMapping("/store")
    public List<Appeal> appealList(){
        return storeService.getAppealsCache();
    }*/

}
