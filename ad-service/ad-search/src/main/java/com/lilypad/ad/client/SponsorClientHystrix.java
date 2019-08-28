package com.lilypad.ad.client;

import com.lilypad.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SponsorClientHystrix implements SponsorClient{

    @Override
    public CommonResponse<List<AdCampaign>> getAdCampaigns(
            AdCampaignGetRequest request) {
        return new CommonResponse<>(-1,
                "eureka-client-ad-sponsor-error");
    }
}
