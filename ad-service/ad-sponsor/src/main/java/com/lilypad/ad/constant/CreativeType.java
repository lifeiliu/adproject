package com.lilypad.ad.constant;

import lombok.Getter;

@Getter
public enum CreativeType {
    IMAGE(1, "image"),
    VIDEO(2, "vedio"),
    TEXT(3, "text");

    private int type;
    private String desc;

    CreativeType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
