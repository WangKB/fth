package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *  Entity - 日志 实体类. 
 * Created on 2015-8-26 下午17:45:30
 * @author 王凯斌
 */
@Entity
@Table(name = "t_system_log")
public class Log extends BaseEntity<Integer> {

	private static final long serialVersionUID = -7186140456087433196L;

	/** "日志内容"属性名称 */
	public static final String LOG_CONTENT_ATTRIBUTE_NAME = Log.class.getName() + ".CONTENT";

	/** 操作员 */
	private String operator;

	/** 内容 */
	private String content;


	/**
	 * 获取操作员
	 * 
	 * @return 操作员
	 */
	@Column(name="operator",updatable = false)
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置操作员
	 * 
	 * @param operator
	 *            操作员
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	@Column(name="content",updatable = false, length = 4000)
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * 设置操作员
	 * 
	 * @param operator
	 *            操作员
	 */
	@Transient
	public void setOperator(Admin operator) {
		setOperator(operator != null ? operator.getWebUsername() : null);
	}

}
