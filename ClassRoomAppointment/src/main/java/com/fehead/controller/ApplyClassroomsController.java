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

    @RequestMapping(value = "/applyClassrooms",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public CommonReturnType applyClassrooms(@RequestParam("build") String build,
                                            @RequestParam("buildnumber") String buildnumber,
                                            @RequestParam("buildlevel") int buildlevel,
                                            @RequestParam("week") int week,
                                            @RequestParam("day") int day,
                                            @RequestParam("time") int time,
                                            @RequestParam("classroom") String[] classrooms) throws BussinessException {

//        Integer[] arr = new Integer[100];
        System.out.println("build"+build+",buildnumber"+buildnumber+",buildlevel"+buildlevel+",week"+week+",day"+day+",time"+time+",classroom"+classrooms[0]);

        if(classrooms.length==1){
            classrooms[classrooms.length-1] = classrooms[0].substring(2,4);
        }else {
            for(int i=0;i<classrooms.length;i++){
                if(i==0){
                    classrooms[i] = classrooms[i].substring(2,4);
                }else{
                    classrooms[i] = classrooms[i].substring(1,3);
                }
//            arr[i] = Integer.valueOf(classrooms[i]);
            }
        }


        System.out.println("build"+build+",buildnumber"+buildnumber+",buildlevel"+buildlevel+",week"+week+",day"+day+",time"+time+",classroom[0]:"+classrooms[0]);


        long startTime = System.currentTimeMillis();//获取开始时间

        List<ClassroomModel> classroomModels =  applyClassroomsService.applyClassrooms(build,buildnumber,buildlevel,week,day,time,classrooms);

        long endTime = System.currentTimeMillis();//获取结束时间
        System.out.println("startTime="+startTime+";endTime="+endTime+";runtime:"+(endTime-startTime)+"ms");

        return CommonReturnType.create(classroomModels);
    }

//    public static void main(String[] args){
//        String[] s = {"10","08"};
//        System.out.println("s[0]:"+s[0]+",s[1]:"+s[1]);
//    }

}
