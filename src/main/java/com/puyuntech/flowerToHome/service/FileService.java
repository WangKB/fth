package com.puyuntech.flowerToHome.service;

import org.springframework.web.multipart.MultipartFile;

import com.puyuntech.flowerToHome.FileType;

/**
 * 
 * Service - 文件. Created on 2015-10-14 下午1:40:25
 * 
 * @author 王凯斌
 */
public interface FileService {

	/**
	 * 
	 * 文件验证.
	 * 
	 * author: 王凯斌 date: 2015-10-14 下午1:40:41
	 * 
	 * @param fileType
	 *            文件类型
	 * @param multipartFile
	 *            上传文件
	 * @return 文件验证是否通过
	 */
	boolean isValid(FileType fileType, MultipartFile multipartFile);

	/**
	 * 
	 * 文件上传.
	 * 
	 * author: 王凯斌 date: 2015-10-14 下午1:41:14
	 * 
	 * @param fileType
	 *            文件类型
	 * @param multipartFile
	 *            上传文件
	 * @param async
	 *            是否异步
	 * @return 访问URL
	 */
	String upload(FileType fileType, MultipartFile multipartFile, boolean async);

	/**
	 * 
	 * 文件上传(异步).
	 * 
	 * author: 王凯斌 date: 2015-10-14 下午1:41:45
	 * 
	 * @param fileType
	 *            文件类型
	 * @param multipartFile
	 *            上传文件
	 * @return 访问URL
	 */
	String upload(FileType fileType, MultipartFile multipartFile);

	/**
	 * 
	 * 文件上传至本地(同步).
	 * 
	 * author: 王凯斌 date: 2015-10-14 下午1:42:10
	 * 
	 * @param fileType
	 *            文件类型
	 * @param multipartFile
	 *            上传文件
	 * @return 路径
	 */
	String uploadLocal(FileType fileType, MultipartFile multipartFile);
	
	String qiNiuUpload(String imageType, MultipartFile multipartFile);

}