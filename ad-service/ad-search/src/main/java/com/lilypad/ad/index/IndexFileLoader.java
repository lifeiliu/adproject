package com.lilypad.ad.index;

import com.alibaba.fastjson.JSON;
import com.lilypad.ad.dump.DConstant;
import com.lilypad.ad.dump.table.*;
import com.lilypad.ad.handler.AdLevelDataHandler;
import com.lilypad.ad.mysql.constant.OpType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
@DependsOn("DataTable")
public class IndexFileLoader {
    @PostConstruct
    public void init(){
        List<String> adCampaignData = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CAMPAIGN));
        adCampaignData.forEach(p -> AdLevelDataHandler.handleLevel2(
                JSON.parseObject(p, AdCampaignTable.class),
                OpType.ADD));
        List<String> creativeData = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CREATIVE));
        adCampaignData.forEach(p -> AdLevelDataHandler.handleLevel2(
                JSON.parseObject(p, CreativeTable.class),
                OpType.ADD));

        List<String> adUnitData = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT));

        adUnitData.forEach(p->AdLevelDataHandler.handleLevel3(
                JSON.parseObject(p, AdUnitTable.class),
                OpType.ADD));
        List<String> creativeUnitData = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CREATIVE_UNIT));

        creativeUnitData.forEach(p->AdLevelDataHandler.handleLevel3(
                JSON.parseObject(p, CreativeUnitTable.class),
                OpType.ADD));
        List<String> unitKeywordData = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_KEYWORD));

        unitKeywordData.forEach(p->AdLevelDataHandler.handleLevel4(
                JSON.parseObject(p, AdUnitKeywordTable.class),
                OpType.ADD));
        List<String> unitInterestData = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_INTEREST));

        unitInterestData.forEach(p->AdLevelDataHandler.handleLevel4(
                JSON.parseObject(p, AdUnitInterestTable.class),
                OpType.ADD));
        List<String> unitDistrictData = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_DISTRICT));

        unitDistrictData.forEach(p->AdLevelDataHandler.handleLevel4(
                JSON.parseObject(p, AdUnitDistrictTable.class),
                OpType.ADD));
    }

    private List<String> loadDumpData(String fileName){
        try(BufferedReader br = Files.newBufferedReader(
                Paths.get(fileName)
        )){
            return br.lines().collect(Collectors.toList());
        }catch(IOException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
