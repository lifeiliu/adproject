package com.lilypad.ad.vo;

import com.lilypad.ad.entities.Creative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeRequest {
    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Long size;
    private Integer duration;
    private Long userId;
    private String url;

    public Creative convertToEntity(){
        Creative creative = new Creative();
        creative.setName(name);
        creative.setType(type);
        creative.setMeterialType(materialType);
        creative.setHeight(height);
        creative.setWidth(width);
        creative.setSize(size);
        creative.setDuration(duration);
        creative.setUserId(userId);
        creative.setUrl(url);
        return creative;
    }



}
