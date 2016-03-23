package com.puyuntech.flowerToHome.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Entity - Greetingcard. Created on 2015-8-26 下午1:52:07
 * 
 * @author 施长成
 */
@Entity
@Table(name = "t_greetingcards")
public class Greetingcard extends BaseEntity<Integer> {


	// 第一次访问日期
	private Date visitDate;

	//名称
	private String name;
	
	//寄语
	private String wishes;
	
	//图片
	private String images;

	@Column(name = "visit_date")
	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "wishes")
	public String getWishes() {
		return wishes;
	}

	public void setWishes(String wishes) {
		this.wishes = wishes;
	}

	@Column(name = "images")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}
