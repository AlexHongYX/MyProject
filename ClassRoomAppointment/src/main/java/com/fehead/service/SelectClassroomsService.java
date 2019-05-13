package com.fehead.service;

import com.fehead.bean.*;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
public interface SelectClassroomsService {

    List<ClassroomSelectBean> selectAllClassroomsFromLocation(String build, String buildnumber, int buildlevel);
}
