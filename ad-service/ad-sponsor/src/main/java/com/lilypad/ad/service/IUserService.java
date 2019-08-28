package com.lilypad.ad.service;

import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.vo.CreateUserRequest;
import com.lilypad.ad.vo.CreateUserResponse;

public interface IUserService {
    CreateUserResponse createUser(CreateUserRequest request)
            throws AdException;

}
