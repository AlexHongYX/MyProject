package com.fehead.Service;


import java.util.Map;

public interface SearchService {

    public Map<Integer,String> searchClassroomByMessage(String build, String buildnumber, int buildlevel, int week, int day, int time);

    public String getBuild(String build,String buildnumber);

    public String getDay(int time,int day);

    public String[] getWeeks(int week);
}
