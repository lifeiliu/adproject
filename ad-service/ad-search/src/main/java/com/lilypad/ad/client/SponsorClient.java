package com.lilypad.ad.client;

import com.lilypad.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "euraka-client-ad-sponsor",
        fallback = SponsorClientHystrix.class)
public interface SponsorClient {
    @RequestMapping(value = "ad-sponsor/adCampaigns/get",
            method = RequestMethod.POST)
    CommonResponse<List<AdCampaign>> getAdCampaigns(AdCampaignGetRequest request);
}
