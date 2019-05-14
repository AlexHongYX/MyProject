package com.fehead.dao;

/**
 * Created by xiaoaxiao on 2019/5/14
 * Description:
 */
public interface ApplyClassroomsMapper {

    Integer insertBuild(String build);

    Integer insertBuildBuildnumber(Integer buildId,String buildNumber);

    Integer insertBuildlevelClassroom(Integer buildlevel,Integer classroom);

}
