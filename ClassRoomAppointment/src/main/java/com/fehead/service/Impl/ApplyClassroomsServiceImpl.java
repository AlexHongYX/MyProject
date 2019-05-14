package com.fehead.service.Impl;

import com.fehead.error.BussinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.service.ApplyClassroomsService;
import com.fehead.service.model.ClassroomModel;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/14
 * Description:
 */
public class ApplyClassroomsServiceImpl implements ApplyClassroomsService {

    @Override
    public List<ClassroomModel> applyClassrooms(String build, String buildnumber, int buildlevel, int week, int day, int time, List<String> classrooms) throws BussinessException {
        if(build==null||buildnumber==null){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }



        return null;
    }
}
