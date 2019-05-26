package com.fehead.service.Impl;

import com.fehead.bean.UserBean;
import com.fehead.bean.UserSelectBean;
import com.fehead.dao.UserMapper;
import com.fehead.dao.UserSelectMapper;
import com.fehead.service.UserService;
import com.fehead.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/23
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSelectMapper userSelectMapper;

    @Override
    public void insertUser(String organization, String name, String telphone) {

        UserModel userModel = new UserModel();
        userModel.setOrganization(organization);
        userModel.setName(name);
        userModel.setTelphone(telphone);

        Integer result = userMapper.selectUser(userModel);

        if(result==null){
            userMapper.insertUser(userModel);
        }

    }

    @Override
    public List<UserModel> getUser(String organization, String name, String telphone, String description) {

        UserBean userBean = new UserBean();
        userBean.setOrganization(organization);
        userBean.setName(name);
        userBean.setTelphone(telphone);
        userBean.setDescription(description);

        List<Integer> classrooms = userSelectMapper.selectClassroomsByUser(userBean);

//        UserSelectBean userSelectBean = new UserSelectBean();
//        userSelectBean.setOrganization(organization);
//        userSelectBean.setName(name);
//        userSelectBean.setTelphone(telphone);
//        userSelectBean.setDescription(description);
//
//        List<UserSelectBean> userSelectBeans = userSelectMapper.selectUserMessage(userSelectBean);
//        List<UserModel> userModels = new ArrayList<>();
//
//        for(UserSelectBean userSelectBean1:userSelectBeans){
//            UserModel userModel = convertUserModelFromUserSelectBean(userSelectBean1);
//            userModels.add(userModel);
//        }
//
//
//        return userModels;
        return null;

    }

    private UserModel convertUserModelFromUserSelectBean(UserSelectBean userSelectBean){
        if(userSelectBean==null){
            return null;
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userSelectBean,userModel);

        return userModel;
    }
}
