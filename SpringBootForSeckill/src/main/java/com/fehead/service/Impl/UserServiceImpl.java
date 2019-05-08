package com.fehead.service.Impl;

import com.fehead.dao.UserInfoMapper;
import com.fehead.dao.UserPasswordMapper;
import com.fehead.dataobject.UserInfo;
import com.fehead.dataobject.UserPassword;
import com.fehead.service.UserService;
import com.fehead.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoaxiao on 2019/5/8
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    @Override
    public UserModel getUserById(Integer id) {
        //调用userInfoMapper获取对应的用户dataobject
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        return null;
    }

    private UserModel convertFromDataObject(UserInfo userInfo, UserPassword userPassword){
        if(userInfo==null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userInfo,userModel);

        if(userPassword!=null){
            userModel.setEncrptPassword(userPassword.getEncrptPassword());
        }
        return userModel;
    }
}
