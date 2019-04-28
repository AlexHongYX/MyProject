package com.fehead.Dao;

import com.fehead.bean.StraightClassroomBean;

import java.util.List;

public interface StraightClassroomDao {

    public List<String> findStraightClassroom(StraightClassroomBean straightClassroomBean);
}
