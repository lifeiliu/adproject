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
public class Creative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer type;
    private Integer meterialType;
    private  Integer height;
    private  Integer width;
    private Long size;
    private Integer duration;
    private Integer auditStatus;
    private Long userId;
    private String url;
    private Date createTime;
    private Date updateTime;


}
