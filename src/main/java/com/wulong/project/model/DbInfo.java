package com.wulong.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "db_info")
public class DbInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 数据库描述
     */
    @Column(name = "db_name")
    private String dbName;

    /**
     * 数据库类型
     */
    @Column(name = "db_type")
    private String dbType;

    /**
     * 数据库地址
     */
    @Column(name = "db_address")
    private String dbAddress;

    /**
     * 数据库用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private String status;

    /**
     * 操作时间
     */
    @Column(name = "op_time")
    private Date opTime;

    /**
     * 级别
     */
    private String level;
    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取数据库描述
     *
     * @return db_name - 数据库描述
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * 设置数据库描述
     *
     * @param dbName 数据库描述
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * 获取数据库类型
     *
     * @return db_type - 数据库类型
     */
    public String getDbType() {
        return dbType;
    }

    /**
     * 设置数据库类型
     *
     * @param dbType 数据库类型
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    /**
     * 获取数据库地址
     *
     * @return db_address - 数据库地址
     */
    public String getDbAddress() {
        return dbAddress;
    }

    /**
     * 设置数据库地址
     *
     * @param dbAddress 数据库地址
     */
    public void setDbAddress(String dbAddress) {
        this.dbAddress = dbAddress;
    }

    /**
     * 获取数据库用户名
     *
     * @return user_name - 数据库用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置数据库用户名
     *
     * @param userName 数据库用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取操作时间
     *
     * @return op_time - 操作时间
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     * 设置操作时间
     *
     * @param opTime 操作时间
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}