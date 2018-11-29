package com.corey.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * <p/>
 * <li>Description:PO基类</li>
 * <li>@author: Corey </li>
 * <li>Date: 2016/7/4 0004 </li>
 * <li>@version: 1.0.0 </li>
 */
@MappedSuperclass
public class BaseEntity extends BaseTable {

    /**
     * <p/>
     * <li>创建者ID</li>
     * <li>允许为空:N</li>
     */
    @Column(nullable = false, name = "create_user_id")
    public Long createUserId;

    /**
     * <p/>
     * <li>创建日期</li>
     * <li>允许为空:N</li>
     */
    @Column(nullable = false, name = "create_date")
    private Date createDate;

    /**
     * <p/>
     * <li>更新者ID</li>
     * <li>允许为空:Y</li>
     */
    @Column(name = "update_user_id")
    private Long updateUserId;

    /**
     * <p/>
     * <li>更新日期</li>
     * <li>允许为空:Y</li>
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * <p/>
     * <li>删除标记</li>
     * <li>Y:已删除</li>
     * <li>N:未删除</li>
     * <li>允许为空:N</li>
     */
    @Column(nullable = false, name = "delete_flag")
    private String deleteFlag;

    /**
     * <li> BaseEntity的构造函数. </li>
     */
    public BaseEntity () {

    }

    /**
     * <li> BaseEntity的构造函数. </li>
     *
     * @param createUserId the create user id
     * @param createDate   the create date
     * @param updateUserId the update user id
     * @param updateDate   the update date
     * @param deleteFlag   the delete flag
     */
    public BaseEntity ( Long createUserId, Date createDate, Long updateUserId, Date updateDate, String deleteFlag) {

        this.createUserId = createUserId;
        this.createDate = createDate;
        this.updateUserId = updateUserId;
        this.updateDate = updateDate;
        this.deleteFlag = deleteFlag;
    }

    /**
     * <p/>
     * <li>创建日期</li>
     * <li>允许为空:N</li>
     *
     * @return 创建日期 create date
     */
    public Date getCreateDate() {

        return createDate;
    }

    /**
     * <p/>
     * <li>创建日期</li>
     * <li>允许为空:N</li>
     *
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {

        this.createDate = createDate;
    }

    /**
     * <p/>
     * <li>创建者ID</li>
     * <li>允许为空:N</li>
     *
     * @return 创建者ID create user id
     */
    public Long getCreateUserId() {

        return createUserId;
    }

    /**
     * <p/>
     * <li>创建者ID</li>
     * <li>允许为空:N</li>
     *
     * @param createUserId 创建者ID
     */
    public void setCreateUserId(Long createUserId) {

        this.createUserId = createUserId;
    }

    /**
     * <p/>
     * <li>删除标记</li>
     * <li>Y:已删除</li>
     * <li>N:未删除</li>
     * <li>允许为空:N</li>
     *
     * @return 删除标记 delete flag
     */
    public String getDeleteFlag() {

        return deleteFlag;
    }

    /**
     * <p/>
     * <li>删除标记</li>
     * <li>Y:已删除</li>
     * <li>N:未删除</li>
     * <li>允许为空:N</li>
     *
     * @param deleteFlag 删除标记
     */
    public void setDeleteFlag(String deleteFlag) {

        this.deleteFlag = deleteFlag;
    }

    /**
     * <p/>
     * <li>更新日期</li>
     * <li>允许为空:Y</li>
     *
     * @return 更新日期 update date
     */
    public Date getUpdateDate() {

        return updateDate;
    }

    /**
     * <p/>
     * <li>更新日期</li>
     * <li>允许为空:Y</li>
     *
     * @param updateDate 更新日期
     */
    public void setUpdateDate(Date updateDate) {

        this.updateDate = updateDate;
    }

    /**
     * <p/>
     * <li>更新者ID</li>
     * <li>允许为空:Y</li>
     *
     * @return 更新者ID update user id
     */
    public Long getUpdateUserId() {

        return updateUserId;
    }

    /**
     * <p/>
     * <li>更新者ID</li>
     * <li>允许为空:Y</li>
     *
     * @param updateUserId 更新者ID
     */
    public void setUpdateUserId(Long updateUserId) {

        this.updateUserId = updateUserId;
    }
}

//~ Formatted by Jindent --- http://www.jindent.com
