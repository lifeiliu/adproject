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
public class AdUnitDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long unitId;
    private String state;
    private String city;

    public AdUnitDistrict(Long unitId, String state, String city){
        this.unitId = unitId;
        this.state = state;
        this.city = city;
    }
}
