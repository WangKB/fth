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

	//标签id
	private Integer tagsId;
	
	//规格-目标
	private String specTargetId;
	
	//规格-花卉
	private String specFlowertId;
	
	//规格-类别
	private String specSorttId;
	
	//规格-设计
	private String specDesigntId;
	
	//规格-颜色
	private String specColortId;
	
	//规格-系列
	private String specSeriestId;
	
	//规格-主题
	private String specThemetId;
	
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
	public String getSpecTargetId() {
		return specTargetId;
	}

	public void setSpecTargetId(String specTargetId) {
		this.specTargetId = specTargetId;
	}

	@Column(name="spec_flowert_id")
	public String getSpecFlowertId() {
		return specFlowertId;
	}

	public void setSpecFlowertId(String specFlowertId) {
		this.specFlowertId = specFlowertId;
	}

	@Column(name="spec_sortt_id")
	public String getSpecSorttId() {
		return specSorttId;
	}

	public void setSpecSorttId(String specSorttId) {
		this.specSorttId = specSorttId;
	}

	@Column(name="spec_designt_id")
	public String getSpecDesigntId() {
		return specDesigntId;
	}

	public void setSpecDesigntId(String specDesigntId) {
		this.specDesigntId = specDesigntId;
	}

	@Column(name="spec_colort_id")
	public String getSpecColortId() {
		return specColortId;
	}

	public void setSpecColortId(String specColortId) {
		this.specColortId = specColortId;
	}

	@Column(name="spec_seriest_id")
	public String getSpecSeriestId() {
		return specSeriestId;
	}

	public void setSpecSeriestId(String specSeriestId) {
		this.specSeriestId = specSeriestId;
	}

	@Column(name="spec_themet_id")
	public String getSpecThemetId() {
		return specThemetId;
	}

	public void setSpecThemetId(String specThemetId) {
		this.specThemetId = specThemetId;
	}
}
