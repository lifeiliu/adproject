package com.lilypad.ad.controllers;

import com.alibaba.fastjson.JSON;
import com.lilypad.ad.client.AdCampaign;
import com.lilypad.ad.client.AdCampaignGetRequest;
import com.lilypad.ad.client.SponsorClient;
import com.lilypad.ad.vo.CommonResponse;
import com.lilypad.ad.annotation.IngorResponseAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class SearchController {
    @Autowired
    private RestTemplate restTemplate;

    @SuppressWarnings("all")
    @IngorResponseAdvice
    @PostMapping("/getAdCampaigsByRibbon")
    public CommonResponse<List<AdCampaign>> getAdCampaignsByRibbon(
            @RequestBody AdCampaignGetRequest request
            ){
        log.info("ad-search: getAdCampaignsByRibbon -> {}",
                JSON.toJSONString(request));
        return restTemplate.postForEntity(
                "http://eureka-client-ad-sponsor/ad-sponsor/adCampaigns",
                request,
                CommonResponse.class
        ).getBody();
    }

    @IngorResponseAdvice
    @PostMapping("/getAdCampaigns")
    public CommonResponse<List<AdCampaign>> getAdCampaigns(
            @RequestBody AdCampaignGetRequest request
    ){
        log.info("ad-search: getAdCampaigns -> {}",
                JSON.toJSONString(request));
        return restTemplate.postForEntity(
                "http://eureka-client-ad-sponsor/ad-sponsor/adCampaigns",
                request,
                CommonResponse.class
        ).getBody();
    }
}
