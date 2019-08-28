package com.lilypad.ad.service;


import com.alibaba.fastjson.JSON;
import com.lilypad.ad.Application;
import com.lilypad.ad.constant.CommonStatus;
import com.lilypad.ad.dao.AdCampaignRepository;
import com.lilypad.ad.dao.AdUnitRepository;
import com.lilypad.ad.dao.CreativeRepository;
import com.lilypad.ad.dao.CreativeUnitRepository;
import com.lilypad.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.lilypad.ad.dao.unit_condition.AdUnitInterestRepository;
import com.lilypad.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.lilypad.ad.dump.DConstant;
import com.lilypad.ad.dump.table.*;
import com.lilypad.ad.entities.AdCampaign;
import com.lilypad.ad.entities.AdUnit;
import com.lilypad.ad.entities.Creative;
import com.lilypad.ad.entities.CreativeUnit;
import com.lilypad.ad.entities.unit_condition.AdUnitDistrict;
import com.lilypad.ad.entities.unit_condition.AdUnitInterest;
import com.lilypad.ad.entities.unit_condition.AdUnitKeyword;
import com.lilypad.ad.vo.AdUnitKeywordRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
@Slf4j
public class DumpDataService {

    private AdCampaignRepository campaignRepository;
    private AdUnitRepository unitRepository;
    private CreativeRepository creativeRepository;
    private AdUnitKeywordRepository unitKeywordRepository;
    private AdUnitInterestRepository unitInterestRepository;
    private AdUnitDistrictRepository unitDistrictRepository;
    private CreativeUnitRepository creativeUnitRepository;

    @Autowired
    public DumpDataService(AdCampaignRepository campaignRepository,
                           AdUnitRepository unitRepository, CreativeRepository creativeRepository,
                           AdUnitKeywordRepository unitKeywordRepository,
                           AdUnitInterestRepository unitInterestRepository,
                           AdUnitDistrictRepository unitDistrictRepository,
                           CreativeUnitRepository creativeUnitRepository) {
        this.campaignRepository = campaignRepository;
        this.unitRepository = unitRepository;
        this.creativeRepository = creativeRepository;
        this.unitKeywordRepository = unitKeywordRepository;
        this.unitInterestRepository = unitInterestRepository;
        this.unitDistrictRepository = unitDistrictRepository;
        this.creativeUnitRepository = creativeUnitRepository;
    }

