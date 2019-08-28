package com.lilypad.ad.dao;

import com.lilypad.ad.entities.AdCampaign;
import com.lilypad.ad.entities.AdUnit;
import com.lilypad.ad.vo.AdUnitResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdUnitRepository extends JpaRepository<AdUnit,Long> {
    AdUnit findByCampaignIdAndUnitName(Long planId, String unitName);
    List<AdUnit> findAllByUnitStatus(Integer status);
    List<AdUnit> findAllByCampaignId(Long campaignId);

}
