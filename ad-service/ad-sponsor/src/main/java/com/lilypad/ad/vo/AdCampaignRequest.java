package com.lilypad.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdCampaignRequest {
    @Id
    private Long id;
    private Long userId;
    private String campaignName;
    private String startDate;
    private String endDate;

    public boolean createValidate(){
        return userId!=null
                && !StringUtils.isEmpty(campaignName)
                && !StringUtils.isEmpty(startDate)
                && !StringUtils.isEmpty(endDate);
    }

    public boolean updateValidate(){
        return id != null && userId != null;
    }
    public boolean deleteValidate(){
        return id != null && userId != null;
    }
}
