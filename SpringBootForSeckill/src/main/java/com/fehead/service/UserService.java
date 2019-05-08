package com.fehead.service;

import com.fehead.service.model.UserModel;

/**
 * Created by xiaoaxiao on 2019/5/8
 * Description:
 */
public interface UserService {

    //通过用户ID获取用户对象
    UserModel getUserById(Integer id);
}
