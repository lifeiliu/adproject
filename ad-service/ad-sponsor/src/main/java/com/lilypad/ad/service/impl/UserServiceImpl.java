package com.lilypad.ad.service.impl;

import com.lilypad.ad.constant.ErrorMessages;
import com.lilypad.ad.dao.AdUserRepository;
import com.lilypad.ad.entities.AdUser;
import com.lilypad.ad.exception.AdException;
import com.lilypad.ad.service.IUserService;
import com.lilypad.ad.utils.CommonUtils;
import com.lilypad.ad.vo.CreateUserRequest;
import com.lilypad.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    private AdUserRepository userRepository;
    @Autowired
    UserServiceImpl(AdUserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        if(!request.validate()){
            throw new AdException(ErrorMessages.REQUEST_PARAM_ERROR);
        }
        AdUser userToCheck = userRepository.findByUsername(request.getUserName());
        if(userToCheck != null){
            throw new AdException(ErrorMessages.SAME_NAME_ERROR);
        }
        AdUser userToSave = userRepository.save(new AdUser(
                request.getUserName(),
                CommonUtils.md5(request.getUserName())
        ));
        return new CreateUserResponse(
                userToSave.getId(), userToSave.getUsername(),
                userToSave.getToken(),userToSave.getCreateTime(),
                userToSave.getUpdateTime()
        );
    }
}
