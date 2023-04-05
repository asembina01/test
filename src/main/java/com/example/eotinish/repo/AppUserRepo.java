package com.example.eotinish.repo;

import com.example.eotinish.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {


    Optional<AppUser> findByFio(String fio);
}
