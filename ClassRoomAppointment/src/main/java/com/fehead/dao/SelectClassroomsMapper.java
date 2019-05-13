package com.fehead.dao;

import java.util.List;
import com.fehead.bean.*;
import com.fehead.service.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
public interface SelectClassroomsMapper {

    List<ClassroomSelectBean> selectAllClassroomsFromLocation(@Param("build") String build, @Param("buildumber") String buildumber, @Param("buildlevel") int buildlevel);
}
