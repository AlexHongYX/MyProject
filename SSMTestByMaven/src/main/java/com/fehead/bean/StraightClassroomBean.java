package com.fehead.bean;

public class StraightClassroomBean {

    private String build;
    private String buildnumber;
    private int buildlevel;
    private int week;
    private int day;
    private int time;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setBuildlevel(int buildlevel) {
        this.buildlevel = buildlevel;
    }

    public int getBuildlevel() {
        return buildlevel;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setBuildnumber(String buildnumber) {
        this.buildnumber = buildnumber;
    }

    public String getBuildnumber() {
        return buildnumber;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getBuild() {
        return build;
    }

}
