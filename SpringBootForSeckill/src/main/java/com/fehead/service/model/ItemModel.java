package com.fehead.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by xiaoaxiao on 2019/5/11
 * Description:Item商品领域模型
 */
//给ItemModel中的部分属性加注解，为了更好的校验
//添加注解一定要注意，String类型->@NotBlank（不能为空），其他类型->@NotNull（不能不填）   ！！！！！！！！
public class ItemModel {

    private Integer id;

    //商品名称

    @NotBlank(message = "商品名称不能为空")
    private String title;

    //商品价格——将价格设置为BigDecimal，因为double传到前端会出现精度问题，json处理会出现问题
    //所以在与数据库交互的dataObject中将price设置为double，在领域模型中将double转换为BigDecimal来存
    //将double转换为BigDecimal->new BigDecimal(double)
    //将BigDecimal转换为double->BigDecimal.doubleValue()
    @NotNull(message = "商品价格不能不填")
    @Min(value = 0,message = "商品价格必须大于0")
    private BigDecimal price;

    //商品库存
    @NotNull(message = "商品库存不能不填")
    private Integer stock;

    //商品描述
    @NotBlank(message = "商品描述信息不能为空")
    private String description;

    //商品销量——不是入参，从别处算的结果
    private Integer sales;

    //商品描述图片的url
    @NotBlank(message = "商品图片信息不能为空")
    private String imgUrl;

    //使用聚合模型，如果promoModel不为空且promoModel.getStatus()!=3，则表示其拥有还未结束的秒杀活动
    //若其拥有还未结束的秒杀活动，则引入对应的promoModel
    private PromoModel promoModel;

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
