package com.puyuntech.flowerToHome.vo;

import com.puyuntech.flowerToHome.entity.Product;

import java.math.BigDecimal;

/**
 * 绑定促销的 VO 对象
 * <p/>
 * Created by 王凯斌 on 2015/10/9 0009.
 */
public class PromotionBindVO {

    private Product product;
    private BigDecimal price;
    private String title;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
