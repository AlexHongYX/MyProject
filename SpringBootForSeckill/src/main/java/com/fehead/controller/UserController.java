package com.fehead.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by xiaoaxiao on 2019/5/8
 * Description:
 */
@Controller("user")
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/get")
    public void getUser(@RequestParam(name = "id") Integer id){
        //调用service服务获取对应id的用户对象并返回给前端
    }
}
