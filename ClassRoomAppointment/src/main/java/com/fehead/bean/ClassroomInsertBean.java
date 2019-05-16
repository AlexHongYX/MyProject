package com.fehead.bean;

/**
 * Created by xiaoaxiao on 2019/5/16
 * Description:
 */
public class ClassroomInsertBean {

    private String build;
    private String buildnumber;
    private int buildlevel;
    private Integer classroom;
    private int week;
    private int day;
    private int time;

    private int insert_count;

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getBuildnumber() {
        return buildnumber;
    }

    public void setBuildnumber(String buildnumber) {
        this.buildnumber = buildnumber;
    }

    public int getBuildlevel() {
        return buildlevel;
    }

    public void setBuildlevel(int buildlevel) {
        this.buildlevel = buildlevel;
    }

    public void setClassroom(Integer classroom) {
        this.classroom = classroom;
    }

    public Integer getClassroom() {
        return classroom;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getInsert_count() {
        return insert_count;
    }

    public void setInsert_count(int insert_count) {
        this.insert_count = insert_count;
    }
}
