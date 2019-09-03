package com.lilypad.ad.handler;

import com.alibaba.fastjson.JSON;
import com.lilypad.ad.dump.table.*;
import com.lilypad.ad.index.CreativeUnit.CreativeUnitIndex;
import com.lilypad.ad.index.CreativeUnit.CreativeUnitObject;
import com.lilypad.ad.index.DataTable;
import com.lilypad.ad.index.IndexAware;
import com.lilypad.ad.index.adUnit.AdUnitIndex;
import com.lilypad.ad.index.adUnit.AdUnitObject;
import com.lilypad.ad.index.adcampaign.AdCampaignIndex;
import com.lilypad.ad.index.adcampaign.AdCampaignObject;
import com.lilypad.ad.index.creative.CreativeIndex;
import com.lilypad.ad.index.creative.CreativeObject;
import com.lilypad.ad.index.district.UnitDistrictIndex;
import com.lilypad.ad.mysql.constant.OpType;
import com.lilypad.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class AdLevelDataHandler {
    public static void handleLevel2(AdCampaignTable campaignTable, OpType type){
        AdCampaignObject object = new AdCampaignObject(
                campaignTable.getId(),
                campaignTable.getUserId(),
                campaignTable.getPlanStatus(),
                campaignTable.getStartDate(),
                campaignTable.getEndDate()
        );
        handleBinlogEvent(
                DataTable.of(AdCampaignIndex.class),
                object.getId(),
                object,
                type);
    }
    public static void handleLevel2(CreativeTable creativeTable, OpType type){
        CreativeObject object = new CreativeObject(
                creativeTable.getAdId(),
                creativeTable.getName(),
                creativeTable.getType(),
                creativeTable.getMaterialType(),
                creativeTable.getHeight(),
                creativeTable.getWidth(),
                creativeTable.getAuditStatus(),
                creativeTable.getAdUrl()
        );
        handleBinlogEvent(DataTable.of(CreativeIndex.class),
                object.getAdId(), object, type);
    }
    public static void handleLevel3(AdUnitTable unitTable, OpType type){
        AdCampaignObject campaignObject = DataTable.of(
                AdCampaignIndex.class).get(unitTable.getCampaignId());
        if(null == campaignObject){
            log.error("handlerLevel3 found AdCampaignObject not exist error: {}",
                    unitTable.getCampaignId());
            return;
        }
        AdUnitObject unitObject = new AdUnitObject(
                unitTable.getUnitId(),
                unitTable.getUnitStatus(),
                unitTable.getPositionType(),
                unitTable.getCampaignId(),
                campaignObject
        );
        handleBinlogEvent(
                DataTable.of(AdUnitIndex.class),
                unitTable.getUnitId(),
                unitObject, type
        );
    }
    public static void handleLevel3(CreativeUnitTable creativeUnitTable, OpType type){
        if(type == OpType.UPDATE){
            log.error("CreativeUnitIndex does not support update");
            return;
        }
        CreativeObject creativeObject =
                DataTable.of(CreativeIndex.class).get(creativeUnitTable.getAdId());
        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(creativeUnitTable.getUnitId());
        if(creativeObject == null || unitObject == null){
            log.error("CreativeUnitTable index error: {}",
                    JSON.toJSONString(creativeUnitTable));
            return;
        }
        CreativeUnitObject object = new CreativeUnitObject(
                creativeUnitTable.getAdId(),
                creativeUnitTable.getUnitId()
        );
        handleBinlogEvent(
                DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(creativeUnitTable.getAdId().toString(),creativeUnitTable.getUnitId().toString()),
                object,type);

    }

    public static void handleLevel4(AdUnitDistrictTable unitDistrictTable, OpType type){
        if(type == OpType.UPDATE){
            log.error("UnitDistrictIndex does not support update");
            return;
        }
        AdUnitObject unitObject =
                DataTable.of(AdUnitIndex.class).get(unitDistrictTable.getUnitId());
        if(unitObject == null){
            log.error("CreativeUnitTable index error: {}",
                    JSON.toJSONString(unitDistrictTable));
            return;
        }
        String key = CommonUtils.stringConcat(unitDistrictTable.getState(),unitDistrictTable.getCity());
        Set<Long> value = new HashSet<>(
                Collections.singleton(unitDistrictTable.getUnitId())
        );
        handleBinlogEvent(
                DataTable.of(UnitDistrictIndex.class),
                key,
                value,type);

    }
    public static void handleLevel4(AdUnitKeywordTable unitKeywordTable, OpType type){
        if(type == OpType.UPDATE){
            log.error("KeywordIndex does not support update");
            return;
        }
        AdUnitObject unitObject =
                DataTable.of(AdUnitIndex.class).get(unitKeywordTable.getUnitId());
        if(unitObject == null){
            log.error("AdUnitKeywordTable index error: {}",
                    JSON.toJSONString(unitKeywordTable));
            return;
        }

        Set<Long> value = new HashSet<>(
                Collections.singleton(unitKeywordTable.getUnitId())
        );
        handleBinlogEvent(
                DataTable.of(UnitDistrictIndex.class),
                unitKeywordTable.getKeyword(),
                value,type);

    }
    public static void handleLevel4(AdUnitInterestTable unitInterestTable, OpType type){
        if(type == OpType.UPDATE){
            log.error("UnitInterestIndex does not support update");
            return;
        }
        AdUnitObject unitObject =
                DataTable.of(AdUnitIndex.class).get(unitInterestTable.getUnitId());
        if(unitObject == null){
            log.error("AdUnitKeywordTable index error: {}",
                    JSON.toJSONString(unitInterestTable.getUnitId()));
            return;
        }

        Set<Long> value = new HashSet<>(
                Collections.singleton(unitInterestTable.getUnitId())
        );
        handleBinlogEvent(
                DataTable.of(UnitDistrictIndex.class),
                unitInterestTable.getInterestTag(),
                value,type);

    }
    private static <K,V> void handleBinlogEvent(
            IndexAware<K,V> index,
            K key,
            V value,
            OpType type
    ){
        switch (type){
            case ADD:
                index.add(key,value);
                break;
            case UPDATE:
                index.update(key,value);
                break;
            case DELETE:
                index.delete(key, value);
                break;
            default:
                break;
        }
    }
}
