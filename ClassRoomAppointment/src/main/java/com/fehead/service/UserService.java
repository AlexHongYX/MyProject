package com.fehead.service;

import com.fehead.service.model.UserModel;

/**
 * Created by xiaoaxiao on 2019/5/23
 * Description:
 */
public interface UserService {

    UserModel insertUser(String organization,String name,String telphone);
}
