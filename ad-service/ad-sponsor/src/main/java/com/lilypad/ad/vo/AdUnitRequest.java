package com.lilypad.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {
    private Long id;
    private List<Long> ids;
    private Long campaignId;
    private String unitName;
    private Integer positionType;
    private Long budget;


    public boolean createValidate(){
        return  null!=campaignId && !StringUtils.isEmpty(unitName)
                && positionType != null && budget != null;
    }

    public boolean updateValidate(){
        return id != null && null!=campaignId;
    }

    public boolean deleteValidate(){
        return id != null && campaignId !=null;
    }

    public boolean getValidate(){
        return id != null && CollectionUtils.isEmpty(ids);
    }

}
