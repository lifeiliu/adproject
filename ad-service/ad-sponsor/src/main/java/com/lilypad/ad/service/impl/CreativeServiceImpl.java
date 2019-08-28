package com.lilypad.ad.service.impl;

import com.lilypad.ad.dao.CreativeRepository;
import com.lilypad.ad.entities.Creative;
import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.service.ICreativeService;
import com.lilypad.ad.vo.CreativeRequest;
import com.lilypad.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreativeServiceImpl implements ICreativeService {
    @Autowired
    CreativeRepository creativeRepository;

    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {
        Creative creative = request.convertToEntity();
        creativeRepository.save(creative);
        return new CreativeResponse(creative.getId(),creative.getName());
    }
}
