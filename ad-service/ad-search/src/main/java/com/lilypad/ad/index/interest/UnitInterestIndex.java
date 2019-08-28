package com.lilypad.ad.index.interest;

import com.lilypad.ad.index.IndexAware;
import com.lilypad.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
@Slf4j
public class UnitInterestIndex implements IndexAware<String, Set<Long>> {

    private static Map<String, Set<Long>> interestTagUnitsMap;
    private static Map<Long, Set<String>> unitInterestTagsMap;
    static {
        interestTagUnitsMap = new ConcurrentHashMap<>();
        unitInterestTagsMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        if (StringUtils.isEmpty(key)){
            return Collections.emptySet();
        }
        Set<Long> result = interestTagUnitsMap.get(key);
        if(result == null){
            return Collections.emptySet();
        }
        return result;
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("UnitInterestIndex, before add: {}",interestTagUnitsMap);
        Set<Long> unitIds = CommonUtils.getOrCreate(
                key, interestTagUnitsMap,
                ConcurrentSkipListSet::new
        );
        unitIds.addAll(value);
        for (Long id : value){
            Set<String> tags = CommonUtils.getOrCreate(
                    id, unitInterestTagsMap,
                    ConcurrentSkipListSet::new
            );
            tags.add(key);
        }
        log.info("UnitInterestIndex, after update: {}",interestTagUnitsMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("UnitInterestIndex does not support update, please delete then create");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitInterestIndex, before delete: {}",interestTagUnitsMap);
        Set<Long> units = CommonUtils.getOrCreate(
                key, interestTagUnitsMap,
                ConcurrentSkipListSet::new
        );
        units.removeAll(value);
        for(Long id : value){
            Set<String> tags = CommonUtils.getOrCreate(
                    id, unitInterestTagsMap,
                    ConcurrentSkipListSet::new
            );
            tags.remove(key);
        }
        log.info("UnitInterestIndex, after delete: {}",interestTagUnitsMap);
    }

    public boolean match(Long unitId, List<String> tags){
        if (unitInterestTagsMap.containsKey(unitId) &&
                CollectionUtils.isNotEmpty(unitInterestTagsMap.get(unitId))){
            return CollectionUtils.isSubCollection(tags,unitInterestTagsMap.get(unitId));
        }
        return false;
    }
}
