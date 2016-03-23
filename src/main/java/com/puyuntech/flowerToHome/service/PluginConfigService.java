
package com.puyuntech.flowerToHome.service;

import com.puyuntech.flowerToHome.entity.PluginConfig;


/**
 * 
 * Service - 插件配置. 
 * Created on 2015-10-14 下午1:55:22 
 * @author 王凯斌
 */
public interface PluginConfigService extends BaseService<PluginConfig, Integer> {

	/**
	 * 
	 * 判断插件ID是否存在.
	 * 
	 * author: 王凯斌
	 *   date: 2015-10-14 下午1:56:06
	 * @param pluginId 插件ID
	 * @return 插件ID是否存在
	 */
	boolean pluginIdExists(String pluginId);

	/**
	 * 
	 * 根据插件ID查找插件配置.
	 * 
	 * author: 王凯斌
	 *   date: 2015-10-14 下午1:56:26
	 * @param pluginId 插件ID
	 * @return 插件配置，若不存在则返回null
	 */
	PluginConfig findByPluginId(String pluginId);

	/**
	 * 
	 * 根据插件ID删除插件配置.
	 * 
	 * author: 王凯斌
	 *   date: 2015-10-14 下午2:01:48
	 * @param pluginId 插件ID
	 */
	void deleteByPluginId(String pluginId);

}