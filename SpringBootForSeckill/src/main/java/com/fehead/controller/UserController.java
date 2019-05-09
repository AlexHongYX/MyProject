package com.fehead.controller;

import com.fehead.controller.viewobject.UserVO;
import com.fehead.error.BussinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.service.UserService;
import com.fehead.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by xiaoaxiao on 2019/5/8
 * Description:
 */
@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    //为了获取request对象
    private HttpServletRequest httpServletRequest;

    /**
     * 通过构建Controller层的viewobject给前端返回数据（不好，太片面）
     * 所以需要构建给前端返回的标准返回格式state+object
     * @param id
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BussinessException {
        //调用service服务获取对应id的用户对象并返回给前端
        UserModel userModel = userService.getUserById(id);

        //若获取的对应用户信息不存在则抛出用户不存在异常
        if(userModel==null){
            userModel.setEncrptPassword("ahgah"); //验证未知异常随便设置的
//           throw new BussinessException(EmBusinessError.USER_NOT_EXIST);
        }
//        //将核心领域模型用户对象转化为可供UI使用的viewobject返回
//        return convertFromModel(userModel);

        //将核心领域模型用户对象转化为向前端返回的对象（领域模型部分——无密码之类的）
        UserVO userVO = convertFromModel(userModel);

        //返回通用对象
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }

    //用户获取otp短信接口——就是验证时给用户发的验证码
    @RequestMapping("/getotp")
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone")String telphone){
        //1、按照一定的规则生成OTP验证码
        Random random = new Random();
        /*
        * For Java：由于random只能取出的随机数为0-某个值，所以要想得到某指定区间之间的随机值需要这样
        * int randNumber = rand.nextInt(MAX - MIN + 1) + MIN; // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
        * */
        int randomInt = random.nextInt(899999)+100000;  //设置randomInt的值为100000-999999（六位数）
        //int->String
        String otpCode = String.valueOf(randomInt);

        //2、将OTP验证码同对应用户的手机号关联，使用HttpSession的方式绑定手机号与OPTCODE
        httpServletRequest.getSession().setAttribute(telphone,otpCode);

        //3、将OTP验证码通过短信通道发送给用户（省略）
        System.out.println("telphone = "+telphone+"&otpCode = "+otpCode);

        return CommonReturnType.create(null);
    }
}
