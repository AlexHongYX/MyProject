package com.fehead.exception;

/**
 * Created by xiaoaxiao on 2019/5/3
 * Description:秒杀相关业务异常，所以其他异常（重复异常/关闭异常）都是相关业务异常的子类
 */
public class SeckillException extends RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
