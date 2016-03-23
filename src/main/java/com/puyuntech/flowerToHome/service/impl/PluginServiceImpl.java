package com.puyuntech.flowerToHome.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Service;

import com.puyuntech.flowerToHome.plugin.PaymentPlugin;
import com.puyuntech.flowerToHome.plugin.StoragePlugin;
import com.puyuntech.flowerToHome.service.PluginService;
/**
 * 
 * Service - 插件. 
 * Created on 2015-10-14 下午2:15:48 
 * @author 王凯斌
 */
@Service("pluginServiceImpl")
public class PluginServiceImpl implements PluginService {
	
	@Resource
	private List<PaymentPlugin> paymentPlugins = new ArrayList<PaymentPlugin>();
	//	引入支付插件
	@Resource
	private Map<String, PaymentPlugin> paymentPluginMap = new HashMap<String, PaymentPlugin>();
	/**
	 * 引入存储插件
	 */
	@Resource
	private List<StoragePlugin> storagePlugins = new ArrayList<StoragePlugin>();
	@Resource
	private Map<String, StoragePlugin> storagePluginMap = new HashMap<String, StoragePlugin>();

	public List<StoragePlugin> getStoragePlugins() {
		
		/**
		 * 排序存储插件
		 */
		Collections.sort(storagePlugins);
		return storagePlugins;
	}

	public List<StoragePlugin> getStoragePlugins(final boolean isEnabled) {
		
		/**
		 * 新建插件集
		 */
		List<StoragePlugin> result = new ArrayList<StoragePlugin>();
		
		/**
		 * 选中可用插件
		 */
		CollectionUtils.select(storagePlugins, new Predicate() {
			public boolean evaluate(Object object) {
				StoragePlugin storagePlugin = (StoragePlugin) object;
				return storagePlugin.getIsEnabled() == isEnabled;
			}
		}, result);
		
		/**
		 * 排序插件
		 */
		Collections.sort(result);
		return result;
	}



	public List<PaymentPlugin> getPaymentPlugins() {
		Collections.sort(paymentPlugins);
		return paymentPlugins;
	}
	public List<PaymentPlugin> getPaymentPlugins(final boolean isEnabled) {
		List<PaymentPlugin> result = new ArrayList<PaymentPlugin>();
		CollectionUtils.select(paymentPlugins, new Predicate() {
			public boolean evaluate(Object object) {
				PaymentPlugin paymentPlugin = (PaymentPlugin) object;
				return paymentPlugin.getIsEnabled() == isEnabled;
			}
		}, result);
		Collections.sort(result);
		return result;
	}

	@Override
	public PaymentPlugin getPaymentPlugin(String id) {
		return paymentPluginMap.get(id);
	}

	@Override
	public StoragePlugin getStoragePlugin(String id) {
		return storagePluginMap.get(id);
	}

	

}