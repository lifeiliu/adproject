package com.lilypad.ad.index.adcampaign;

import com.lilypad.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
@Component
public class AdCampaignIndex implements IndexAware<Long, AdCampaignObject> {
    private static Map<Long, AdCampaignObject> objectMap;
    static {
        objectMap = new ConcurrentHashMap<>();
    }
    @Override
    public AdCampaignObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdCampaignObject value) {
        log.info("before add: {}", objectMap);
        objectMap.put(key, value);
        log.info("after add:{}",objectMap);
    }

    @Override
    public void update(Long key, AdCampaignObject value) {
        log.info("before update: {}",objectMap);
        AdCampaignObject toUpdate = objectMap.get(key);
        if( null == toUpdate){
            objectMap.put(key,value);
        }else{
            toUpdate.update(value);
        }
        log.info("after update: {}",objectMap);
    }

    @Override
    public void delete(Long key, AdCampaignObject value) {
        log.info("before delete: {}",objectMap);
        objectMap.remove(key);
        log.info("after delete: {}",objectMap);
    }
}
