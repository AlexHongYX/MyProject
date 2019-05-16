package com.fehead.controller;

import com.fehead.error.BussinessException;
import com.fehead.response.CommonReturnType;
import com.fehead.service.ApplyClassroomsService;
import com.fehead.service.model.ClassroomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/14
 * Description:
 */
@Controller("apply")
@RequestMapping("/apply")
//解决跨域问题
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ApplyClassroomsController extends BaseController {

    @Autowired
    private ApplyClassroomsService applyClassroomsService;

    @RequestMapping("/applyClassrooms")
    @ResponseBody
    public CommonReturnType applyClassrooms(@RequestParam("build") String build,
                                            @RequestParam("buildnumber") String buildnumber,
                                            @RequestParam("buildlevel") int buildlevel,
                                            @RequestParam("week") int week,
                                            @RequestParam("day") int day,
                                            @RequestParam("time") int time,
                                            @RequestParam("classroom") List<String> classrooms) throws BussinessException {

        long startTime = System.currentTimeMillis();//获取开始时间

        List<ClassroomModel> classroomModels =  applyClassroomsService.applyClassrooms(build,buildnumber,buildlevel,week,day,time,classrooms);

        long endTime = System.currentTimeMillis();//获取结束时间
        System.out.println("startTime="+startTime+";endTime="+endTime+";runtime:"+(endTime-startTime)+"ms");

        return CommonReturnType.create(classroomModels);
    }

}
