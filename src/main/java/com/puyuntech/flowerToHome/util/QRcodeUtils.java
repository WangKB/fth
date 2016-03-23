package com.puyuntech.flowerToHome.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 
 * 二维码 工具类 . 
 * Created on 2015-11-23 下午1:50:55 
 * @author 王凯斌
 */
public class QRcodeUtils {
	
	protected static final Logger logger = LoggerFactory.getLogger( QRcodeUtils.class );
	
	/**
	 * 
	 * 生存二维码.
	 * <p>方法详细说明,如果要换行请使用<br>标签</p>
	 * <br>
	 * author: 王凯斌
	 *   date: 2015-11-23 下午2:04:18
	 * @param json
	 * @param filePath
	 * @param fileName
	 * @param width
	 * @param height
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
    public static String Encode(JSONObject json,String filePath,String fileName,int width,int height) throws WriterException, IOException {  
        String content = json.toString();// 内容  
        if(json.isNullObject() || filePath.isEmpty() || fileName.isEmpty()  || width==0 || height==0){
        	filePath = "D://";
        	fileName = "error.png";
        	width = 200; // 图像宽度  
        	height = 200; // 图像高度  
        	json.put("error","错误的二维码");  
        }
        String format = "png";// 图像类型  
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
        Path path = FileSystems.getDefault().getPath(filePath, fileName);  
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像  
        System.out.println("输出成功.");
		return filePath+fileName;  
	}
    
    /**
     * 
     * 生成二维码图片的公用方法.
     * <p>方法详细说明,如果要换行请使用<br>标签</p>
     * <br>
     * author: 王凯斌
     *   date: 2015-11-23 下午2:14:10
     * @param data
     * @param filePath
     * @param fileName
     * @param width
     * @param height
     * @return
     */
    public static String generalEncode( Map<String, Object> data , String filePath , String fileName , int width , int height ){
    	JSONObject json = new JSONObject();
    	Iterator<Entry<String, Object>>  iterator = data.entrySet().iterator();
    	
    	while( iterator.hasNext() ){
    		Map.Entry<String, Object> entry = iterator.next();
    		json.put( entry.getKey() , entry.getValue());
    	}

        System.out.println( json );

    	String content = json.toString();
    	
         if(json.isNullObject() || filePath.isEmpty() || fileName.isEmpty()  || width==0 || height==0){
         	return null;
         }
         
         String format = "png";// 图像类型  
         Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
         hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
         BitMatrix bitMatrix;
         File file = new File( filePath );
         if( !file.exists() ){
        	 file.mkdirs();
         }
         
		try {
			bitMatrix = new MultiFormatWriter().encode(content,  BarcodeFormat.QR_CODE, width, height, hints);
			 Path path = FileSystems.getDefault().getPath(filePath, fileName);  
	         MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像  
			
		} catch (WriterException e) {
			logger.error("product create qrenCode is error in writeException" +e);
		}// 生成矩阵  
		catch (IOException e) {
			logger.error("product create qrenCode is error in ioException" +e);
		}
        
 		return filePath+fileName;  
    }
}
