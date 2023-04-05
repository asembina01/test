package com.example.eotinish.repo;

import com.example.eotinish.entity.Ghost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GhostRepo extends JpaRepository<Ghost, Long> {
}
