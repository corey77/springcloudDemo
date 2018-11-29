package com.corey.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * <p/>
 * <li>Description:表基类</li>
 * <li>@author: Corey </li>
 * <li>Date: 2016/7/18 0018 </li>
 * <li>@version: 1.0.0 </li>
 */
@MappedSuperclass
public class BaseTable {
    
    /**
     * <p/>
     * <li>主键ID</li>
     * <li>允许为空:N</li>
     */
    @Id
    @GeneratedValue
    private Long id;
    
    /**
     * <p/>
     * <li>主键ID</li>
     * <li>允许为空:N</li>
     *
     * @return 主键ID
     */
    public Long getId() {
        
        return id;
    }
    
    /**
     * <p/>
     * <li>主键ID</li>
     * <li>允许为空:N</li>
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        
        this.id = id;
    }
}
