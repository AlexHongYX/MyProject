package com.fehead.entity;

public class ReturnJson {

    private String classroom;
    private String usage;

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getUsage() {
        return usage;
    }

    @Override
    public String toString() {
        return "ReturnJson{" +
                "classroom='" + classroom + '\'' +
                ", usage='" + usage + '\'' +
                '}';
    }
}
