package com.lilypad.ad.entities;

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
public class CreativeUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long creativeId;
    private Long unitId;

    public CreativeUnit(Long creativeId, Long unitId){
        this.creativeId = creativeId;
        this.unitId = unitId;
    }
}
