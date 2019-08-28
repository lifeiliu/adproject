package com.lilypad.ad.service;

import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.vo.CreativeRequest;
import com.lilypad.ad.vo.CreativeResponse;

public interface ICreativeService {
    CreativeResponse createCreative(CreativeRequest request) throws AdException;
//    List<AdUnit> getAdUnit(AdUnitRequest request) throws AdException;
//    AdUnitResponse updateAdUnit(AdUnitRequest request) throws AdException;
//    void deleteAdUnit(AdUnitRequest request) throws AdException;

}
