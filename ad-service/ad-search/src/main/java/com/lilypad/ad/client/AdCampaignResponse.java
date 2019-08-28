package com.lilypad.ad.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCampaignResponse {
    private Long id;
    private String campaignName;
}
