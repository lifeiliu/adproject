package com.lilypad.ad.index.adcampaign;

import com.lilypad.ad.client.AdCampaign;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCampaignObject {
    private Long id;
    private Long userId;
    private Integer campaignStatus;
    private Date startDate;
    private Date endDate;

    public void update(AdCampaignObject newObject){
        if(null != newObject.getId()){
            this.id = newObject.getId();
        }
        if(null != newObject.getUserId()){
            this.userId = newObject.getUserId();
        }
        if(null != newObject.getCampaignStatus()){
            this.campaignStatus = newObject.getCampaignStatus();
        }
        if(null != newObject.getStartDate()){
            this.startDate = newObject.getStartDate();
        }
        if(null != newObject.getEndDate()){
            this.endDate = newObject.getEndDate();
        }
    }
}
