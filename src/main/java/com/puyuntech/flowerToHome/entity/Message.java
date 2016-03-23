package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "t_message")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_message")
public class Message extends BaseEntity<Integer> {

	private static final long serialVersionUID = -589387880623074225L;

	/** 标题 */
	private String title;

	/** 内容 */
	private String content;

	/** ip */
	private String ip;

	/** 是否为草稿 */
	private Integer isDraft;

	/** 发件人已读 */
	private Integer senderRead;

	/** 收件人已读 */
	private Integer receiverRead;

	/** 发件人删除 */
	private Integer senderDelete;

	/** 收件人删除 */
	private Integer receiverDelete;

	/** 发件人 */
	private Integer sender;

	/** 收件人 */
	private Integer receiver;

	/** 原消息 */
	private Integer forMessage;

	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	@Column(nullable = false, updatable = false)
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	@NotEmpty
	@Length(max = 4000)
	@Column(nullable = false, updatable = false, length = 4000)
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
	 * 获取ip
	 * 
	 * @return ip
	 */
	@Column(nullable = false, updatable = false)
	public String getIp() {
		return ip;
	}

	/**
	 * 设置ip
	 * 
	 * @param ip
	 *            ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获取是否为草稿
	 * 
	 * @return 是否为草稿
	 */
	@Column(nullable = false, updatable = false)
	public Integer getIsDraft() {
		return isDraft;
	}

	/**
	 * 设置是否为草稿
	 * 
	 * @param isDraft
	 *            是否为草稿
	 */
	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}

	/**
	 * 获取发件人已读
	 * 
	 * @return 发件人已读
	 */
	@Column(nullable = false)
	public Integer getSenderRead() {
		return senderRead;
	}

	/**
	 * 设置发件人已读
	 * 
	 * @param senderRead
	 *            发件人已读
	 */
	public void setSenderRead(Integer senderRead) {
		this.senderRead = senderRead;
	}

	/**
	 * 获取收件人已读
	 * 
	 * @return 收件人已读
	 */
	@Column(nullable = false)
	public Integer getReceiverRead() {
		return receiverRead;
	}

	/**
	 * 设置收件人已读
	 * 
	 * @param receiverRead
	 *            收件人已读
	 */
	public void setReceiverRead(Integer receiverRead) {
		this.receiverRead = receiverRead;
	}

	/**
	 * 获取发件人删除
	 * 
	 * @return 发件人删除
	 */
	@Column(nullable = false)
	public Integer getSenderDelete() {
		return senderDelete;
	}

	/**
	 * 设置发件人删除
	 * 
	 * @param senderDelete
	 *            发件人删除
	 */
	public void setSenderDelete(Integer senderDelete) {
		this.senderDelete = senderDelete;
	}

	/**
	 * 获取收件人删除
	 * 
	 * @return 收件人删除
	 */
	@Column(nullable = false)
	public Integer getReceiverDelete() {
		return receiverDelete;
	}

	/**
	 * 设置收件人删除
	 * 
	 * @param receiverDelete
	 *            收件人删除
	 */
	public void setReceiverDelete(Integer receiverDelete) {
		this.receiverDelete = receiverDelete;
	}

	/**
	 * 获取发件人
	 * 
	 * @return 发件人
	 */
	@Column(nullable = false)
	public Integer getSender() {
		return sender;
	}

	/**
	 * 设置发件人
	 * 
	 * @param sender
	 *            发件人
	 */
	public void setSender(Integer sender) {
		this.sender = sender;
	}

	/**
	 * 获取收件人
	 * 
	 * @return 收件人
	 */
	@Column(nullable = false)
	public Integer getReceiver() {
		return receiver;
	}

	/**
	 * 设置收件人
	 * 
	 * @param receiver
	 *            收件人
	 */
	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	/**
	 * 获取原消息
	 * 
	 * @return 原消息
	 */
	@Column(nullable = false)
	public Integer getForMessage() {
		return forMessage;
	}

	/**
	 * 设置原消息
	 * 
	 * @param forMessage
	 *            原消息
	 */
	public void setForMessage(Integer forMessage) {
		this.forMessage = forMessage;
	}

}
