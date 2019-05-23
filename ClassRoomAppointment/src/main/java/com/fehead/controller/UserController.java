package com.fehead.controller;

import com.fehead.response.CommonReturnType;
import com.fehead.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaoaxiao on 2019/5/23
 * Description:
 */
@RestController
@RequestMapping(value = "/user")
//解决跨域问题
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    /**
     * 前端发送请求，在cookie中查找是否有已登录的社团信息，如果没有就跳到社团信息输入页面，如果有就直接跳到选择具体位置和时间的页面
     */
//    public CommonReturnType isUserExist() {
//
//        Cookie[] cookies = request.getCookies();
//
//        if(cookies!=null){
//
//        }
//    }

    /**
     * 将user信息放在数据库和Cookie中
     */
    public CommonReturnType setUserAndCookie(@RequestParam("organization") String organization,
                                 @RequestParam("name") String name,
                                 @RequestParam("telphone")String telphone,
                                 @RequestParam("description")String description) {

            String result = userService.insertUser(organization,name,telphone,description);

            return CommonReturnType.create(result);
    }

}
