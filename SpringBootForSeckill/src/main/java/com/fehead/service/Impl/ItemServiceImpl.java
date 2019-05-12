package com.fehead.service.Impl;

import com.fehead.dao.ItemInfoMapper;
import com.fehead.dao.ItemStockMapper;
import com.fehead.dataobject.ItemInfo;
import com.fehead.dataobject.ItemStock;
import com.fehead.error.BussinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.service.ItemService;
import com.fehead.service.model.ItemModel;
import com.fehead.validator.ValidationResult;
import com.fehead.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaoaxiao on 2019/5/11
 * Description:
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    @Autowired
    private ItemStockMapper itemStockMapper;

    @Override
    //创建一般都需要事务
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BussinessException {
        if(itemModel==null){
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //校验入参（只是入参需要校验，有些在Service层设置或在Service层获取的属性值不用校验）
        //校验就需要给ItemModel中的部分属性加注解
        ValidationResult validationResult = validator.validate(itemModel);
        if(validationResult.isHasErrors()){
            //将map中的信息都抛出去
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,validationResult.getErrMsg());
        }

        //转化itemModel->dataObject（itemModel->itemInfo）
        ItemInfo itemInfo = this.convertItemInfoFromItemModel(itemModel);

        //写入数据库(itemInfoMapper)，并拿到itemInfo的id
        itemInfoMapper.insertSelective(itemInfo);
        itemModel.setId(itemInfo.getId());

        //转化itemModel->dataObject（itemModel->itemStock）
        ItemStock itemStock = this.convertItemStockFromItemModel(itemModel);

        //写入数据库(itemStockMapper)
        itemStockMapper.insertSelective(itemStock);


        //返回创建完成的对象->通过service层的getItemById即可
        return this.getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {

        List<ItemInfo> itemInfoList = itemInfoMapper.listItem();
        List<ItemModel> itemModelList = itemInfoList.stream().map(itemInfo -> {
            ItemStock itemStock = itemStockMapper.selectByItemId(itemInfo.getId());
            ItemModel itemModel = this.convertItemModelFromDataObject(itemInfo,itemStock);
            return itemModel;
        }).collect(Collectors.toList());

        return itemModelList;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemInfo itemInfo = itemInfoMapper.selectByPrimaryKey(id);
        if(itemInfo==null){
            return null;
        }

        //操作获得库存数量
        ItemStock itemStock = itemStockMapper.selectByItemId(itemInfo.getId());

        //将dataObject->model
        ItemModel itemModel = this.convertItemModelFromDataObject(itemInfo,itemStock);

        return itemModel;
    }

    @Override
    //对应减操作需要保证事务一致性
    @Transactional
    public boolean decreaseStock(Integer itemId, Integer amount) throws BussinessException {
        //影响行数——若更新成功返回对应行数1，没成功返回0
        int affectedRow = itemStockMapper.decreaseStock(itemId,amount);
        if(affectedRow>0){
            //更新库存成功
            return true;
        }else {
            //更新库存失败
            return false;
        }
    }

    @Override
    //更新使销量增加需要事务
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) throws BussinessException {
        itemInfoMapper.increaseSales(itemId,amount);
    }

    //model->dataObject   ItemModel->ItemInfo
    private ItemInfo convertItemInfoFromItemModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemInfo itemInfo = new ItemInfo();
        BeanUtils.copyProperties(itemModel,itemInfo);
        //因为model和info中的price属性的数据类型不一致，所以BeanUtils不能处理
        // 只能手动处理：将BigDecimal转换为double
        itemInfo.setPrice(itemModel.getPrice().doubleValue());
        return itemInfo;
    }

    //model->dataObject   ItemModel->ItemStock
    private ItemStock convertItemStockFromItemModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemStock itemStock = new ItemStock();
        //属性不多，手动添加就行
        itemStock.setStock(itemModel.getStock());
        itemStock.setItemId(itemModel.getId());
        return itemStock;
    }

    //dataObject->model     ItemInfo+ItemStock->ItemModel
    private ItemModel convertItemModelFromDataObject(ItemInfo itemInfo,ItemStock itemStock){
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemInfo,itemModel);
        //price的类型不匹配，手动转换——将double转换为BigDecimal->new BigDecimal(double)
        itemModel.setPrice(new BigDecimal(itemInfo.getPrice()));
        itemModel.setStock(itemStock.getStock());

        return itemModel;
    }

}
