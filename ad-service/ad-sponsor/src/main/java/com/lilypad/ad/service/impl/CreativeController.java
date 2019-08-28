package com.lilypad.ad.service.impl;

import com.alibaba.fastjson.JSON;
import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.service.ICreativeService;
import com.lilypad.ad.vo.CreativeRequest;
import com.lilypad.ad.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/creatives")
public class CreativeController {
    @Autowired
    private ICreativeService creativeService;

    @PostMapping("")
    public CreativeResponse createCreative(
            @RequestBody CreativeRequest request) throws AdException{
        log.info("ad-sponsor: create user ->{}",
                JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
