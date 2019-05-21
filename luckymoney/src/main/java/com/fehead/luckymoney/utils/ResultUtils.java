package com.fehead.luckymoney.utils;

import com.fehead.luckymoney.domain.Result;
import org.springframework.stereotype.Component;

/**
 * Created by xiaoaxiao on 2019/5/21
 * Description:
 */
public class ResultUtils {

    public static Result success(Object object){
        Result result = new Result();
        result.setCode(1);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result failure(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
