package com.example.eotinish;


import com.example.eotinish.entity.Appeal;
import com.example.eotinish.dto.req.AppealResponse;
import com.example.eotinish.repo.AppUserRepo;
import com.example.eotinish.repo.AppealRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreService {
    @Getter
    private final String URL_LOGIN = "https://eotinish.gov.kz/api/public/v1/login";

    @Getter
    private final String URL_NEW = "https://eotinish.gov.kz/api/private/v1/pa/appeals/search?page=0&size=20&sort=deadline,ASC&sort=id,asc&language=RUSSIAN&registryMode=ALL&startDate=2022-12-31T18:00:00.000Z&endDate=2023-12-31T17:59:59.999Z&search=currentWorkingState:NEW";

    @Getter
    private final String URL_IN_PROGRESS = "https://eotinish.gov.kz/api/private/v1/pa/appeals/search?page=0&size=20&sort=deadline,ASC&sort=id,asc&language=RUSSIAN&registryMode=ALL&startDate=2022-12-31T18:00:00.000Z&endDate=2023-12-31T17:59:59.999Z&search=currentWorkingState:IN_PROGRESS";
    @Getter
    private String username = "010503650468";
    @Getter
    @Setter
    private String password = "Madlen2001!";
    @Getter
    @Setter
    private String accessToken;

    @Getter @Setter
    private AppealResponse newAppealResponse;
    @Getter @Setter
    private AppealResponse inProgressAppealResponse;

    public StoreService(AppealRepo appealRepo, AppUserRepo userRepo) {
        this.appealRepo = appealRepo;
        this.userRepo = userRepo;
    }

    private final AppealRepo appealRepo;
    private final AppUserRepo userRepo;

    public void addAppeal(Appeal appeal){
        appealRepo.save(appeal);
    }

    public void clearAppeals(){
        appealRepo.deleteAll();
    }
    public List<Appeal> getAllAppeals(){
        return appealRepo.findAll();
    }

}
