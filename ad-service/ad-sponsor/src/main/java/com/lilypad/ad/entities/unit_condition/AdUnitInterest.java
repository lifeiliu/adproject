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
public class AdUnitInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long unitId;
    private String interestTag;

    public AdUnitInterest(Long unitId, String interestTag){
        this.unitId = unitId;
        this.interestTag = interestTag;
    }
}
