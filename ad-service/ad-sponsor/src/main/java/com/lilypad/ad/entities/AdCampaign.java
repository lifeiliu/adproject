package com.lilypad.ad.entities;

import com.lilypad.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ad_plan")
public class AdCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String campaignName;
    private Integer campaignStatus;
    private Date startDate;
    private Date endDate;
    private Date createTime;
    private Date updateTime;

    public AdCampaign(Long userId, String CampaignName,
                      Date startDate, Date endDate){
        this.userId = userId;
        this.campaignName = campaignName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campaignStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }

}
