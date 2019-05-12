package com.fehead.controller;

import com.fehead.error.BussinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.service.OrderService;
import com.fehead.service.model.OrderModel;
import com.fehead.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaoaxiao on 2019/5/11
 * Description:
 */
@Controller("order")
@RequestMapping("/order")
//实现跨域请求，只写一个@CrossOrigin不能实现ajax请求的跨域（不能实现session共享）
//DEFAULT_ALLOWED_HEADERS="*"：允许跨域传输所有的header参数，将用于使用token放入header域，做session共享的跨域请求
//DEFAULT_ALLOW_CREDENTIALS=true：需要配合前端设置xhrFields（ajax中的属性）授信后使得跨域session共享
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //封装下单请求
    @RequestMapping(value = "/createorder",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORWARD})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId")Integer itemId,
                                        @RequestParam(name = "amount")Integer amount) throws BussinessException {

        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin==null||!isLogin.booleanValue()){
            throw new BussinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        //获取用户登录信息
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        //通过orderService返回orderModel
        OrderModel orderModel = orderService.createOrder(userModel.getId(),itemId,amount);

        return CommonReturnType.create(null);
    }
}
