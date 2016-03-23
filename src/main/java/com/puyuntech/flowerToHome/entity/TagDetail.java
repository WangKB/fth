package com.puyuntech.flowerToHome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Entity - 标签. 
 * Created on 2015-8-26 下午4:39:46 
 * @author Liaozhen
 */
@Entity
@Table(name = "t_tags_detail")
public class TagDetail extends OrderEntity<Integer> {

	//名称
	private String name;

	//商品id
	private Integer tagsId;
	
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
	
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "tags_id")
	public Integer getTagsId() {
		return tagsId;
	}

	public void setTagsId(Integer tagsId) {
		this.tagsId = tagsId;
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
}
