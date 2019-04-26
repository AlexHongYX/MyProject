package com.fehead.Controller;

import com.fehead.Service.SearchService;
import com.fehead.bean.Build;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    //获取请求参数和@RequestBody都可以用@RequestParam
    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String searchClassroomByMessage(@RequestParam("build") String build,@RequestParam("buildnumber") String buildnumber,
                                           @RequestParam("buildlevel") int buildlevel,@RequestParam("week") int week,
                                           @RequestParam("day") int day,@RequestParam("time") int time){
        System.out.println("build:"+build+" buildnumber:"+buildnumber+" buildlevel:"+buildlevel+" week:"+week+" day:"+day
            +" time:"+time);
        String build_buildnumber_id = searchService.getBuild(build,buildnumber);
        System.out.println(build_buildnumber_id);
        String day_time_id = searchService.getDay(day,time);
        System.out.println(day_time_id);
        String[] weeks_id = searchService.getWeeks(week);
        for(int i=0;i<weeks_id.length;i++){
            System.out.println(weeks_id[i]);
        }
        return "search";
    }

    @RequestMapping("index")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("hello")
    public String getHello(){
        return "hello";
    }

    @RequestMapping("main")
    public String getMain(){
        return "main";
    }
}
