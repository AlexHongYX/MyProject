package com.fehead.service.Impl;

import com.fehead.dao.SelectClassroomsMapper;
import com.fehead.service.SelectClassroomsService;
import com.fehead.service.model.ClassroomModel;
import com.fehead.bean.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
@Service
public class SelectClassroomsServiceImpl implements SelectClassroomsService {

    @Autowired
    private SelectClassroomsMapper selectClassroomsMapper;

    @Override
    public List<ClassroomSelectBean> selectAllClassroomsFromLocation(String build, String buildnumber, int buildlevel) {

        if(build==null||buildnumber==null){
            return null;
        }

        List<ClassroomSelectBean> classroomSelectBeans = selectClassroomsMapper.selectAllClassroomsFromLocation(build,buildnumber,buildlevel);

        return classroomSelectBeans;
    }
}
