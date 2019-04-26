package com.fehead.Service.impl;

import com.fehead.Dao.BuildDao;
import com.fehead.Dao.DayTimeDao;
import com.fehead.Dao.StartEndDao;
import com.fehead.Service.SearchService;
import com.fehead.bean.Build;
import com.fehead.bean.DayTime;
import com.fehead.bean.StartEnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private BuildDao buildDao;

    @Autowired
    private DayTimeDao dayTimeDao;

    @Autowired
    private StartEndDao startEndDao;

    public Map<Integer,String> searchClassroomByMessage(String build, String buildnumber, int buildlevel, int week, int day, int time){
        Map<Integer,String> map = new HashMap<Integer, String>();
        return null;
    }

    public String getBuild(String build,String buildnumber){
        Build build1 = new Build();
        build1.setBuild(build);
        build1.setBuildnumber(buildnumber);
        String build_buildnumber_id = buildDao.getBuild(build1);
        return build_buildnumber_id;
    }

    public String getDay(int day,int time){
        DayTime dayTime = new DayTime();
        dayTime.setDay(day);
        dayTime.setTime(time);
        String day_time_id = dayTimeDao.getDay(dayTime);
        return day_time_id;
    }

    public String[] getWeeks(int week){
        String[] weeks_id = startEndDao.getWeeks(week);
        return weeks_id;
    }
}
