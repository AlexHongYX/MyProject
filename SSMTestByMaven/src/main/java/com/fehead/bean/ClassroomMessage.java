package com.fehead.bean;

public class ClassroomMessage {

    private int id;
    private int week_start_end_id;
    private int day_time_id;
    private int build_buildnumber_id;
    private int buildlevel_classroom;

    public void setBuild_buildnumber_id(int build_buildnumber_id) {
        this.build_buildnumber_id = build_buildnumber_id;
    }

    public int getBuild_buildnumber_id() {
        return build_buildnumber_id;
    }

    public void setBuildlevel_classroom(int buildlevel_classroom) {
        this.buildlevel_classroom = buildlevel_classroom;
    }

    public int getBuildlevel_classroom() {
        return buildlevel_classroom;
    }

    public void setDay_time_id(int day_time_id) {
        this.day_time_id = day_time_id;
    }

    public int getDay_time_id() {
        return day_time_id;
    }

    public void setWeek_start_end_id(int week_start_end_id) {
        this.week_start_end_id = week_start_end_id;
    }

    public int getWeek_start_end_id() {
        return week_start_end_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
