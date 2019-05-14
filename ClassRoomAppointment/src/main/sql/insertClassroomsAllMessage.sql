--创建插入所有表的存储过程
--一条完整的数据需要分别插入对应的表
--包括关系表（因为在查询时会用到）
DELIMITER $$ -- console ; 转换为 $$

--定义存储过程
CREATE PROCEDURE classroomInsert
  (IN v_build VARCHAR(50),IN v_buildnumber VARCHAR(5),IN v_buildlevel INT,IN v_day INT,
  IN v_time INT,IN v_week INT,IN v_classroom INT)
  BEGIN
     declare v_build_id INT;
     declare v_build_buildnumber_id INT;
     declare v_buildlevel_classroom_id INT;
     declare v_classroom_id INT;
     declare v_week_day_id INT;
     declare v_time_id INT;
     START TRANSACTION;

    INSERT into build(build) VALUES(v_build);
    SELECT build_id into v_build_id from build where build=v_build;

    INSERT into build_buildnumber(build_id,buildnumber) VALUES (v_build_id,v_buildnumber);
    SELECT build_buildnumber_id into v_build_buildnumber_id from build_buildnumber where build_id=v_build_id and buildnumber=v_buildnumber;

    INSERT into buildlevel_classroom (buildlevel,classroom) VALUES (v_buildlevel,v_classroom);
    SELECT buildlevel_classroom_id into v_buildlevel_classroom_id from buildlevel_classroom where buildlevel=v_buildlevel and classroom=v_classroom;

    INSERT into classroom_all(build_buildnumber_id,buildlevel_classroom_id) VALUES (v_build_buildnumber_id,v_buildlevel_classroom_id);
    SELECT id into v_classroom_id from classroom_all where build_buildnumber_id=v_build_buildnumber_id and buildlevel_classroom_id=v_buildlevel_classroom_id;

    INSERT into week_day(week,day) VALUES (v_week,v_day);
    SELECT id into v_week_day_id from week_day where week=v_week and day=v_day;

    INSERT into week_day_time(week_day_id,time) VALUES (v_week_day_id,v_time);
    SELECT id into v_time_id from week_day_time where week_day_id=v_week_day_id and time=v_time;

    INSERT into query(time_id,classroom_id) VALUES (v_time_id,v_classroom_id);

  END;
$$
--存储过程定义结束

DELIMITER ; -- 还原为;

