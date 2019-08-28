package com.lilypad.ad.dao;

import com.lilypad.ad.entities.AdCampaign;
import com.lilypad.ad.entities.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdCampaignRepository extends JpaRepository<AdCampaign,Long> {
    AdCampaign findByUserIdAndId(Long id, Long userId);
    List<AdCampaign> findByIdInAndUserId(List<Long> ids, Long userId);
    AdCampaign findByUserIdAndAdCampaignName(Long userId, String campaignName);
    List<AdCampaign> findAllByCampaignStatus(Integer campaignStatus);
}
