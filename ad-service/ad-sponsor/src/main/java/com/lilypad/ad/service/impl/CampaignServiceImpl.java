package com.lilypad.ad.service.impl;

import com.lilypad.ad.constant.CommonStatus;
import com.lilypad.ad.constant.ErrorMessages;
import com.lilypad.ad.dao.AdCampaignRepository;
import com.lilypad.ad.dao.AdUserRepository;
import com.lilypad.ad.entities.AdCampaign;
import com.lilypad.ad.entities.AdUser;
import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.service.IAdCampaignService;
import com.lilypad.ad.utils.CommonUtils;
import com.lilypad.ad.vo.AdCampaignGetRequest;
import com.lilypad.ad.vo.AdCampaignRequest;
import com.lilypad.ad.vo.AdCampaignResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements IAdCampaignService {
    @Autowired
    private AdUserRepository userRepo;
    @Autowired
    private AdCampaignRepository campaignRepo;

    @Override
    @Transactional
    public AdCampaignResponse createAdCampain(AdCampaignRequest request) throws AdException {
        if(!request.createValidate()){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        Optional<AdUser> user = userRepo.findById(request.getUserId());
        if(!user.isPresent()){
            throw new AdException(ErrorMessages.CAN_NOT_FIND_RECORD_ERROR);
        }
        AdCampaign campaignToCheck = campaignRepo.findByUserIdAndAdCampaignName(
                            request.getUserId(),request.getCampaignName());
        if(campaignToCheck != null){
            throw new AdException(ErrorMessages.SAME_CAMPAIGN_NAME_ERROR);
        }
        AdCampaign savedCampaign = campaignRepo.save(new AdCampaign(
                request.getUserId(),request.getCampaignName(),
                CommonUtils.parseStringDate(request.getStartDate()),
                CommonUtils.parseStringDate(request.getEndDate())));

        return new AdCampaignResponse(savedCampaign.getId(),savedCampaign.getCampaignName());
    }

    @Override
    @Transactional
    public List<AdCampaign> getAdCampaigns(AdCampaignGetRequest request) throws AdException {
        if(!request.getValidate()){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        return campaignRepo.findByIdInAndUserId(request.getIds(),request.getUserId());
    }

    @Override
    @Transactional
    public AdCampaignResponse updateAdCampain(AdCampaignRequest request) throws AdException {
        if(!request.updateValidate()){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        AdCampaign campaignToUpdate = campaignRepo.findByUserIdAndId(request.getId(),request.getUserId());
        if(campaignToUpdate == null){
            throw new AdException(ErrorMessages.CAN_NOT_FIND_RECORD_ERROR);
        }
        if(request.getStartDate() != null){
            campaignToUpdate.setStartDate(CommonUtils.parseStringDate(request.getStartDate()));
        }
        if(request.getEndDate() != null){
            campaignToUpdate.setEndDate(CommonUtils.parseStringDate(request.getEndDate()));
        }
        campaignToUpdate.setUpdateTime(new Date());
        AdCampaign savedCampaign = campaignRepo.save(campaignToUpdate);
        return new AdCampaignResponse(savedCampaign.getId(), savedCampaign.getCampaignName());

    }

    @Override
    @Transactional
    public void deleteAdCampain(AdCampaignRequest request) throws AdException {
        if (!request.deleteValidate()){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        AdCampaign campaignToDel = campaignRepo.findByUserIdAndId(request.getId(), request.getUserId());
        if(campaignToDel == null){
            throw new AdException(ErrorMessages.CAN_NOT_FIND_RECORD_ERROR);
        }
        campaignToDel.setCampaignStatus(CommonStatus.INVALID.getStatus());
        campaignToDel.setUpdateTime(new Date());
        campaignRepo.save(campaignToDel);
    }
}
