package com.lilypad.ad.controllers;

import com.alibaba.fastjson.JSON;
import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.service.IUserService;
import com.lilypad.ad.vo.CreateUserRequest;
import com.lilypad.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("")
    public CreateUserResponse createUser(
            @RequestBody CreateUserRequest request) throws AdException{
        log.info("ad-sponser: create user ->{}",
                JSON.toJSONString(request));
        return userService.createUser(request);
    }

}
