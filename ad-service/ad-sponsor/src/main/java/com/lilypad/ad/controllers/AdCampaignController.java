package com.lilypad.ad.controllers;

import com.alibaba.fastjson.JSON;
import com.lilypad.ad.entities.AdCampaign;
import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.service.IAdCampaignService;
import com.lilypad.ad.vo.AdCampaignGetRequest;
import com.lilypad.ad.vo.AdCampaignRequest;
import com.lilypad.ad.vo.AdCampaignResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/adCampaigns")
public class AdCampaignController {
    @Autowired
    IAdCampaignService campaignService;

    @PostMapping("")
    public AdCampaignResponse createAdCampaign(
            @RequestBody AdCampaignRequest request) throws AdException {
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        return campaignService.createAdCampain(request);
    }
    @PostMapping("/get")
    public List<AdCampaign> getAdPlanByIds(
            @RequestBody AdCampaignGetRequest request) throws AdException{
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        return campaignService.getAdCampaigns(request);
    }

    @PutMapping("")
    public AdCampaignResponse updateAdCampaign(
            @RequestBody AdCampaignRequest request) throws AdException{
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        return campaignService.updateAdCampain(request);
    }

    @DeleteMapping("")
    public void deleteAdCampaign(
            @RequestBody AdCampaignRequest request) throws AdException {
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        campaignService.deleteAdCampain(request);
    }
}
