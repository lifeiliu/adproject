package com.lilypad.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictRequest {
    List<UnitDistrict> districs;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitDistrict{
        private Long unitId;
        private String state;
        private String city;
    }
}
