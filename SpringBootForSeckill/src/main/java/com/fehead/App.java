package com.fehead;

import com.fehead.dao.UserInfoMapper;
import com.fehead.dataobject.UserInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
//将App的启动类当成一个自动化可以支持配置的bean，
//并且能够开启整个工程基于SpringBoot的自动配置(几乎所有的内容都可以自动加载)
//用SpringApplication.run启动对应的SpringBoot项目
//@EnableAutoConfiguration

//@SpringBootApplication的作用于@EnableAutoConfiguration相类似，
// 都是可以将App这个类被spring托管，并且可以指定它为主启动类
    //scanBasePackages：依次往下做扫描，springboot能够通过注解的方式自动发现@Service/@Controller...
@SpringBootApplication(scanBasePackages = {"com.fehead"})
//@RestController
//@MapperScan存放Dao的地址
@MapperScan("com.fehead.dao")
public class App {

//    @Autowired
//    private UserInfoMapper userInfoMapper;
    //IDEA会找接口对应的实现类，但MyBatis代理了dao层接口从而生成实现类（通过.xml配置的方式）
    // 并没有实际生成，所以IDEA扫描不到对应实现类就报错了，但没有任何影响

//    @RequestMapping("/")
//    public String home(){
//        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);
//        if(userInfo==null){
//            return "用户对象不存在";
//        }else {
//            return userInfo.getName();
//        }
//
//    }

    public static void main( String[] args ){
        System.out.println( "Hello World!" );
        //启动SpringBoot项目
        SpringApplication.run(App.class,args);
    }
}
