package com.fehead.service.Impl;

import com.alibaba.druid.util.StringUtils;
import com.fehead.dao.UserInfoMapper;
import com.fehead.dao.UserPasswordMapper;
import com.fehead.dataobject.UserInfo;
import com.fehead.dataobject.UserPassword;
import com.fehead.error.BussinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.service.UserService;
import com.fehead.service.model.UserModel;
import com.fehead.validator.ValidationResult;
import com.fehead.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xiaoaxiao on 2019/5/8
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    //使用validation验证方式
    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        //调用userInfoMapper获取对应的用户dataobject
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        UserPassword userPassword = userPasswordMapper.selectByPrimaryKey(id);

        return convertFromDataObject(userInfo,userPassword);
    }

    @Override
    //因为是分别插入两张表，所以需要加上事务
    @Transactional
    public void register(UserModel userModel) throws BussinessException{
        if(userModel==null){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //使用StringUtils.isEmpty()
        /*不能为null+不能为空
        * public static boolean isEmpty(CharSequence value) {
                return value == null || value.length() == 0;
            }
        * */
        //只需要判断这几个就行了，因为其他的都是Controller层设置的（后台设置）
//        if(StringUtils.isEmpty(userModel.getName())
//            ||userModel.getGender()==null
//            ||userModel.getAge()==null
//            ||StringUtils.isEmpty(userModel.getTelphone())){
//            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }
        //使用validation进行验证，需要Autowired validationResult，将需要校验的model直接放进去，但对应的model需要用注解标注其属性
        ValidationResult validationResult = validator.validate(userModel);
        if(validationResult.isHasErrors()){
            //将map中的信息都抛出去
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,validationResult.getErrMsg());
        }

        //实现model->dataObject方法
        //使用insertSelective，而不是insert——>insertSelective会对传入的数据先进行判空，尽量避免使用null
        UserInfo userInfo = convertUserInfoFromModel(userModel);
        //防重校验：防止手机号重复注册，已经将数据库中的telphone设置为唯一索引
        try {
            userInfoMapper.insertSelective(userInfo);
        }catch (DuplicateKeyException ex){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号重复注册");
        }

        //设置userModel的id为userInfo的id
        userModel.setId(userInfo.getId());

        UserPassword userPassword = convertUserPasswordFromModel(userModel);
        userPasswordMapper.insertSelective(userPassword);

        return;
    }

    @Override
    public UserModel validateLogin(String telphone, String encrptPassword) throws BussinessException {

        //通过用户的手机获取用户信息，需要在UserInfoMapper中加一个select标签
        UserInfo userInfo = userInfoMapper.selectByTelphone(telphone);
        if(userInfo==null){
            throw new BussinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        //通过user_id获取用户密码信息，需要在UserPasswordMapper中加一个select标签
        UserPassword userPassword = userPasswordMapper.selectByUserId(userInfo.getId());
        UserModel userModel = convertFromDataObject(userInfo,userPassword);

        //比对用户信息内加密的密码是否和传输进来的密码相匹配
        if(!StringUtils.equals(encrptPassword,userModel.getEncrptPassword())){
            throw new BussinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;
    }

    private UserPassword convertUserPasswordFromModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setEncrptPassword(userModel.getEncrptPassword());
        //通过设置user_id为userModel的id——外键关联
        userPassword.setUserId(userModel.getId());
        return userPassword;
    }

    private UserInfo convertUserInfoFromModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userModel,userInfo);
        return userInfo;
    }

    private UserModel convertFromDataObject(UserInfo userInfo, UserPassword userPassword){
        if(userInfo==null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userInfo,userModel);

        if(userPassword!=null){
            userModel.setEncrptPassword(userPassword.getEncrptPassword());
        }
        return userModel;
    }
}
