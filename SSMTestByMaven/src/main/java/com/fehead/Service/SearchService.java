package com.fehead.Service;

import com.fehead.bean.Location;

import java.util.List;

public interface SearchService {

    public List<Integer> searchClassroomByMessage(String build,String buildnumber,int day,int time,int buildlevel,int start,int end);
}
