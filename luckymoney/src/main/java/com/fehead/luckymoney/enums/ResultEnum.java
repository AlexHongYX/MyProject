package com.fehead.luckymoney.enums;

import org.omg.CORBA.UNKNOWN;

/**
 * Created by xiaoaxiao on 2019/5/21
 * Description:
 */
public enum ResultEnum {
    UNKNOWN_ERROR(-1,"未知错误"),
    SUCCESS(1,"成功"),
    UNDER_TEN(100,"这么点钱能够？"),
    UNDER_TWENTY(101,"就加10块钱？"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
