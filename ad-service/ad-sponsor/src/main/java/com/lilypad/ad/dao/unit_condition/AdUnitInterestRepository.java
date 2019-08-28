package com.lilypad.ad.dao.unit_condition;

import com.lilypad.ad.entities.unit_condition.AdUnitInterest;
import com.lilypad.ad.entities.unit_condition.AdUnitKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUnitInterestRepository extends JpaRepository<AdUnitInterest,Long> {
   
}
