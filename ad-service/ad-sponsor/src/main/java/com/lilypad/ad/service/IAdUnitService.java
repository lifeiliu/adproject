package com.lilypad.ad.service;

import com.lilypad.ad.entities.AdCampaign;
import com.lilypad.ad.entities.AdUnit;
import com.lilypad.ad.entities.unit_condition.AdUnitDistrict;
import com.lilypad.ad.entities.unit_condition.AdUnitInterest;
import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.vo.*;

import java.util.List;

public interface IAdUnitService {
    AdUnitResponse createAdUnit(AdUnitRequest request) throws AdException;
    List<AdUnit> getAdUnit(AdUnitRequest request) throws AdException;
    AdUnitResponse updateAdUnit(AdUnitRequest request) throws AdException;
    void deleteAdUnit(AdUnitRequest request) throws AdException;
    AdUnitKeywordResponse createUniteKeywords(AdUnitKeywordRequest request) throws AdException;
    AdUnitDistrictResponse createUniteDistricts(AdUnitDistrictRequest request) throws AdException;
    AdUnitInterestResponse createUniteInterests(AdUnitInterestRequest request) throws AdException;
    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws  AdException;

}
