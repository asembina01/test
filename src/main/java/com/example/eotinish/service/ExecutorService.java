package com.example.eotinish.service;

import com.example.eotinish.entity.AppUser;
import com.example.eotinish.repo.AppUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExecutorService {

    private final AppUserRepo appUserRepo;

    public Optional<AppUser> findByFIO(String fio){
        return appUserRepo.findByFio(fio);
    }

    public List<AppUser> findAll(){
        return appUserRepo.findAll();
    }
}
