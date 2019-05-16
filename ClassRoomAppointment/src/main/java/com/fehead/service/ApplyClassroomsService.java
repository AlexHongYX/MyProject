package com.fehead.service;

import com.fehead.error.BussinessException;
import com.fehead.service.model.ClassroomModel;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/14
 * Description:
 */
public interface ApplyClassroomsService {

    List<ClassroomModel> applyClassrooms(String build, String buildnumber, int buildlevel,
                                          int week, int day, int time,List<String> classrooms) throws BussinessException;
}
