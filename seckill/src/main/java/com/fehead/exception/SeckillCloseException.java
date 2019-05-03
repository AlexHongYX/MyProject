package com.fehead.exception;

/**
 * Created by xiaoaxiao on 2019/5/3
 * Description:
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
