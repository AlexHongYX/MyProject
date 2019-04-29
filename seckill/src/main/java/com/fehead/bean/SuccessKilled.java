package com.fehead.bean;

import java.util.Date;

public class SuccessKilled {

    private long seckill_id;
    private long user_phone;
    private int state;
    private Date create_time;

    //多对一关系
    private Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public long getSeckill_id() {
        return seckill_id;
    }

    public void setSeckill_id(long seckill_id) {
        this.seckill_id = seckill_id;
    }

    public long getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(long user_phone) {
        this.user_phone = user_phone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckill_id=" + seckill_id +
                ", user_phone=" + user_phone +
                ", state=" + state +
                ", create_time=" + create_time +
                '}';
    }
}
