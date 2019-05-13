package com.fehead.service.Impl;

import com.fehead.dao.OrderInfoMapper;
import com.fehead.dao.SequenceInfoMapper;
import com.fehead.dataobject.OrderInfo;
import com.fehead.dataobject.SequenceInfo;
import com.fehead.error.BussinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.service.ItemService;
import com.fehead.service.OrderService;
import com.fehead.service.UserService;
import com.fehead.service.model.ItemModel;
import com.fehead.service.model.OrderModel;
import com.fehead.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by xiaoaxiao on 2019/5/11
 * Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private SequenceInfoMapper sequenceInfoMapper;

    @Override
    //订单的创建为一个事务
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer promoId,Integer amount) throws BussinessException {

        //1、校验下单状态，下单的商品是否存在，用户是否合法，购买数量是否正确
        ItemModel itemModel = itemService.getItemById(itemId);
        if(itemModel==null){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品信息不存在");
        }

        UserModel userModel = userService.getUserById(userId);
        if(userModel==null){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户信息不存在");
        }

        //购买数量只能是1-99
        if(amount<=0||amount>99){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"数量信息不正确");
        }

        //校验活动信息
        if(promoId!=null){
            //（1）校验对应活动是否存在这个适用商品
            //传进来的promoId和itemModel中的promoModel中的不相等——不存在该活动
            if(!promoId.equals(itemModel.getPromoModel().getId())){
                throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动信息不正确");
            }else if(itemModel.getPromoModel().getStatus()!=2){
                throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动已结束或还未开始");
            }
        }

        //2、落单减库存（下单成功就加库存，而不是支付减库存——避免超卖现象）
        boolean result = itemService.decreaseStock(itemId,amount);
        if(!result){
            throw new BussinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }

        //3、订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
//        如果promoId!=null则证明有秒杀活动，就是用秒杀价格，如果==null就证明没有秒杀活动，就用平常销售价格
        if(promoId!=null){
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setItemPrice(itemModel.getPrice());
        }

        //使用orderModel.getItemPrice()，因为item的Price代表的是平销价格
        //而orderModel.getItemPrice()已经在前面做过判断了，代表准确的价格（平销价/秒杀价）
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));

        //生成订单号（交易流水号）
        orderModel.setId(generateOrderNo());
        //model->dataObject
        OrderInfo orderInfo = convertOrderInfoFromOrderModel(orderModel);

        //dataObject对数据库进行操作
        // 该表没有自增主键，所以不需要设置insert的keyProperty="id" useGeneratedKeys="true"属性
        orderInfoMapper.insertSelective(orderInfo);

        //加上商品的销量
        itemService.increaseSales(itemId,amount);

        //4、返回前端
        return orderModel;
    }

    //生成订单号
    //这个事务注解的意思是：即便是前面生成订单的逻辑出了错，这六位数也要生成，再生成订单，这六位数就往后移一个了——为了保证唯一性
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private String generateOrderNo(){
        //订单有16位
        StringBuilder stringBuilder = new StringBuilder();
        //1、前8位为时间信息，年月日
        //java8创建当前时间的方式
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-","");
        stringBuilder.append(nowDate);
        //2、中间6位为自增序列（每天的订单）
        //获取当前sequence
        int sequence = 0;
        SequenceInfo sequenceInfo = sequenceInfoMapper.getSequenceByName("order_info");
        sequence = sequenceInfo.getCurrentValue();
        sequenceInfo.setCurrentValue(sequenceInfo.getCurrentValue()+sequenceInfo.getStep());
        sequenceInfoMapper.updateByPrimaryKeySelective(sequenceInfo);

        String sequenceStr = String.valueOf(sequence);
        for(int i=0;i<6-sequenceStr.length();i++){
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);
        //3、最后2位为分库分表位，暂时写死
        stringBuilder.append("00");

        return stringBuilder.toString();
    }

    private OrderInfo convertOrderInfoFromOrderModel(OrderModel orderModel){
        if(orderModel==null){
            return null;
        }
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderModel,orderInfo);
        //将BigDecimal（领域模型）->double(数据库）
        orderInfo.setItemprice(orderModel.getItemPrice().doubleValue());
        orderInfo.setOrderprice(orderModel.getOrderPrice().doubleValue());
        return orderInfo;
    }
}
