package com.fehead.luckymoney.controller;

import com.fehead.luckymoney.domain.LimitConfig;
import com.fehead.luckymoney.domain.Luckymoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiaoaxiao on 2019/5/20
 * Description:
 */
//@Controller
@RestController
public class HelloController {

//    @Value("${minMoney}")
//    private BigDecimal minMoney;
//
//    @Value("${description}")
//    private String description;
    @Autowired
    private LimitConfig limit;


//    @GetMapping("/hello")
//    @RequestMapping(value = "/hello",method = RequestMethod.GET)
//    @ResponseBody
//    @PostMapping("/hello")
    @RequestMapping(value = "/hello")
    public String say(@RequestParam(value = "id",required = false,defaultValue = "0")String id){
//        return "minMoney:"+minMoney+",description:"+description;
//        return "description:"+limit.getDescription();
//        Luckymoney luckymoney = new Luckymoney();
//        luckymoney.setProducer("come");
//        luckymoney.setConsumer("hello");
        return id+","+limit.getDescription();
    }

//    @RequestMapping(value = "/springboot",method = RequestMethod.GET)
//    public String spring(){
//        return "index";
//    }
}
