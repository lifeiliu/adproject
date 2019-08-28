package com.lilypad.ad.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdCampaign {

    private Long id;
    private Long userId;
    private String campaignName;
    private Integer campaignStatus;
    private Date startDate;
    private Date endDate;
    private Date createTime;
    private Date updateTime;


}
