package com.corey.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户类。
 *
 * @author mingxu.huang
 * @since 1.0
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity implements Serializable {

    /**
     * <li>uuid :UUID，流水号，主要用来与外部系统数据交互时的唯一性主键 </li>
     */
    @Column(nullable = false, length = 50)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    /**
     * <li>orgId :所属组织ID </li>
     */
    @Column(nullable = false, name = "organization_id")
    private Long orgId;

   /* *//**
     * <li>roleId :角色ID </li>
     *//*
    @Column(nullable = true, name = "role_id")
    private Long roleId;
*/
    /**
     * <li>username :用户账号 </li>
     */
    @Column(nullable = false, length = 30)
    private String username;

    /**
     * <li>password :密码 </li>
     */
    @Column(nullable = false, length = 100)
    private String password;

    /**
     * <li>realName :用户真实姓名 </li>
     */
    @Column(nullable = false, name = "real_name", length = 16)
    private String realName;

    /**
     * <li>telephone :手机号码 </li>
     */
    @Column(nullable = true, length = 11)
    private String telephone;

    /**
     * <li>email :email </li>
     */
    @Column(nullable = true, length = 100)
    private String email;

    /**
     * <li>sex :性别 </li>
     */
    @Column(nullable = true, length = 2)
    private String sex;

    /**
     * <li>phone :办公电话 </li>
     */
    @Column(nullable = true, length = 11)
    private String phone;

    /**
     * <li>userType :用户类型 </li>
     */
    @Column(nullable = false, name = "user_type")
    private Integer userType;

    /**
     * <p/>
     * <li>启用标志</li>
     * <li>Y:已启用</li>
     * <li>N:未启用</li>
     * <li>允许为空:N</li>
     */
    @Column(nullable = false, name = "is_enabled")
    private String isEnabled;

    /**
     * <li>department: 用户所属类型</li>
     */
    @Column(nullable = false)
    private String department;

    /**
     * <li>belongPowerPlant: 用户所属电厂</li>
     */
    @Column(nullable = true,length = 1,name = "belong_power_plant")
    private String powerPlantType;

    /**
     * <li>powertId: 用户所在电厂ID</li>
     */
    @Column(nullable=true,length = 20,name = "power_plant_id")
    private Long powertId;

    /**
     * <li> user 的构造函数. </li>
     */
    public User () {

        super();
    }

    /**
     * <li> user 的构造函数. </li>
     *
     * @param createUserId the create user id
     * @param createDate   the create date
     * @param updateUserId the update user id
     * @param updateDate   the update date
     * @param deleteFlag   the delete flag
     * @param orgId        the org id
     * @param username     the username
     * @param password     the password
     * @param realName     the real name
     * @param telephone    the telephone
     * @param email        the email
     * @param sex          the sex
     * @param phone        the phone
     */
    public User ( Long createUserId, Date createDate, Long updateUserId, Date updateDate, String deleteFlag, Long orgId,
                  String username, String password, String realName, String telephone, String email, String sex,
                  String phone) {

        super(createUserId, createDate, updateUserId, updateDate, deleteFlag);
        this.orgId = orgId;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.telephone = telephone;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
    }

    /**
     * org id 的getter方法
     *
     * @return 返回 org id
     */
    public Long getOrgId() {

        return orgId;
    }

    /**
     * org id 的setter方法
     *
     * @param orgId the org id
     */
    public void setOrgId(Long orgId) {

        this.orgId = orgId;
    }

    /**
     * role id 的getter方法
     * @return 返回 role id
     */
    /*public Long getRoleId() {

        return roleId;
    }*/

    /**
     * role id 的setter方法
     *
     * @param roleId the role id
     */
    /*public void setRoleId(Long roleId) {

        this.roleId = roleId;
    }*/

    /**
     * uuid 的getter方法
     *
     * @return 返回 uuid
     */
    public String getUuid() {

        return uuid;
    }

    /**
     * uuid 的setter方法
     *
     * @param uuid the uuid
     */
    public void setUuid(String uuid) {

        this.uuid = uuid;
    }

    /**
     * username 的getter方法
     *
     * @return 返回 username
     */
    public String getUsername() {

        return username;
    }

    /**
     * username 的setter方法
     *
     * @param username the username
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * password 的getter方法
     *
     * @return 返回 password
     */
    public String getPassword() {

        return password;
    }

    /**
     * password 的setter方法
     *
     * @param password the password
     */
    public void setPassword(String password) {

        this.password = password;
    }

    /**
     * real name 的getter方法
     *
     * @return 返回 real name
     */
    public String getRealName() {

        return realName;
    }

    /**
     * real name 的setter方法
     *
     * @param realName the real name
     */
    public void setRealName(String realName) {

        this.realName = realName;
    }

    /**
     * email 的getter方法
     *
     * @return 返回 email
     */
    public String getEmail() {

        return email;
    }

    /**
     * email 的setter方法
     *
     * @param email the email
     */
    public void setEmail(String email) {

        this.email = email;
    }

    /**
     * telephone 的getter方法
     *
     * @return 返回 telephone
     */
    public String getTelephone() {

        return telephone;
    }

    /**
     * telephone 的setter方法
     *
     * @param telephone the telephone
     */
    public void setTelephone(String telephone) {

        this.telephone = telephone;
    }

    /**
     * sex 的getter方法
     *
     * @return 返回 sex
     */
    public String getSex() {

        return sex;
    }

    /**
     * sex 的setter方法
     *
     * @param sex the sex
     */
    public void setSex(String sex) {

        this.sex = sex;
    }

    /**
     * phone 的getter方法
     *
     * @return 返回 phone
     */
    public String getPhone() {

        return phone;
    }

    /**
     * phone 的setter方法
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {

        this.phone = phone;
    }

    /**
     * user type 的getter方法
     *
     * @return 返回 user type
     */
    public Integer getUserType() {

        return userType;
    }

    /**
     * user type 的setter方法
     *
     * @param userType the user type
     */
    public void setUserType(Integer userType) {

        this.userType = userType;
    }

    /**
     * is enabled 的getter方法
     *
     * @return 返回 is enabled
     */
    public String getIsEnabled() {
        return isEnabled;
    }

    /**
     * is enabled 的setter方法
     *
     * @param isEnabled the is enabled
     */
    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getPowertId() {
        return powertId;
    }

    public void setPowertId(Long powertId) {
        this.powertId = powertId;
    }

    public String getPowerPlantType() {
        return powerPlantType;
    }

    public void setPowerPlantType(String powerPlantType) {
        this.powerPlantType = powerPlantType;
    }
}
