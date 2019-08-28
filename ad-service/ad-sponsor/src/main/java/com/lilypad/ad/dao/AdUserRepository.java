package com.lilypad.ad.dao;

import com.lilypad.ad.entities.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserRepository extends JpaRepository<AdUser,Long> {
    AdUser findByUsername(String username);
}
