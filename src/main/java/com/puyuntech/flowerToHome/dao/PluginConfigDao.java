package com.puyuntech.flowerToHome.dao;

import com.puyuntech.flowerToHome.entity.PluginConfig;

/**
 * 
 * Dao - 插件配置. Created on 2015-10-14 上午11:34:16
 * 
 * @author 王凯斌
 */
public interface PluginConfigDao extends BaseDao<PluginConfig, Integer> {

	/**
	 * 
	 * 判断插件ID是否存在.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午11:34:35
	 * 
	 * @param pluginId
	 *            插件ID
	 * @return 插件ID是否存在
	 */
	boolean pluginIdExists(String pluginId);

	/**
	 * 
	 * 根据插件ID查找插件配置.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午11:35:09
	 * 
	 * @param pluginId
	 *            插件ID
	 * @return 插件配置，若不存在则返回null
	 */
	PluginConfig findByPluginId(String pluginId);

}