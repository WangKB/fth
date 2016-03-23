package com.puyuntech.flowerToHome.dao.impl;

import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.dao.OrganizationDao;
import com.puyuntech.flowerToHome.entity.Organization;

/**
 * 
 *门店Dao . 
 * Created on 2015-9-24 下午4:29:17 
 * @author 施长成
 */
@Repository("organizationDaoImpl")
public class OrganizationDaoImpl extends BaseDaoImpl<Organization, Integer> implements OrganizationDao {
	
}
