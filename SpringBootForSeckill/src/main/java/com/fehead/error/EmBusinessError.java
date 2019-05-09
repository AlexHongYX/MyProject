package com.fehead.error;

/**
 * Created by xiaoaxiao on 2019/5/9
 * Description:
 */
public enum EmBusinessError implements CommonError {

    //通用错误类型10001
    //错误码都是10001，后面的描述可以通过setErrMsg进行修改
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),

    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001,"用户不存在")
    ;

    private int errCode;
    private String errMsg;

    //外部不会调用这个构造器的
    // eg：当外部调用EmBusinessError.USER_NOT_EXIST时，在该类中自动调用该构造器。
    private EmBusinessError(int errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }}
