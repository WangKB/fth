package com.puyuntech.flowerToHome.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.dao.MemberDao;
import com.puyuntech.flowerToHome.entity.Member;

/**
 * 
 * Dao - 会员 
 * Created on 2015-8-26 下午15:32:33 
 * @author 王凯斌
 */
@Repository("memberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member, Integer> implements MemberDao {
	
	public boolean usernameExists(String username) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		String jpql = "select count(*) from Member members where lower(members.username) = lower(:username)";
		Long count = entityManager.createQuery(jpql, Long.class).setParameter("username", username).getSingleResult();
		return count > 0;
	}
}