package com.puyuntech.flowerToHome.service;

import java.util.Map;

/***
 * 
 * @ClassName: StaticService
 * @Description: TODO 生成静态文件的Service 方法【未完成】 
 * @date: 2015-8-12 下午2:25:17 
 * @author: 王凯斌
 */
public interface StaticService {

	/**
	 * 生成静态
	 * 
	 * @param templatePath
	 *            模板文件路径
	 * @param staticPath
	 *            静态文件路径
	 * @param model
	 *            数据
	 * @return 生成数量
	 */
	int generate(String templatePath, String staticPath, Map<String, Object> model);

	/**
	 * 生成静态
	 * 
	 * @param article
	 *            文章
	 * @return 生成数量
	 */
//	int generate(Article article);

	/**
	 * 生成静态
	 * 
	 * @param goods
	 *            货品
	 * @return 生成数量
	 */
//	int generate(Goods goods);

	/**
	 * 生成文章静态
	 * 
	 * @param articleCategory
	 *            文章分类
	 * @param isPublication
	 *            是否发布
	 * @param generateMethod
	 *            静态生成方式
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return 生成数量
	 */
//	int generateArticle(ArticleCategory articleCategory, Boolean isPublication, Article.GenerateMethod generateMethod, Date beginDate, Date endDate);

	/**
	 * 生成货品静态
	 * 
	 * @param productCategory
	 *            商品分类
	 * @param isMarketable
	 *            是否上架
	 * @param generateMethod
	 *            静态生成方式
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @return 生成数量
	 */
//	int generateGoods(ProductCategory productCategory, Boolean isMarketable, Goods.GenerateMethod generateMethod, Date beginDate, Date endDate);

	/**
	 * 
	 * 生成首页静态.
	 * <p>ycmall.xml 中的 index <br></p>
	 * <br>
	 * author: 王凯斌
	 *   date: 2015-8-17 下午2:35:13
	 * @return
	 */
	int generateIndex();

	/**
	 * 生成Sitemap
	 * 
	 * @return 生成数量
	 */
//	int generateSitemap();

	/**
	 * 
	 * 生成公用JS静态文件.
	 * <p>ycmall.xml 中的 shopCommonJs 和 adminCommonJs<br></p>
	 * <br>
	 * author: 王凯斌
	 *   date: 2015-8-17 下午2:34:17
	 * @return
	 */
	int generateOther();

	/**
	 * 生成所有静态
	 * 
	 * @return 生成数量
	 */
	int generateAll();

	/**
	 * 删除静态
	 * 
	 * @param staticPath
	 *            静态文件路径
	 * @return 删除数量
	 */
	int delete(String staticPath);

	/**
	 * 删除静态
	 * 
	 * @param article
	 *            文章
	 * @return 删除数量
	 */
//	int delete(Article article);

	/**
	 * 删除静态
	 * 
	 * @param goods
	 *            货品
	 * @return 删除数量
	 */
//	int delete(Goods goods);

	/**
	 * 删除首页静态
	 * 
	 * @return 删除数量
	 */
	int deleteIndex();

	/**
	 * 删除其它静态
	 * 
	 * @return 删除数量
	 */
	int deleteOther();

}