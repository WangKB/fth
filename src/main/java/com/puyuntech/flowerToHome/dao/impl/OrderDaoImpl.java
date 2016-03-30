package com.puyuntech.flowerToHome.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.puyuntech.flowerToHome.dao.OrderDao;
import com.puyuntech.flowerToHome.entity.Order;

/**
 * 
 * order Dao. Created on 2015-9-6 上午10:40:22
 * 
 * @author 王凯斌
 */
@Repository("orderDaoImpl")
public class OrderDaoImpl extends BaseDaoImpl<Order, Integer> implements
OrderDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {  
        return jdbcTemplate;  
    }  

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Integer changeStatus(Order order, Order.Status status) {

		Integer id = order.getId();
		Integer statusCode = status.getId();
		
		jdbcTemplate.execute( "{call proc_setOrderStatus("+id+","+statusCode+")}");
		return 1;
	}
}
