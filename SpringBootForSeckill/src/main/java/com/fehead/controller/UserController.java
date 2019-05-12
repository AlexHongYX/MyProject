package com.fehead.controller;

import com.alibaba.druid.util.StringUtils;
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
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by xiaoaxiao on 2019/5/8
 * Description:
 */
@Controller("user")
@RequestMapping("/user")
//实现跨域请求，只写一个@CrossOrigin不能实现ajax请求的跨域（不能实现session共享）
//DEFAULT_ALLOWED_HEADERS="*"：允许跨域传输所有的header参数，将用于使用token放入header域，做session共享的跨域请求
//DEFAULT_ALLOW_CREDENTIALS=true：需要配合前端设置xhrFields（ajax中的属性）授信后使得跨域session共享
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
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
    //必须映射到http的post请求才可以生效
    @RequestMapping(value = "/getotp",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORWARD})
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

    @RequestMapping(value = "/register",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORWARD})
    @ResponseBody
    //用户注册接口
    public CommonReturnType register(@RequestParam(name = "telphone")String telphone,
                                     @RequestParam(name = "otpCode")String otpCode,
                                     @RequestParam(name = "name")String name,
                                     @RequestParam(name = "gender")Integer gender,
                                     @RequestParam(name = "age")Integer age,
                                     @RequestParam(name = "password")String password) throws BussinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        //验证手机号和对应的otpCode相符合
        //先将服务器中的session中的otpCode取出来，由otp绑定telphone时生成
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        //若otpCode和inSessionOtpCode不相等则抛出错误，若相等就进入用户注册流程
        if(!StringUtils.equals(otpCode,inSessionOtpCode)){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不正确");
        }
        //用户的注册流程
        UserModel userModel = new UserModel();
        userModel.setTelphone(telphone);
        userModel.setName(name);
        //将Integer强转为Byte
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setAge(age);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(this.EncodeByMd5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    //自定义创建MD5加密方式
    public String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        String newstr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    //用户登录接口
    @RequestMapping(value = "/login",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORWARD})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone")String telphone,
                                  @RequestParam(name = "password")String password) throws BussinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        //校验电话和密码是否为null或空字符串
        if(StringUtils.isEmpty(telphone)||StringUtils.isEmpty(password)){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //用户登录服务，用来校验用户登录是否合法
        UserModel userModel = userService.validateLogin(telphone,this.EncodeByMd5(password));

        //将登录凭证加入到用户登录成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);

        return CommonReturnType.create(null);
    }
}
