package com.lilypad.ad.controllers;

import com.alibaba.fastjson.JSON;
import com.lilypad.ad.entities.AdUnit;
import com.lilypad.ad.entities.CreativeUnit;
import com.lilypad.ad.entities.unit_condition.AdUnitDistrict;
import com.lilypad.ad.entities.unit_condition.AdUnitInterest;
import com.lilypad.ad.entities.unit_condition.AdUnitKeyword;
import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.service.IAdUnitService;
import com.lilypad.ad.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/adUnits")
public class AdUnitController {
    @Autowired
    private IAdUnitService unitService;

    @PostMapping("")
    public AdUnitResponse createAdUnit(
            @RequestBody AdUnitRequest request) throws AdException{
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        return unitService.createAdUnit(request);
    }
    @PostMapping("/get")
    public List<AdUnit> getByCampaignId(
            @RequestBody AdUnitRequest request) throws AdException{
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        return unitService.getAdUnit(request);
    }
    @PutMapping("")
    public AdUnitResponse updateAdUnit(
            @RequestBody AdUnitRequest request) throws AdException{
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        return unitService.updateAdUnit(request);
    }
    @DeleteMapping("")
    public void deleteAdUnit(
            @RequestBody AdUnitRequest request) throws AdException{
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        unitService.deleteAdUnit(request);
    }
    @PostMapping("/keywords")
    public AdUnitKeywordResponse createAdUnitKeyword(
            @RequestBody AdUnitKeywordRequest request) throws AdException{
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        return unitService.createUniteKeywords(request);
    }
    @PostMapping("/interests")
    public AdUnitInterestResponse createAdUnitInterests(
            @RequestBody AdUnitInterestRequest request) throws AdException{
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        return unitService.createUniteInterests(request);
    }
    @PostMapping("/districts")
    public AdUnitDistrictResponse createAdUnitDistricts(
            @RequestBody AdUnitDistrictRequest request) throws AdException{
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        return unitService.createUniteDistricts(request);
    }
    @PostMapping("/creativeUnit")
    public CreativeUnitResponse createCreativeUnit(
            @RequestBody CreativeUnitRequest request) throws AdException{
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        return unitService.createCreativeUnit(request);
    }

}
