package com.puyuntech.flowerToHome.dao.impl;

import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.dao.CommentDao;
import com.puyuntech.flowerToHome.entity.Comment;

/**
 * 
 *Comment Dao . 
 * Created on 2015-9-24 下午4:29:17 
 * @author 施长成
 */
@Repository("commentDaoImpl")
public class CommentDaoImpl extends BaseDaoImpl<Comment, Integer> implements CommentDao {
	
}
