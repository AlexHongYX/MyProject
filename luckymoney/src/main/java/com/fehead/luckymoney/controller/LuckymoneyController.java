package com.fehead.luckymoney.controller;

import com.fehead.luckymoney.aspect.HttpAspect;
import com.fehead.luckymoney.domain.Luckymoney;
import com.fehead.luckymoney.domain.Result;
import com.fehead.luckymoney.properties.LuckymoneyRepository;
import com.fehead.luckymoney.service.LuckymoneyService;
import com.fehead.luckymoney.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by xiaoaxiao on 2019/5/20
 * Description:
 */
@RestController
public class LuckymoneyController {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private LuckymoneyRepository luckymoneyRepository;

    @Autowired
    private LuckymoneyService luckymoneyService;

    /**
     * 获取红包列表
     */
    @GetMapping("/luckymoneys")
    public List<Luckymoney> list(){
//        System.out.println("This is list from Luckmoney");
        logger.info("This is list from Luckymoney");
        return luckymoneyRepository.findAll();
    }

    /**
     * 创建红包（发红包）
     */
//    @PostMapping("/luckymoneys")
//    public Luckymoney create(@RequestParam("producer")String producer,
//                             @RequestParam("money") BigDecimal money){
//        Luckymoney luckymoney = new Luckymoney();
//        luckymoney.setProducer(producer);
//        luckymoney.setMoney(money);
//        return luckymoneyRepository.save(luckymoney);
//    }

    @PostMapping("/luckymoneys")
    public Result<Luckymoney> create(@Valid Luckymoney luckymoney, BindingResult bindingResult){
        //如果有错就返回错误对应的码和错误信息
        if(bindingResult.hasErrors()){
//            System.out.println(bindingResult.getFieldError().getDefaultMessage());
//            return null;
//            Result result = new Result();
//            result.setCode(0);
//            result.setMsg(bindingResult.getFieldError().getDefaultMessage());
//            return result;
            return ResultUtils.failure(0,bindingResult.getFieldError().getDefaultMessage());
        }
        //如果成功就返回成功码和成功信息和对应对象
        luckymoney.setMoney(luckymoney.getMoney());
        luckymoney.setProducer(luckymoney.getProducer());

//        Result<Luckymoney> result = new Result<>();
//        result.setCode(1);
//        result.setMsg("成功");
//        result.setData(luckymoneyRepository.save(luckymoney));
//        return result;
        return ResultUtils.success(luckymoneyRepository.save(luckymoney));
    }

    /**
     * 通过id查询红包
     */
    @GetMapping("/luckymoneys/{id}")
    public Luckymoney findById(@PathVariable("id")Integer id){
        //luckymoneyRepository.findById(id)返回的是Optional对象
        //使用orElse(null)->如果查到了直接返回LuckMoney，查不到就返回null
        return luckymoneyRepository.findById(id).orElse(null);
    }

    /**
     * 更新红包（领红包）
     */
    @PutMapping("/luckymoneys/{id}")
    public Luckymoney update(@PathVariable("id") Integer id,
                             @RequestParam("consumer") String consumer){
        //将已有的记录取出来，再给其id和consumer赋值

        //需要先判断是否为null（先查询）
        Optional<Luckymoney> optional = luckymoneyRepository.findById(id);
        //判断数据库中是否存在该id对应的记录，若有，则再进行更新
        if(optional.isPresent()){
            //获取数据库中该id对应的记录
            Luckymoney luckymoney = optional.get();
            //将已有的记录取出来，再给其id和consumer赋值
            luckymoney.setConsumer(consumer);
            return luckymoneyRepository.save(luckymoney);
        }

        return null;
    }

    @PostMapping("/luckymoneys/two")
    public void createTwo(){
        luckymoneyService.createTwo();
    }

    @GetMapping(value = "/luckymoneys/getMoney/{id}")
    public void getMoney(@PathVariable("id") Integer id) throws Exception{
            luckymoneyService.getMoney(id);
    }
}

