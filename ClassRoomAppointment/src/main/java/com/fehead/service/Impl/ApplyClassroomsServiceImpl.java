package com.fehead.service.Impl;

import com.fehead.bean.ClassroomSelectBean;
import com.fehead.dao.ApplyClassroomsMapper;
import com.fehead.error.BussinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.service.ApplyClassroomsService;
import com.fehead.service.model.ClassroomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/14
 * Description:
 */
@Service
public class ApplyClassroomsServiceImpl implements ApplyClassroomsService {

    @Autowired
    private ApplyClassroomsMapper applyClassroomsMapper;

    @Override
    public List<ClassroomModel> applyClassrooms(String build, String buildnumber, int buildlevel, int week, int day, int time, List<String> classrooms) throws BussinessException {
        if(build==null||buildnumber==null){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        for (String classroom:classrooms){
            applyClassroomsMapper.insertAllTables(build,buildnumber,buildlevel,day,time,week,Integer.valueOf(classroom));
        }

        return null;
    }
}
