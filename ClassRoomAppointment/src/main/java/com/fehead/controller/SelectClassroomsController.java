package com.fehead.controller;

import com.fehead.response.CommonReturnType;
import com.fehead.bean.*;
import com.fehead.service.SelectClassroomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
@Controller("select")
@RequestMapping("/select")
//解决跨域问题
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class SelectClassroomsController extends BaseController{

    @Autowired
    private SelectClassroomsService selectClassroomsService;

    @RequestMapping("/allClassroomsFromLevel")
    @ResponseBody
    public CommonReturnType selectAllClassroomsFromLocation(@RequestParam(name = "build")String build,
                                                            @RequestParam(name = "buildnumber")String buildnumber,
                                                            @RequestParam(name = "buildlevel")int buildlevel){
        if(build==null||buildnumber==null){
            return null;
        }
        List<ClassroomSelectBean> classroomSelectBeans = selectClassroomsService.selectAllClassroomsFromLocation(build,buildnumber,buildlevel);

        return CommonReturnType.create(classroomSelectBeans);
    }

}
