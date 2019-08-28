package com.lilypad.ad.index.keyword;

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
public class UnitKeywordIndex implements IndexAware<String, Set<Long>> {
    private static Map<String, Set<Long>> keywordUnitMap;
    private static Map<Long, Set<String>> unitKeywordMap;
    static {
        keywordUnitMap = new ConcurrentHashMap<>();
        unitKeywordMap = new ConcurrentHashMap<>();
    }
    @Override
    public Set<Long> get(String key) {
        if(StringUtils.isEmpty(key)){
            return Collections.emptySet();
        }
        Set<Long> result = keywordUnitMap.get(key);
        if (result == null){
            return  Collections.emptySet();
        }
        return result;
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("UnitKeywordIndex, before add: {}",keywordUnitMap);
        Set<Long> unitIdSet = CommonUtils.getOrCreate(
                key, keywordUnitMap,
                ConcurrentSkipListSet::new
        );
        unitIdSet.addAll(unitIdSet);
        for(Long id: value){
            Set<String> keywordSet = CommonUtils.getOrCreate(
                    id, unitKeywordMap,
                    ConcurrentSkipListSet::new
            );
            keywordSet.add(key);
        }
        log.info("UnitKeywordIndex, after add: {}",keywordUnitMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("keyword index do not support update, please delete then add new");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitKeywordIndex, before delete: {}",keywordUnitMap);
        Set<Long> unitSet = CommonUtils.getOrCreate(
                key, keywordUnitMap,
                ConcurrentSkipListSet::new
        );
        unitSet.removeAll(unitSet);
        for(Long id: value){
            Set<String> keywordSet = CommonUtils.getOrCreate(
                    id, unitKeywordMap,
                    ConcurrentSkipListSet::new
            );
            keywordSet.remove(key);
        }
        log.info("UnitKeywordIndex, after delete: {}",keywordUnitMap);
    }
    public boolean match(Long unitId, List<String> keywords){
        if(unitKeywordMap.containsKey(unitId)
                && CollectionUtils.isNotEmpty(unitKeywordMap.get(unitId))){
            Set<String> keywordsOfUnit = unitKeywordMap.get(unitId);
            return CollectionUtils.isSubCollection(keywords, keywordsOfUnit);
        }
        return false;
    }
}
