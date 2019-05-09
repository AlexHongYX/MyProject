package com.fehead.error;

/**
 * Created by xiaoaxiao on 2019/5/9
 * Description:
 */
public interface CommonError {

    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}
