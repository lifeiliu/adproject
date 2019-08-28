package com.lilypad.ad.dao;

import com.lilypad.ad.entities.AdUnit;
import com.lilypad.ad.entities.Creative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreativeRepository extends JpaRepository<Creative,Long> {

}
