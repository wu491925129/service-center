package com.wulong.project.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.wulong.project.db.impl.MySQLBase;
import com.wulong.project.db.impl.OracleBase;
import com.wulong.project.db.impl.SQLServerBase;
import com.wulong.project.model.DbInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wulong
 * @Date: 2019/3/14 10:51
 * @Email: 491925129@qq.com
 *
 * 数据库执行工厂
 */
public class DataBaseFactory {

    /**
     * 日志输出
     */
    private static final Logger logger = LoggerFactory.getLogger(DataBaseFactory.class);

    /**
     * 数据库池
     */
    private final static Map<String, DruidDataSource> dbMap = new HashMap<>();

    private volatile static DataBaseFactory dataBaseFactory;

    private DataBaseFactory() {

    }

    /**
     * 使用单例确保全局只有一个访问点
     * @return
     */
    public static DataBaseFactory getInstance() {
        if (dataBaseFactory == null) {
            // 加锁
            synchronized (DataBaseFactory.class) {
                if (dataBaseFactory == null) {
                    dataBaseFactory = new DataBaseFactory();
                }
            }
        }
        return dataBaseFactory;
    }

    /**
     * 获取相应的数据库信息
     * @param dbInfo
     * @return
     */
    private DataBase getDataBase(DbInfo dbInfo) {
        DataBase database = null;
        switch (dbInfo.getDbType()) {
            case DBConstants.MYSQL:
                database = new MySQLBase();
                break;
            case DBConstants.ORACLE:
                database = new OracleBase();
                break;
            case DBConstants.SQL_SERVER:
                database = new SQLServerBase();
                break;
            default:
                break;
        }
        return database;
    }

    /**
     * 创建数据源
     * @param dbInfo
     * @return
     */
    public DruidDataSource createDataSource(DbInfo dbInfo){
        logger.info("创建数据源："+dbInfo.getDbName());
        if (dbMap.containsKey(dbInfo.getId())) {
            logger.info(dbInfo.getDbName() + ":数据源已存在，从连接池中获取");
            return dbMap.get(dbInfo.getId());
        } else {
            DruidDataSource dataSource = getDataBase(dbInfo).getDataSource(dbInfo);
            logger.info(dbInfo.getDbName()+" - 创建成功");
            dbMap.put(dbInfo.getId(),dataSource);
            return dataSource;
        }
    }

    /**
     * 获取数据库连接
     * @param dbInfo
     * @return
     */
    public Connection getConnection(DbInfo dbInfo) {
        Connection connection = null;
        if (!dbMap.containsKey(dbInfo.getId())) {
            createDataSource(dbInfo);
        }
        try {
            DruidDataSource dataSource = dbMap.get(dbInfo.getId());
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            // 移除该数据源
            removeDataSource(dbInfo);
            logger.info(dbInfo.getDbName() + " -无法获取连接！");
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 移除数据源
     * @param dbInfo
     */
    private void removeDataSource(DbInfo dbInfo) {
        DruidDataSource dataSource = dbMap.get(dbInfo.getId());
        if(dataSource != null){
            dataSource.close();
            dbMap.remove(dbInfo.getId());
        }
        logger.info(dbInfo.getDbName()+" - 移除成功");
    }

    /**
     * 测试连接
     * @param dbInfo
     * @return
     */
    public boolean testConnection(DbInfo dbInfo) {
        Connection connection = getConnection(dbInfo);
        if (connection == null) {
            return false;
        }
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(getDataBase(dbInfo).getLinkSql());
            if (rs.next()) {
                logger.info(getDataBase(dbInfo).getLinkSql() + " ----> " + rs.getString(1));
            }
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
