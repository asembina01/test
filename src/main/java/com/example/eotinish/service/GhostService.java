package com.example.eotinish.service;

import com.example.eotinish.entity.Ghost;
import com.example.eotinish.repo.GhostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GhostService {
    private final GhostRepo ghostRepo;

    public boolean exists(Long chatId){
        return ghostRepo.existsById(chatId);
    }

    public void save(Ghost ghost){
        ghostRepo.save(ghost);
    }
}
