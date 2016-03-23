package com.puyuntech.flowerToHome.plugin.localStorage;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.puyuntech.flowerToHome.Setting;
import com.puyuntech.flowerToHome.plugin.StoragePlugin;
import com.puyuntech.flowerToHome.util.SystemUtils;

/**
 * 
 * Plugin - 本地文件存储. 
 * Created on 2015-10-15 下午6:10:23 
 * @author 王凯斌
 */
@Component("localStoragePlugin")
public class LocalStoragePlugin extends StoragePlugin implements
		ServletContextAware {

	/** ServletContext */
	private ServletContext servletContext;

	/**
	 * 设置ServletContext
	 * 
	 * @param servletContext
	 *            ServletContext
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public String getName() {
		return "本地文件存储";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getAuthor() {
		return "Puyuntech";
	}

	@Override
	public String getSiteUrl() {
		//TODO 网址需要改
		return "http://www.puyuntech.com";
	}

	@Override
	public String getInstallUrl() {
		return null;
	}

	@Override
	public String getUninstallUrl() {
		return null;
	}

	@Override
	public String getSettingUrl() {
		return "local_storage/setting.jhtml";
	}

	@Override
	public void upload(String path, File file, String contentType) {
		File destFile = new File(servletContext.getRealPath(path));
		try {
			FileUtils.moveFile(file, destFile);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Override
	public String getUrl(String path) {
		Setting setting = SystemUtils.getSetting(); 
		return setting.getSiteUrl() + path;
	}

	@Override
	public String getServletPath( String path ) {
		return servletContext.getRealPath(path);
	}

}