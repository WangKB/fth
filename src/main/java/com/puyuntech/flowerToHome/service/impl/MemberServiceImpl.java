package com.puyuntech.flowerToHome.service.impl;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.puyuntech.flowerToHome.Principal;
import com.puyuntech.flowerToHome.Setting;
import com.puyuntech.flowerToHome.dao.MemberDao;
import com.puyuntech.flowerToHome.entity.Member;
import com.puyuntech.flowerToHome.service.MemberService;
import com.puyuntech.flowerToHome.util.SystemUtils;

/**
 * 
 * Service - 会员 . 
 * Created on 2015-9-6 上午9:33:23 
 * @author 王凯斌
 */
@Service("memberServiceImpl")
public class MemberServiceImpl extends BaseServiceImpl<Member, Integer> implements MemberService {
	
	@Resource(name = "memberDaoImpl")
	MemberDao memberDao;
	
	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return memberDao.usernameExists(username);
	}

	@Transactional(readOnly = true)
	public Member getCurrent() {
		return getCurrent(false);
	}

	@Transactional(readOnly = true)
	public Member getCurrent(boolean lock) {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		Principal principal = requestAttributes != null ? (Principal) requestAttributes.getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, RequestAttributes.SCOPE_SESSION) : null;
		Integer id = principal != null ? principal.getId() : null;
		if (lock) {
			return memberDao.find(id, LockModeType.PESSIMISTIC_WRITE);
		} else {
			return memberDao.find(id);
		}
	}
	
}