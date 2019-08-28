package com.lilypad.ad.entities.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdUnitKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long unitId;
    private String keyword;

    public AdUnitKeyword(Long unitId, String keyword){
        this.unitId = unitId;
        this.keyword = keyword;
    }
}
