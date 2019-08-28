package com.lilypad.ad.service;

import com.lilypad.ad.entities.AdCampaign;
import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.vo.AdCampaignGetRequest;
import com.lilypad.ad.vo.AdCampaignRequest;
import com.lilypad.ad.vo.AdCampaignResponse;

import java.util.List;

public interface IAdCampaignService {
    AdCampaignResponse createAdCampain(AdCampaignRequest request) throws AdException;
    List<AdCampaign> getAdCampaigns(AdCampaignGetRequest request) throws AdException;
    AdCampaignResponse updateAdCampain(AdCampaignRequest request) throws AdException;
    void deleteAdCampain(AdCampaignRequest request) throws AdException;

}
