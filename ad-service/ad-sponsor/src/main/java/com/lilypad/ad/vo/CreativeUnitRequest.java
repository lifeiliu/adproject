package com.lilypad.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitRequest {
    private Long creativeId;
    private Long unitId;

    public boolean validate(){
        return creativeId != null && unitId != null;
    }
}
