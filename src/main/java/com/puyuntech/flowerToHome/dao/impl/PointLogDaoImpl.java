package com.puyuntech.flowerToHome.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.Page;
import com.puyuntech.flowerToHome.Pageable;
import com.puyuntech.flowerToHome.dao.PointLogDao;
import com.puyuntech.flowerToHome.entity.Member;
import com.puyuntech.flowerToHome.entity.PointLog;

/**
 * 
 * 积分Dao . 
 * Created on 2015-9-13 下午4:06:16 
 * @author 王凯斌
 */
@Repository("pointLogDaoImpl")
public class PointLogDaoImpl extends BaseDaoImpl<PointLog, Integer> implements PointLogDao {

	@Override
	public Page<PointLog> findPage(Member member, Pageable pageable) {
		if (member == null) {
			return Page.emptyPage(pageable);
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PointLog> criteriaQuery = criteriaBuilder.createQuery(PointLog.class);
		Root<PointLog> root = criteriaQuery.from(PointLog.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("member"), member));
		return super.findPage(criteriaQuery, pageable);
	}

	

}
