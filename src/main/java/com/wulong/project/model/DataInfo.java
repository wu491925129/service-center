package com.wulong.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "data_info")
public class DataInfo {
    /**
     * 主键
     */
    @Id
    @Column(name = "data_id")
    private String dataId;

    /**
     * 数据源ID
     */
    @Column(name = "db_id")
    private String dbId;

    /**
     * 数据名称
     */
    @Column(name = "data_name")
    private String dataName;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * sql串
     */
    @Column(name = "data_sql")
    private String dataSql;

    /**
     * 数据
     */
    private String result;

    private String status;

    /**
     * 获取主键
     *
     * @return data_id - 主键
     */
    public String getDataId() {
        return dataId;
    }

    /**
     * 设置主键
     *
     * @param dataId 主键
     */
    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    /**
     * 获取数据源ID
     *
     * @return db_id - 数据源ID
     */
    public String getDbId() {
        return dbId;
    }

    /**
     * 设置数据源ID
     *
     * @param dbId 数据源ID
     */
    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    /**
     * 获取数据名称
     *
     * @return data_name - 数据名称
     */
    public String getDataName() {
        return dataName;
    }

    /**
     * 设置数据名称
     *
     * @param dataName 数据名称
     */
    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取sql串
     *
     * @return data_sql - sql串
     */
    public String getDataSql() {
        return dataSql;
    }

    /**
     * 设置sql串
     *
     * @param dataSql sql串
     */
    public void setDataSql(String dataSql) {
        this.dataSql = dataSql;
    }

    /**
     * 获取数据
     *
     * @return result - 数据
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置数据
     *
     * @param result 数据
     */
    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}