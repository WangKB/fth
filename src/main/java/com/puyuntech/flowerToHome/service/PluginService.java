
package com.puyuntech.flowerToHome.service;

import java.util.List;

import com.puyuntech.flowerToHome.plugin.PaymentPlugin;
import com.puyuntech.flowerToHome.plugin.StoragePlugin;

/**
 * 
 * Service - 插件. 
 * Created on 2015-10-14 下午1:55:45 
 * @author 王凯斌
 */
public interface PluginService {

	/**
	 * 
	 * 获取存储插件.
	 * 
	 * author: 王凯斌
	 *   date: 2015-10-14 下午2:02:15
	 * @return 存储插件
	 */
	List<StoragePlugin> getStoragePlugins();

	/**
	 * 
	 * 获取存储插件.
	 * 
	 * author: 王凯斌
	 *   date: 2015-10-14 下午2:02:35
	 * @param isEnabled 是否启用
	 * @return 存储插件
	 */
	List<StoragePlugin> getStoragePlugins(boolean isEnabled);

	/**
	 * 
	 * 获取存储插件.
	 * 
	 * author: 王凯斌
	 *   date: 2015-10-14 下午2:02:58
	 * @param id ID
	 * @return 存储插件
	 */
	StoragePlugin getStoragePlugin(String id);
	
	

	/**
	 * 获取支付插件
	 * 
	 * author: 南金豆
	 *   date: 2015-10-23 下午2:02:58
	 * @return 支付插件
	 */
	List<PaymentPlugin> getPaymentPlugins();

	/**
	 * 
	 *  获取支付插件
	 * author: 南金豆
	 *  date: 2015-10-23 下午2:02:58
	 * @param isEnabled
	 *            是否启用
	 * @return 支付插件
	 */
	List<PaymentPlugin> getPaymentPlugins(boolean isEnabled);

	/**
	 * 获取支付插件
	  * author: 南金豆
	 *  date: 2015-10-23 下午2:02:58
	 * @param id
	 *            ID
	 * @return 支付插件
	 */
	PaymentPlugin getPaymentPlugin(String id);
}