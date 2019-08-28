package com.lilypad.ad.index.creative;

import com.lilypad.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class CreativeIndex implements IndexAware<Long, CreativeObject> {
    private static Map<Long, CreativeObject> creativeIndexMap;
    static{
        creativeIndexMap = new ConcurrentHashMap<>();
    }


    @Override
    public CreativeObject get(Long key) {
        CreativeObject result = creativeIndexMap.get(key);
        return result;
    }

    @Override
    public void add(Long key, CreativeObject value) {
        log.info("CreativeIndex before add: {}",creativeIndexMap);
        creativeIndexMap.put(key, value);
        log.info("CreativeIndex after add: {}",creativeIndexMap);
    }

    @Override
    public void update(Long key, CreativeObject value) {
        log.info("CreativeIndex before update: {}",creativeIndexMap);
        CreativeObject toUpdate = creativeIndexMap.get(key);
        toUpdate.update(value);
        log.info("CreativeIndex after update: {}",creativeIndexMap);

    }

    @Override
    public void delete(Long key, CreativeObject value) {
        log.info("CreativeIndex before delete: {}",creativeIndexMap);
        creativeIndexMap.remove(key,value);
        log.info("CreativeIndex after delete: {}",creativeIndexMap);

    }
}
