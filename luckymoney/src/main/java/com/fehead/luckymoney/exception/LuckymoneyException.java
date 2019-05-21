package com.fehead.luckymoney.exception;

import com.fehead.luckymoney.enums.ResultEnum;

/**
 * Created by xiaoaxiao on 2019/5/21
 * Description:
 */
public class LuckymoneyException extends RuntimeException{
    private Integer code;

    public LuckymoneyException(Integer code,String message){
        //super相当于调用父类的构造器
        super(message);
        this.code = code;
    }

    public LuckymoneyException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
