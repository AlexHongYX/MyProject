package com.fehead.dao;

import com.fehead.bean.UserBean;
import com.fehead.bean.UserSelectBean;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/26
 * Description:
 */
public interface UserSelectMapper {

    List<UserSelectBean> selectUserMessage(UserSelectBean userSelectBean);

    List<Integer> selectClassroomsByUser(UserBean userBean);
}
