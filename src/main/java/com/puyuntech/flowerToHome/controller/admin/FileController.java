package com.puyuntech.flowerToHome.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.puyuntech.flowerToHome.FileType;
import com.puyuntech.flowerToHome.enmu.Message;
import com.puyuntech.flowerToHome.service.FileService;

/**
 * 
 * Controller - 文件. Created on 2015-10-14 上午9:46:30
 * 
 * @author 王凯斌
 */
@Controller("adminFileController")
@RequestMapping("/admin/file")
public class FileController extends BaseController {

	/**
	 * 导入service对象
	 */
	@Resource(name = "fileServiceImpl")
	private FileService fileService;

	/**
	 * 
	 * 上传文件.
	 * 
	 * author: 王凯斌 date: 2015-10-14 上午9:46:57
	 * 
	 * @param fileType
	 *            文件类型
	 * @param file
	 *            文件对象
	 * @return 数据模型
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> upload(FileType fileType, MultipartFile file,String imageType) {

		/**
		 * 创建模型对象
		 */
		Map<String, Object> data = new HashMap<String, Object>();

		/**
		 * 判断非空，为空则返回错误信息
		 */
		if (fileType == null || file == null || file.isEmpty()) {
			data.put("message", ERROR_MESSAGE);
			data.put("state", message("admin.message.error"));
			return data;
		}

		/**
		 * 验证文件类型以及文件
		 */
		if (!fileService.isValid(fileType, file)) {
			data.put("message", Message.warn("admin.upload.invalid"));
			data.put("state", message("admin.upload.invalid"));
			return data;
		}

		/**
		 * 上传文件并且返回服务器路径
		 */
		String url="";
		Boolean flag = StringUtils.isNotEmpty(imageType);
		if(flag&&FileType.image.equals(fileType)){
			url = fileService.qiNiuUpload(imageType, file);
		}else{
			url = fileService.upload(fileType, file, false);
		}
			
		/**
		 * 判断路径是否为空
		 */
		if (StringUtils.isEmpty(url)) {
			data.put("message", Message.warn("admin.upload.error"));
			data.put("state", message("admin.upload.error"));
			return data;
		}

		/**
		 * 将上传信息、状态以及路径写入数据模型
		 */
		data.put("message", SUCCESS_MESSAGE);
		data.put("state", "SUCCESS");
		data.put("url", url);
		return data;
	}

}