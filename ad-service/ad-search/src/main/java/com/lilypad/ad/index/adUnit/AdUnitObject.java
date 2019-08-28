package com.lilypad.ad.index.adUnit;


import com.lilypad.ad.index.adcampaign.AdCampaignObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitObject {
    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long campaignId;
    private AdCampaignObject campaignObject;

    void update(AdUnitObject newObject){
        if( null != newObject.getUnitId()){
            unitId = newObject.getUnitId();
        }
        if( null != newObject.getCampaignId()){
            campaignId = newObject.getCampaignId();
        }
        if( null != newObject.getUnitStatus()){
            unitStatus = newObject.getUnitStatus();
        }
        if( null != newObject.getPositionType()){
            positionType = newObject.getPositionType();
        }

        if(null != newObject.getCampaignObject()){
            campaignObject = newObject.getCampaignObject();
        }

    }

}
