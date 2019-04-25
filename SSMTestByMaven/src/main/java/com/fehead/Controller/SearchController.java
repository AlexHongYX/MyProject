package com.fehead.Controller;

import com.fehead.Service.SearchService;
import com.fehead.bean.Build;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "action",method = RequestMethod.GET)
    public void searchClassroomByMessage(@RequestBody Map<String,String> map){
        String build = map.get("build");
        String buildnumber = map.get("buildnumber");
        int day = Integer.parseInt(map.get("day"));
        int time = Integer.parseInt(map.get("time"));
        int buildlevel = Integer.parseInt(map.get("buildlevel"));
        int start = Integer.parseInt(map.get("start"));
        int end = Integer.parseInt(map.get("end"));
        System.out.println("build:"+build+" buildnumber:"+buildnumber+" day:"+day
            +" time:"+time+" buildlevel:"+buildlevel+" start:"+start+" end:"+end);
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
