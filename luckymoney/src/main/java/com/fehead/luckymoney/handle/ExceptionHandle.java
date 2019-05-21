package com.fehead.luckymoney.handle;

import com.fehead.luckymoney.domain.Result;
import com.fehead.luckymoney.exception.LuckymoneyException;
import com.fehead.luckymoney.utils.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiaoaxiao on 2019/5/21
 * Description: 这个类通过各种注解结合起来，相当于是在拦截该项目所有的Exception（因为@ExceptionHandler(value = Exception.class)）
 *              对这些Exception进行相应的处理：看是否为自定义异常，是就自定义处理，不是就抛出未知异常
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof LuckymoneyException){
            LuckymoneyException luckymoneyException = (LuckymoneyException) e;
            return ResultUtils.failure(luckymoneyException.getCode(),luckymoneyException.getMessage());
        }
        return ResultUtils.failure(-1,"未知错误");
    }
}
