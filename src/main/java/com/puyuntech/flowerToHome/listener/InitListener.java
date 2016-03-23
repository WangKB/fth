package com.puyuntech.flowerToHome.listener;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import com.puyuntech.flowerToHome.plugin.unionpayPayment.SDKConfig;
import com.puyuntech.flowerToHome.service.ConfigService;
import com.puyuntech.flowerToHome.service.SearchService;
import com.puyuntech.flowerToHome.service.StaticService;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/***
 * 
 * @ClassName: InitListener
 * @Description: Listener - 初始化  
 * @date: 2015-8-12 下午4:47:08 
 * @author: 王凯斌
 */
@Component("initListener")
public class InitListener implements ServletContextAware, ApplicationListener<ContextRefreshedEvent> {

	/** ServletContext */
	private ServletContext servletContext;

	@Resource(name = "configServiceImpl")
	private ConfigService configService;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;
	@Resource(name = "searchServiceImpl")
	private SearchService searchService;
	
	/**
	 * 设置ServletContext
	 * 
	 * @param servletContext
	 *            ServletContext
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 事件执行
	 * 当 ApplicationContext 被初始化或者刷新 时会触发该方法
	 * 
	 * @param contextRefreshedEvent
	 *            ContextRefreshedEvent
	 *            
	 *            configService.init  将 shop.xml 中set标签的值加入到FreeMarker的Configuration中
	 *            staticService.generateIndex 静态化 index.html
	 *            staticService.generateOther 静态化 公用的 js 文件
	 */
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		if (servletContext != null && contextRefreshedEvent.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {//这段代码加载一次
			configService.init();
			/**
			 * TODO
			 * 删除和生成索引
			 */
			searchService.purge();
			searchService.index();
			
//			staticService.generateIndex(); 
//			staticService.generateOther();
            String rootPath = servletContext.getRealPath("/");
            SDKConfig.getConfig().loadPropertiesFromSrc( rootPath );

		}else{
			
		}
	}

}