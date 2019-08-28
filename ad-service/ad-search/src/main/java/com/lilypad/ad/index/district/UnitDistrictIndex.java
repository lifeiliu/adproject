package com.lilypad.ad.index.district;

import com.lilypad.ad.index.IndexAware;
import com.lilypad.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@Slf4j
public class UnitDistrictIndex implements IndexAware<String, Set<Long>> {
    // String key is a district, like state-city
    private static Map<String ,Set<Long> > districtUnitsMap;
    private static Map<Long, Set<String>> unitDistrictsMap;
    static {
        districtUnitsMap = new ConcurrentHashMap<>();
        unitDistrictsMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        Set<Long> units = districtUnitsMap.get(key);
        if(units == null){
            return Collections.emptySet();
        }
        return units;
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("UnitDistrictIndex, before add: {}", districtUnitsMap);
        Set<Long> units = CommonUtils.getOrCreate(
                key, districtUnitsMap,
                ConcurrentSkipListSet::new
        );
        units.addAll(value);
        for(Long id : value){
            Set<String> districts = CommonUtils.getOrCreate(
                    id, unitDistrictsMap,
                    ConcurrentSkipListSet::new
            );
            districts.add(key);
        }
        log.info("UnitDistrictIndex, after add: {}", districtUnitsMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("UnitDistrictIndex does not support update operation, please delete then create new");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitDistrictIndex, before delete: {}", districtUnitsMap);
        Set<Long> units = CommonUtils.getOrCreate(
                key, districtUnitsMap,
                ConcurrentSkipListSet::new
        );
        units.removeAll(value);
        for(Long id : value){
            Set<String> districts = CommonUtils.getOrCreate(
                    id, unitDistrictsMap,
                    ConcurrentSkipListSet::new
            );
            districts.remove(key);
        }
        log.info("UnitDistrictIndex, after delete: {}", districtUnitsMap);

    }
}
