package com.puyuntech.flowerToHome.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

/**
 * 
 * Entity - 商品 . Created on 2015-8-27 下午3:25:10
 * 
 * @author 王凯斌
 * 
 */
@Indexed
@Entity
@Table(name = "t_product")
public class Product extends BaseEntity<Integer> {

	private static final long serialVersionUID = 4979454533832727241L;

	//销售价
	private BigDecimal price;
	
	//成本
	private BigDecimal cost;
	
	//市场价
	private BigDecimal marketPrice;
	
	//名称
	private String name;
	
	//副标题
	private String caption;
	
	//单位
	private String unit;
	
	//是否上架
	private Integer isMarketable;
	
	//是否列出
	private Integer isList;
	
	//简介
	private String introduction;
	
	//备注
	private String remark;
	
	//关键词
	private String keyword;
	
	//商品图片1
	private String productImages1;
	
	//商品图片2
	private String productImages2;
	
	//商品图片3
	private String productImages3;
	
	//商品图片4
	private String productImages4;
		
	//默认缩略图
	private String productImagesDefault;
	
	//积分
	private Integer point;
	
	//规格-目标
	private Integer specTargetId;
	
	//规格-花卉
	private Integer specFlowertId;
	
	//规格-类别
	private Integer specSorttId;
	
	//规格-设计
	private Integer specDesigntId;
	
	//规格-颜色
	private Integer specColortId;
	
	//规格-系列
	private Integer specSeriestId;
	
	//规格-主题
	private Integer specThemetId;
	
	//是否是标品
	private Integer isStandard;
	
	//商品编号
	private String sn;
	
	//是否是虚拟商品
	private Integer isVirtual;
	
	//区域价格
	private BigDecimal areaPrice;

	@Column(name="price")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name="cost")
	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Column(name="market_price")
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="caption")
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name="unit")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name="is_marketable")
	public Integer getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(Integer isMarketable) {
		this.isMarketable = isMarketable;
	}

	@Column(name="is_list")
	public Integer getIsList() {
		return isList;
	}

	public void setIsList(Integer isList) {
		this.isList = isList;
	}

	@Column(name="introduction")
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="keyword")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name="product_images1")
	public String getProductImages1() {
		return productImages1;
	}

	public void setProductImages1(String productImages1) {
		this.productImages1 = productImages1;
	}

	@Column(name="product_images2")
	public String getProductImages2() {
		return productImages2;
	}

	public void setProductImages2(String productImages2) {
		this.productImages2 = productImages2;
	}

	@Column(name="product_images3")
	public String getProductImages3() {
		return productImages3;
	}

	public void setProductImages3(String productImages3) {
		this.productImages3 = productImages3;
	}

	@Column(name="product_images4")
	public String getProductImages4() {
		return productImages4;
	}

	public void setProductImages4(String productImages4) {
		this.productImages4 = productImages4;
	}

	@Column(name="product_images_default")
	public String getProductImagesDefault() {
		return productImagesDefault;
	}

	public void setProductImagesDefault(String productImagesDefault) {
		this.productImagesDefault = productImagesDefault;
	}

	@Column(name="point")
	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	@Column(name="spec_target_id")
	public Integer getSpecTargetId() {
		return specTargetId;
	}

	public void setSpecTargetId(Integer specTargetId) {
		this.specTargetId = specTargetId;
	}

	@Column(name="spec_flowert_id")
	public Integer getSpecFlowertId() {
		return specFlowertId;
	}

	public void setSpecFlowertId(Integer specFlowertId) {
		this.specFlowertId = specFlowertId;
	}

	@Column(name="spec_sortt_id")
	public Integer getSpecSorttId() {
		return specSorttId;
	}

	public void setSpecSorttId(Integer specSorttId) {
		this.specSorttId = specSorttId;
	}

	@Column(name="spec_designt_id")
	public Integer getSpecDesigntId() {
		return specDesigntId;
	}

	public void setSpecDesigntId(Integer specDesigntId) {
		this.specDesigntId = specDesigntId;
	}

	@Column(name="spec_colort_id")
	public Integer getSpecColortId() {
		return specColortId;
	}

	public void setSpecColortId(Integer specColortId) {
		this.specColortId = specColortId;
	}

	@Column(name="spec_seriest_id")
	public Integer getSpecSeriestId() {
		return specSeriestId;
	}

	public void setSpecSeriestId(Integer specSeriestId) {
		this.specSeriestId = specSeriestId;
	}

	@Column(name="spec_themet_id")
	public Integer getSpecThemetId() {
		return specThemetId;
	}

	public void setSpecThemetId(Integer specThemetId) {
		this.specThemetId = specThemetId;
	}

	@Column(name="is_standard")
	public Integer getIsStandard() {
		return isStandard;
	}

	public void setIsStandard(Integer isStandard) {
		this.isStandard = isStandard;
	}

	@Column(name="sn")
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name="is_virtual")
	public Integer getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(Integer isVirtual) {
		this.isVirtual = isVirtual;
	}

	@Column(name="area_price")
	public BigDecimal getAreaPrice() {
		return areaPrice;
	}

	public void setAreaPrice(BigDecimal areaPrice) {
		this.areaPrice = areaPrice;
	}
	
}
