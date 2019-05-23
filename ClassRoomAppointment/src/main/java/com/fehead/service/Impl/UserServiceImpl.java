package com.fehead.service.Impl;

import com.fehead.dao.UserMapper;
import com.fehead.service.UserService;
import com.fehead.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoaxiao on 2019/5/23
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String insertUser(String organization, String name, String telphone, String description) {

        UserModel userModel = new UserModel();
        userModel.setOrganization(organization);
        userModel.setName(name);
        userModel.setTelphone(telphone);
        userModel.setOrganization(organization);

        userMapper.insertUser(userModel);

        return "登录成功";
    }
}