    @Test
    public void dumpData(){
        dumpAdCampaignTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_CAMPAIGN));
        dumpAdUnitTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT));
        dumpCreativeTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_CREATIVE));
        dumpCreativeUnitTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_CREATIVE_UNIT));
        dumpUnitKeywordTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_KEYWORD));
        dumpUnitInterestTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_INTEREST));
        dumpUnitDistrictTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_DISTRICT));

    }
    private void dumpAdCampaignTable(String fileName){
        List<AdCampaign> campaigns = campaignRepository.findAllByCampaignStatus(
                CommonStatus.VALID.getStatus());
        if(CollectionUtils.isEmpty(campaigns)){
            return;
        }
        List<AdCampaignTable> campaignTableContent = new ArrayList<>();
        for(AdCampaign campaign: campaigns){
            campaignTableContent.add(new AdCampaignTable(
               campaign.getId(), campaign.getUserId(),
               campaign.getCampaignStatus(),campaign.getStartDate(),
               campaign.getEndDate()
            ));
        }
        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdCampaignTable eachRecord: campaignTableContent){
                writer.write(JSON.toJSONString(eachRecord));
                writer.newLine();
                writer.close();
            }
        }catch (IOException e){
            log.error("writing dumpAdCampaignTable error" );
        }
    }

    private void dumpAdUnitTable(String fileName){
        List<AdUnit> unitData = unitRepository.findAllByUnitStatus(
                CommonStatus.VALID.getStatus());
        if (CollectionUtils.isEmpty(unitData)){
            return;
        }
        List<AdUnitTable> tableRecords = new ArrayList<>();
        unitData.forEach(u-> tableRecords.add(
                new AdUnitTable(u.getId(), u.getUnitStatus(),
                        u.getPositionType(),u.getCampaignId())
        ));
        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdUnitTable eachRecord:tableRecords){
                writer.write(JSON.toJSONString(eachRecord));
                writer.newLine();
                writer.close();
            }
        }catch (IOException e){
            log.error("writing dumpAdUnitTable error" );
        }

    }

    private void dumpCreativeTable(String fileName){
        List<Creative> creativeData = creativeRepository.findAll();
        if (CollectionUtils.isEmpty(creativeData)){
            return;
        }
        List<CreativeTable> tableRecords = new ArrayList<>();
        creativeData.forEach(u-> tableRecords.add(
                new CreativeTable(u.getId(), u.getName(),
                        u.getUserId(),u.getType(),
                        u.getMeterialType(),u.getHeight(),
                        u.getWidth(),
                        u.getAuditStatus(),u.getUrl())
        ));
        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for(CreativeTable eachRecord:tableRecords){
                writer.write(JSON.toJSONString(eachRecord));
                writer.newLine();
                writer.close();
            }
        }catch (IOException e){
            log.error("writing dumpCreativeTable error" );
        }
    }

    private void dumpUnitKeywordTable(String fileName){
        List<AdUnitKeyword> unitKeywordData = unitKeywordRepository.findAll();
        if (CollectionUtils.isEmpty(unitKeywordData)){
            return;
        }
        List<AdUnitKeywordTable> tableRecords = new ArrayList<>();
        unitKeywordData.forEach(u-> tableRecords.add(
                new AdUnitKeywordTable(u.getUnitId(), u.getKeyword())
        ));
        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdUnitKeywordTable eachRecord:tableRecords){
                writer.write(JSON.toJSONString(eachRecord));
                writer.newLine();
                writer.close();
            }
        }catch (IOException e){
            log.error("writing dumpUnitKeywordTable error" );
        }
    }

    private void dumpUnitInterestTable(String fileName){
        List<AdUnitInterest> unitInterestData = unitInterestRepository.findAll();
        if (CollectionUtils.isEmpty(unitInterestData)){
            return;
        }
        List<AdUnitInterestTable> tableRecords = new ArrayList<>();
        unitInterestData.forEach(u-> tableRecords.add(
                new AdUnitInterestTable(u.getUnitId(), u.getInterestTag())
        ));
        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdUnitInterestTable eachRecord:tableRecords){
                writer.write(JSON.toJSONString(eachRecord));
                writer.newLine();
                writer.close();
            }
        }catch (IOException e){
            log.error("writing dumpUnitInterestTable error" );
        }
    }

    private void dumpUnitDistrictTable(String fileName){
        List<AdUnitDistrict> unitDistrictData = unitDistrictRepository.findAll();
        if (CollectionUtils.isEmpty(unitDistrictData)){
            return;
        }
        List<AdUnitDistrictTable> tableRecords = new ArrayList<>();
        unitDistrictData.forEach(u-> tableRecords.add(
                new AdUnitDistrictTable(u.getUnitId(), u.getState(),u.getCity())
        ));
        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdUnitDistrictTable eachRecord:tableRecords){
                writer.write(JSON.toJSONString(eachRecord));
                writer.newLine();
                writer.close();
            }
        }catch (IOException e){
            log.error("writing dumpUnitDistrictTable error" );
        }
    }
    private void dumpCreativeUnitTable(String fileName){
        List<CreativeUnit> creativeUnitData = creativeUnitRepository.findAll();
        if (CollectionUtils.isEmpty(creativeUnitData)){
            return;
        }
        List<CreativeUnitTable> tableRecords = new ArrayList<>();
        creativeUnitData.forEach(u-> tableRecords.add(
                new CreativeUnitTable(u.getUnitId(), u.getCreativeId())
        ));
        Path path = Paths.get(fileName);
        try(BufferedWriter writer = Files.newBufferedWriter(path)){
            for(CreativeUnitTable eachRecord:tableRecords){
                writer.write(JSON.toJSONString(eachRecord));
                writer.newLine();
                writer.close();
            }
        }catch (IOException e){
            log.error("writing dumpUnitDistrictTable error" );
        }
    }

}
