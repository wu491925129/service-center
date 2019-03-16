package com.wulong.project.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.wulong.project.model.DbInfo;

import java.sql.SQLException;

/**
 * @Author: wulong
 * @Date: 2019/3/14 17:48
 * @Email: 491925129@qq.com
 *
 * 数据源接口
 */
public interface DataBase {

    /**
     * 返回数据库驱动
     * @return
     */
    String getDriver();

    /**
     * 返回测试连接sql
     * @return
     */
    String getLinkSql();

    /**
     * 返回数据库类型
     * @return
     */
    String getDbType();

    /**
     * 默认实现 返回dataSource
     * @return
     */
    default DruidDataSource getDataSource(DbInfo dbInfo){
        // 创建这个连接
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.getDriver());
        dataSource.setUrl(dbInfo.getDbAddress());
        dataSource.setUsername(dbInfo.getUserName());
        dataSource.setPassword(dbInfo.getPassword());
        dataSource.setMaxWait(10000);
        // 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        try {
            dataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 根据数据库等级进行连接配置
        switch (dbInfo.getLevel()) {
            case "1":
                dataSource.setInitialSize(10);
                dataSource.setMinIdle(5);
                dataSource.setMaxActive(50);
                break;
            case "2":
                dataSource.setInitialSize(20);
                dataSource.setMinIdle(10);
                dataSource.setMaxActive(100);
                break;
            case "3":
                dataSource.setInitialSize(30);
                dataSource.setMinIdle(15);
                dataSource.setMaxActive(150);
                break;
            default:
                dataSource.setInitialSize(5);
                dataSource.setMinIdle(2);
                dataSource.setMaxActive(20);
                break;
        }
        return dataSource;
    }

}
