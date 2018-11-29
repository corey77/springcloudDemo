package com.corey.dao;

import com.corey.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2018/11/28</li>\n
 */
@Component
public class TestDAO implements Serializable {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    public User get(){
        JdbcTemplate jdbcTemplate=getJdbcTemplate();
        User user=jdbcTemplate.queryForObject( "select * from user where id=1",User.class );
        return user;
    }
    public JdbcTemplate getJdbcTemplate(){
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        return null;
    }
}
