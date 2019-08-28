package com.lilypad.ad.service.impl;

import com.lilypad.ad.constant.CommonStatus;
import com.lilypad.ad.constant.ErrorMessages;
import com.lilypad.ad.dao.AdCampaignRepository;
import com.lilypad.ad.dao.AdUnitRepository;
import com.lilypad.ad.dao.CreativeRepository;
import com.lilypad.ad.dao.CreativeUnitRepository;
import com.lilypad.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.lilypad.ad.dao.unit_condition.AdUnitInterestRepository;
import com.lilypad.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.lilypad.ad.entities.AdCampaign;
import com.lilypad.ad.entities.AdUnit;
import com.lilypad.ad.entities.CreativeUnit;
import com.lilypad.ad.entities.unit_condition.AdUnitDistrict;
import com.lilypad.ad.entities.unit_condition.AdUnitInterest;
import com.lilypad.ad.entities.unit_condition.AdUnitKeyword;
import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.service.IAdUnitService;
import com.lilypad.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdUnitServiceImpl implements IAdUnitService {
    @Autowired
    AdCampaignRepository campaignRepository;
    @Autowired
    AdUnitRepository unitRepository;
    @Autowired
    AdUnitKeywordRepository unitKeywordRepository;
    @Autowired
    AdUnitInterestRepository unitInterestRepository;
    @Autowired
    AdUnitDistrictRepository unitDistrictRepository;
    @Autowired
    CreativeRepository creativeRepository;
    @Autowired
    CreativeUnitRepository creativeUnitRepository;

    @Override
    @Transactional
    public AdUnitResponse createAdUnit(AdUnitRequest request) throws AdException {
        if(! request.getValidate()){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        Optional<AdCampaign> campaign = campaignRepository.findById(request.getCampaignId());
        if(!campaign.isPresent()){
            throw new AdException(ErrorMessages.CAN_NOT_FIND_RECORD_ERROR);
        }
        AdUnit unitToCheck = unitRepository.findByCampaignIdAndUnitName(request.getCampaignId(), request.getUnitName());
        if( unitToCheck != null){
            throw new AdException(ErrorMessages.SAME_UNIT_NAME_ERROR);
        }
        AdUnit savedUnit = unitRepository.save(new AdUnit(request.getCampaignId(), request.getUnitName(),
                request.getPositionType(), request.getBudget()));

        return new AdUnitResponse(savedUnit.getId(), savedUnit.getUnitName());
    }

    @Override
    @Transactional
    public List<AdUnit> getAdUnit(AdUnitRequest request) throws AdException {
        if(!request.getValidate()){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        List<AdUnit> adUnits = unitRepository.findAllByCampaignId(request.getCampaignId());
        return adUnits;
    }

    @Override
    @Transactional
    public AdUnitResponse updateAdUnit(AdUnitRequest request) throws AdException {
        if(! request.updateValidate()){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        AdUnit unitToUpdate = unitRepository.findByCampaignIdAndUnitName(request.getCampaignId(), request.getUnitName());
        if (unitToUpdate != null){
            throw new AdException(ErrorMessages.CAN_NOT_FIND_RECORD_ERROR);
        }
        if(request.getPositionType() != null){
            unitToUpdate.setPositionType(request.getPositionType());
        }
        if(request.getBudget() != null){
            unitToUpdate.setBugget(request.getBudget());
        }
        unitToUpdate.setUpdateTime(new Date());
        unitRepository.save(unitToUpdate);
        return new AdUnitResponse(unitToUpdate.getId(), unitToUpdate.getUnitName());
    }

    @Override
    @Transactional
    public void deleteAdUnit(AdUnitRequest request) throws AdException {
        if(!request.deleteValidate()){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        AdUnit unitToDel = unitRepository.findByCampaignIdAndUnitName(request.getCampaignId(), request.getUnitName());
        if (unitToDel != null){
            throw new AdException(ErrorMessages.CAN_NOT_FIND_RECORD_ERROR);
        }
        unitToDel.setUnitStatus(CommonStatus.INVALID.getStatus());
        unitRepository.save(unitToDel);
    }

    @Override
    public AdUnitKeywordResponse createUniteKeywords(AdUnitKeywordRequest request) throws AdException {
        List<Long> unitIds = request.getKeywords().stream()
                            .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                            .collect(Collectors.toList());
        if(!isRelatedUnitesExist(unitIds)){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        List<AdUnitKeyword> toSave = new ArrayList<>();
        List<Long> ids = Collections.emptyList();
        if(!CollectionUtils.isEmpty(request.getKeywords())){
            request.getKeywords().forEach(each->{
                toSave.add(new AdUnitKeyword(each.getUnitId(),each.getKeyword()));
            });
            ids = unitKeywordRepository.saveAll(toSave).stream()
                    .map(AdUnitKeyword::getId).collect(Collectors.toList());
        }

        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUniteDistricts(AdUnitDistrictRequest request) throws AdException {
        List<Long> unitIds = request.getDistrics().stream()
                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitesExist(unitIds)){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        List<AdUnitDistrict> toSave = new ArrayList<>();
        List<Long> ids = Collections.emptyList();
        if(!CollectionUtils.isEmpty(request.getDistrics())){
            request.getDistrics().forEach(each->{
                toSave.add(new AdUnitDistrict(each.getUnitId(),each.getState(), each.getCity()));
            });
            ids = unitDistrictRepository.saveAll(toSave).stream()
                    .map(AdUnitDistrict::getId).collect(Collectors.toList());
        }

        return new AdUnitDistrictResponse(ids);
    }

    @Override
    public AdUnitInterestResponse createUniteInterests(AdUnitInterestRequest request) throws AdException {
        List<Long> unitIds = request.getInterests().stream()
                .map(AdUnitInterestRequest.UnitInterest::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitesExist(unitIds)){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        List<AdUnitInterest> toSave = new ArrayList<>();
        List<Long> ids = Collections.emptyList();
        if(!CollectionUtils.isEmpty(request.getInterests())){
            request.getInterests().forEach(each->{
                toSave.add(new AdUnitInterest(each.getUnitId(),each.getInterestTag()));
            });
            ids = unitInterestRepository.saveAll(toSave).stream()
                    .map(AdUnitInterest::getId).collect(Collectors.toList());
        }

        return new AdUnitInterestResponse(ids);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {
        if(!request.validate()){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        if(!unitRepository.findById(request.getUnitId()).isPresent()){
            throw new AdException(ErrorMessages.CAN_NOT_FIND_RECORD_ERROR);
        }
        if(!creativeRepository.findById(request.getCreativeId()).isPresent()){
            throw new AdException(ErrorMessages.CAN_NOT_FIND_RECORD_ERROR);
        }
        if(creativeUnitRepository.findByCreativeIdAndUnitId(request.getCreativeId(),request.getUnitId())!= null){
            throw new AdException(ErrorMessages.Resouce_ALREADY_EXSITED);
        }
        CreativeUnit toSave = new CreativeUnit(request.getCreativeId(),request.getUnitId());
        creativeUnitRepository.save(toSave);
        return new CreativeUnitResponse(toSave.getId());
    }

    private boolean isRelatedUnitesExist(List<Long> unitIds){
        if(CollectionUtils.isEmpty(unitIds)){
            return false;
        }
        return unitRepository.findAllById(unitIds).size()
                == new HashSet<>(unitIds).size();
    }
}
