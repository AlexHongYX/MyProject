package com.fehead.controller;

import com.fehead.response.CommonReturnType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping("/applyClassrooms")
    public CommonReturnType applyClassrooms(@RequestParam("build") String build,
                                            @RequestParam("buildnumber") String buildnumber,
                                            @RequestParam("buildlevel") int buildlevel,
                                            @RequestParam("week") int week,
                                            @RequestParam("day") int day,
                                            @RequestParam("time") int time,
                                            @RequestParam("classroom") List<String> classrooms){
        return null;
    }

}
