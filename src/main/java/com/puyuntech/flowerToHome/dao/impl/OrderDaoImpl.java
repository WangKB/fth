package com.puyuntech.flowerToHome.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.Page;
import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.dao.OrderDao;
import com.puyuntech.flowerToHome.entity.Order;
import com.puyuntech.flowerToHome.entity.Order.FromType;
import com.puyuntech.flowerToHome.entity.Order.Status;

/**
 * 
 * order Dao. Created on 2015-9-6 上午10:40:22
 * 
 * @author 王凯斌
 */
@Repository("orderDaoImpl")
public class OrderDaoImpl extends BaseDaoImpl<Order, Integer> implements
OrderDao {
	
	/** 属性分隔符 */
	private static final String PROPERTY_SEPARATOR = ".";
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {  
        return jdbcTemplate;  
    }  

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Integer changeStatus(Order order, Order.Status status) {

		Integer id = order.getId();
		Integer statusCode = status.getId();
		
		jdbcTemplate.execute( "{call proc_setOrderStatus("+id+","+statusCode+")}");
		return 1;
	}

	@Override
	public Page<Order> pageByTel(Pageable pageable) {
		
		String jpql = "select members.id from Member members where members.phone like :phone ";
		String jpql2 = "select orders from Order orders where orders.memberId in :memberIds ";
		try {
			List<Integer> memberIds = entityManager.createQuery(jpql, Integer.class).setParameter("phone","%" + pageable.getSearchValue() + "%").getResultList();
			if(memberIds.size()==0){
				return new Page<Order>(new ArrayList<Order>(),0L,pageable);
			}
			Integer count = entityManager.createQuery(jpql2, Order.class).setParameter("memberIds",memberIds).getResultList().size();
			List<Order> orders = entityManager.createQuery(jpql2, Order.class).setParameter("memberIds",memberIds).setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize()).setMaxResults(pageable.getPageSize()).getResultList();
			return new Page<Order>(orders,Long.valueOf(count),pageable);
		} catch (NoResultException e) {
			return new Page<Order>(new ArrayList<Order>(),0L,pageable);
		}
	}

	public Page<Order> report(Pageable pageable, Status status, Integer shopId, String memberTel, 
			String province,String city, String distract, Integer isBlance, FromType fromType) {


		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery = criteriaBuilder
				.createQuery(Order.class);
		Root<Order> root = criteriaQuery.from(Order.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (status != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("status"), status));
		}
		if (shopId != null&&shopId!=-1) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("shopId"), shopId));
		}
		if (isBlance != null&&isBlance!=-1) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("isBalance"), isBlance));
		}
		if (fromType != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("fromType"), fromType));
		}
		if (memberTel != null) {
			String jpql = "select members.id from Member members where members.phone like :phone ";
			List<Integer> memberIds = entityManager.createQuery(jpql, Integer.class).setParameter("phone","%" + memberTel + "%").getResultList();
			if(memberIds.size()!=0){
				restrictions = criteriaBuilder.and(restrictions,
						root.get("memberId").in(memberIds));
			}else{
				List<Integer> empty = new ArrayList<Integer>();
				empty.add(-1);
				restrictions = criteriaBuilder.and(restrictions,
						root.get("memberId").in(empty));
			}
			
		}
		Path<String> searchPath;
		if (province != null) {
			searchPath = getPath(root, "addrProvince");
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(searchPath, "%" + province + "%"));
		}
		if (city != null) {
			searchPath = getPath(root, "addrCity");
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(searchPath, "%" + city + "%"));
		}
		if (distract != null) {
			searchPath = getPath(root, "addrDistrict");
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(searchPath, "%" + distract + "%"));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);

	}
	
	@SuppressWarnings("unchecked")
	private <X> Path<X> getPath(Path<?> path, String propertyPath) {
		if (path == null || StringUtils.isEmpty(propertyPath)) {
			return (Path<X>) path;
		}
		String property = StringUtils.substringBefore(propertyPath, PROPERTY_SEPARATOR);
		return getPath(path.get(property), StringUtils.substringAfter(propertyPath, PROPERTY_SEPARATOR));
	}
}
