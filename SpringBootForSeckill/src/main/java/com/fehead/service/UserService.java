package com.fehead.service;

import com.fehead.error.BussinessException;
import com.fehead.service.model.UserModel;

/**
 * Created by xiaoaxiao on 2019/5/8
 * Description:
 */
public interface UserService {

    //通过用户ID获取用户对象
    UserModel getUserById(Integer id);

    //用户注册请求
    void register(UserModel userModel) throws BussinessException;

    //校验用户登录是否合法
    //telphone：用户注册手机  password：用户加密后的密码
    UserModel validateLogin(String telphone,String encrptPassword) throws BussinessException;
}
