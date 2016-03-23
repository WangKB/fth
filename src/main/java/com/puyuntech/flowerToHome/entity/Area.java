package com.puyuntech.flowerToHome.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * Entity - 地区. 
 * Created on 2015-8-17 上午11:25:49 
 * @author 王凯斌
 */

@Entity
@Table(name = "t_area")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "seq_area")
public class Area extends OrderEntity<Integer> {

	private static final long serialVersionUID = -7433468874759195791L;

	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";

	/** 名称 */
	private String name;

	/** 价格系数 */
	private BigDecimal proportion;
	
	/** 活跃度 */
	private BigDecimal activity;

	/** 树路径 */
	private String treePath;

	/** 层级 */
	private Integer grade;

	/** 上级地区 */
	private Area parent;

	/** 下级地区 */
	private Set<Area> children = new HashSet<Area>();

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(name="name",nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="proportion")
	public BigDecimal getProportion() {
		return proportion;
	}

	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}
	
	@Column(name="activity")
	public BigDecimal getActivity() {
		return activity;
	}

	public void setActivity(BigDecimal activity) {
		this.activity = activity;
	}

	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Column(name="tree_path",nullable = false)
	public String getTreePath() {
		return treePath;
	}

	/**
	 * 设置树路径
	 * 
	 * @param treePath
	 *            树路径
	 */
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	/**
	 * 获取层级
	 * 
	 * @return 层级
	 */
	@Column(name="grade",nullable = false)
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 设置层级
	 * 
	 * @param grade
	 *            层级
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	/**
	 * 获取上级地区
	 * 
	 * @return 上级地区
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parent")
	public Area getParent() {
		return parent;
	}

	/**
	 * 设置上级地区
	 * 
	 * @param parent
	 *            上级地区
	 */
	public void setParent(Area parent) {
		this.parent = parent;
	}

	/**
	 * 获取下级地区
	 * 
	 * @return 下级地区
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("order asc")
	public Set<Area> getChildren() {
		return children;
	}

	/**
	 * 设置下级地区
	 * 
	 * @param children
	 *            下级地区
	 */
	public void setChildren(Set<Area> children) {
		this.children = children;
	}
	


	/**
	 * 获取所有上级地区ID
	 * 
	 * @return 所有上级地区ID
	 */
	@Transient
	public Long[] getParentIds() {
		String[] parentIds = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
		Long[] result = new Long[parentIds.length];
		for (int i = 0; i < parentIds.length; i++) {
			result[i] = Long.valueOf(parentIds[i]);
		}
		return result;
	}

	/**
	 * 获取所有上级地区
	 * 
	 * @return 所有上级地区
	 */
	@Transient
	public List<Area> getParents() {
		List<Area> parents = new ArrayList<Area>();
		recursiveParents(parents, this);
		return parents;
	}

	/**
	 * 递归上级地区
	 * 
	 * @param parents
	 *            上级地区
	 * @param area
	 *            地区
	 */
	private void recursiveParents(List<Area> parents, Area area) {
		if (area == null) {
			return;
		}
		Area parent = area.getParent();
		if (parent != null) {
			parents.add(0, parent);
			recursiveParents(parents, parent);
		}
	}

	/**
	 * 删除前处理
	 */
	@PreRemove
	public void preRemove() {
		
	}
	

}
