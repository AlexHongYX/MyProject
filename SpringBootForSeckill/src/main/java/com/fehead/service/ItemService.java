package com.fehead.service;

import com.fehead.error.BussinessException;
import com.fehead.service.model.ItemModel;

import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/11
 * Description:
 */
public interface ItemService {

    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BussinessException;

    //商品列表浏览
    List<ItemModel> listItem();

    //商品详情浏览
    ItemModel getItemById(Integer id);

    //根据数量（amount）减库存
    boolean decreaseStock(Integer itemId,Integer amount) throws BussinessException;

    //商品销量增加
    void increaseSales(Integer itemId,Integer amount) throws BussinessException;
}
