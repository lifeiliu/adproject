package com.lilypad.ad.index.CreativeUnit;

import com.lilypad.ad.index.IndexAware;
import com.lilypad.ad.index.adUnit.AdUnitObject;
import com.lilypad.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@Slf4j
public class CreativeUnitIndex implements IndexAware<String, CreativeUnitObject> {
    //key: adId-UnitId
    private static Map<String, CreativeUnitObject> objectMap;
    private static Map<Long, Set<Long>> adUnitsMap;
    private static Map<Long, Set<Long>> unitAdsMap;
    static {
        objectMap = new ConcurrentHashMap<>();
        adUnitsMap = new ConcurrentHashMap<>();
        unitAdsMap = new ConcurrentHashMap<>();
    }
    @Override
    public CreativeUnitObject get(String key) {
        return objectMap.get(key);
    }

    @Override
    public void add(String key, CreativeUnitObject value) {
        log.info("CreativeUnitIndex before add: {}",objectMap);
        objectMap.put(key, value);
        Long unitId = value.getUnitId();
        Long adId = value.getAdId();
        Set<Long> ads = CommonUtils.getOrCreate(
                unitId, unitAdsMap,
                ConcurrentSkipListSet::new
        );
        ads.add(adId);
        Set<Long> units = CommonUtils.getOrCreate(
                adId, adUnitsMap,
                ConcurrentSkipListSet::new
        );
        units.add(unitId);
        log.info("CreativeUnitIndex after add: {}",objectMap);
    }

    @Override
    public void update(String key, CreativeUnitObject value) {
        log.error("CreativeUnitIndex does not support update operation," +
                "please delete then add new ");

    }

    @Override
    public void delete(String key, CreativeUnitObject value) {
        log.info("CreativeUnitIndex before add: {}",objectMap);
        objectMap.remove(key);
        Long unitId = value.getUnitId();
        Long adId = value.getAdId();
        Set<Long> ads = unitAdsMap.get(unitId);
        if (CollectionUtils.isNotEmpty(ads)){
            ads.remove(adId);
        }
        Set<Long> units = adUnitsMap.get(adId);
        if (CollectionUtils.isNotEmpty(units)){
            units.remove(unitId);
        }
        log.info("CreativeUnitIndex after add: {}",objectMap);

    }
    public Set<Long> selectAdsByObjects (Set<CreativeUnitObject> objects){
        if(CollectionUtils.isEmpty(objects)){
            return Collections.emptySet();
        }
        Set<Long> ads = new HashSet<>();
        for(CreativeUnitObject each : objects){
            Set<Long> adsInEachUnit = unitAdsMap.get(each.getUnitId());
            if (CollectionUtils.isNotEmpty(adsInEachUnit)){
                ads.addAll(adsInEachUnit);
            }
        }
        return ads;
    }
}
