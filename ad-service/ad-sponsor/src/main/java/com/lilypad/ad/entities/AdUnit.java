package com.lilypad.ad.entities;

import com.lilypad.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long campaignId;
    private String unitName;
    // the positions of the type(MobileApp, Movie Opening, , Social Media)
    private Integer positionType;
    private Integer unitStatus;
    private Long bugget;
    private Date createTime;
    private Date updateTime;

    public AdUnit(Long campaignId, String unitName,
                  Integer positionType, Long bugget){
        this.campaignId = campaignId;
        this.unitName = unitName;
        this.positionType = positionType;
        this.bugget = bugget;
        this.unitStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }

}
