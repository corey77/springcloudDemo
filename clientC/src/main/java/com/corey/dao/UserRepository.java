package com.corey.dao;

import com.corey.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * “用户信息”的数据持久化操作。
 * Created by mingxu.huang  on 2016.6.30
 *
 * @since 1.0.0
 */
@Repository
@SuppressWarnings("all")
public interface UserRepository extends JpaRepository<User, Long>, Serializable {
    /**
     * 根据指定的“用户名”查找用户信息。
     *
     * @param username 用户名
     * @return 查找到的用户信息 ，如果没有找到则返回{@code null}
     */
    @Query("select a from User a where a.username =?1 and a.deleteFlag = 'N'")
    User findByUsername ( String username );

    /**
     * 查询所有普通用户
     */
    @Override
    @Query("select a from User a where a.deleteFlag = 'N' and a.userType = 5")
    List<User> findAll ();

    /**
     * <li>Description: findByOrgId </li>
     *
     * @param orgId orgId
     * @return List<user>
     */
    @Query("select a from User a where a.orgId =?1 and a.deleteFlag = 'N'")
    List<User> findByOrgId ( Long orgId );

    @Override
    @Query("select a from User a where a.id =?1 and a.deleteFlag = 'N'")
    User findOne ( Long id );

    /**
     * 特殊说明：
     * 分页动态查询所有用户信息
     *
     * @param spec 查询参数
     * @param pageable 分页参数
     * @return  Page<user>
     */
    Page<User> findAll ( Specification< User > spec, Pageable pageable );

    @Override
    User save ( User user );

}
