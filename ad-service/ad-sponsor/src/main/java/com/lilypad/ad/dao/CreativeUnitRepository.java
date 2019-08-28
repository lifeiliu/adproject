package com.lilypad.ad.dao;

import com.lilypad.ad.entities.CreativeUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreativeUnitRepository extends JpaRepository<CreativeUnit, Long> {
    CreativeUnit findByCreativeIdAndUnitId(Long creativeId, Long unitId);
}
