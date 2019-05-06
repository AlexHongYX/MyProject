package com.fehead.web;

import com.fehead.bean.Seckill;
import com.fehead.dto.Exposer;
import com.fehead.dto.SeckillExecution;
import com.fehead.dto.SeckillResult;
import com.fehead.enums.SeckillStatEnum;
import com.fehead.exception.RepeatKillException;
import com.fehead.exception.SeckillCloseException;
import com.fehead.exception.SeckillException;
import com.fehead.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/4
 * Description:
 */
@Controller
@RequestMapping("/seckill")     //url:/模块/资源/{id}/细分
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        //获取列表页
        List<Seckill> list = seckillService.getSeckillList();

        model.addAttribute("list",list);
        //list.jsp + model = ModelAndView
        return "list";  //  /WEB-INF/list.jsp
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if(seckillId==null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getSeckillById(seckillId);
        if(seckill==null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    //ajax,json
    @RequestMapping(value = "/{seckillId}/exposer",
                method = RequestMethod.POST,
                produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> result;
        try {
            Exposer exposer  = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",
                    method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                    @PathVariable("md5") String md5,
                                                    @CookieValue(value = "killPhone",required = false) Long phone){
        if (phone==null){
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }
        SeckillResult<SeckillExecution> result;
        try {
//            SeckillExecution execution = seckillService.executeSeckill(seckillId,phone,md5);
            //通过存储过程调用
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId,phone,md5);
            return new SeckillResult<SeckillExecution>(true,execution);
            //即便是报错也需要返回true——可以将错误的信息返回至前端页面
        }catch (RepeatKillException e){
            SeckillExecution execution = new SeckillExecution(seckillId,SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch (SeckillCloseException e){
            SeckillExecution execution = new SeckillExecution(seckillId,SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            SeckillExecution execution = new SeckillExecution(seckillId,SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true,execution);
        }
    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }


}
