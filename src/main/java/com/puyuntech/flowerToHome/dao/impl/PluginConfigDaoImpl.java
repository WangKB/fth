package com.puyuntech.flowerToHome.dao.impl;

import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.dao.PluginConfigDao;
import com.puyuntech.flowerToHome.entity.PluginConfig;

/**
 * 
 * Dao - 插件配置. Created on 2015-10-14 下午1:14:29
 * 
 * @author 王凯斌
 */
@Repository("pluginConfigDaoImpl")
public class PluginConfigDaoImpl extends BaseDaoImpl<PluginConfig, Integer>
		implements PluginConfigDao {

	public boolean pluginIdExists(String pluginId) {

		/**
		 * 判断插件id是否为空
		 */
		if (StringUtils.isEmpty(pluginId)) {
			return false;
		}

		/**
		 * 定义jpql语句
		 */
		String jpql = "select count(*) from PluginConfig pluginConfig where pluginConfig.pluginId = :pluginId";

		/**
		 * 定义参数并返回结果
		 */
		Long count = entityManager.createQuery(jpql, Long.class)
				.setParameter("pluginId", pluginId).getSingleResult();
		return count > 0;
	}

	public PluginConfig findByPluginId(String pluginId) {

		/**
		 * 判断插件id是否为空
		 */
		if (StringUtils.isEmpty(pluginId)) {
			return null;
		}

		/**
		 * 定义jpql语句并设定参数进行查询
		 */
		try {
			String jpql = "select pluginConfig from PluginConfig pluginConfig where pluginConfig.pluginId = :pluginId";
			return entityManager.createQuery(jpql, PluginConfig.class)
					.setParameter("pluginId", pluginId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}