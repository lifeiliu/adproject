package com.lilypad.ad.constant;

import lombok.Getter;

@Getter
public enum CommonStatus {
    VALID(1, "valid"),
    INVALID(0,"in_valid");

    private Integer status;
    private String description;

    CommonStatus(Integer status, String description){
        this.status = status;
        this.description = description;
    }

}
