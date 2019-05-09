package com.fehead.error;

/**
 * Created by xiaoaxiao on 2019/5/9
 * Description:设计模式——包装器业务异常类实现
 */
public class BussinessException extends Exception implements CommonError {

    private CommonError commonError;

    //直接接收EmBusinessError的传参用于构造业务异常
    public BussinessException(CommonError commonError){
        //对应Exception自身会有一些初始化的机制
        super();
        this.commonError = commonError;
    }

    //接收自定义errMsg的方式构造业务异常
    public BussinessException(CommonError commonError,String errMsg){
        super();
        this.commonError=commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
