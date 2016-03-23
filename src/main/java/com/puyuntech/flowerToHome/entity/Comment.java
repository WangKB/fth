package com.puyuntech.flowerToHome.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * 评论 - Entity  . 
 * Created on 2015-8-27 下午2:22:08 
 * @author 王凯斌
 */
@Entity
@Table(name = "t_comment")
public class Comment extends BaseEntity<Integer> {

	private static final long serialVersionUID = 4193114029718904441L;

	//名称
	private String content;
	
	//ip
	private String ip;
	
	//图片
	private String image;
	
	//商品id
	private Integer productId;
	
	//商家id
	private Integer memberId;

	@Column(name="product_id")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name="image")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name="member_id")
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

}
