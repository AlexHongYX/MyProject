package com.fehead;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
//@SpringBootApplication的作用于@EnableAutoConfiguration相类似，
// 都是可以将App这个类被spring托管，并且可以指定它为主启动类
//scanBasePackages：依次往下做扫描，springboot能够通过注解的方式自动发现@Service/@Controller...
@SpringBootApplication(scanBasePackages = {"com.fehead"})
//@RestController
//@MapperScan存放Dao的地址
@MapperScan("com.fehead.dao")
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        启动SpringBoot项目
        SpringApplication.run(App.class,args);
    }

}
