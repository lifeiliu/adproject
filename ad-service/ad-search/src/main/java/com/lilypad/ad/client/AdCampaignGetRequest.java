package com.lilypad.ad.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCampaignGetRequest {
    private Long userId;
    private List<Long> ids;

    public boolean getValidate(){
        return userId != null && !CollectionUtils.isEmpty(ids);
    }
}
