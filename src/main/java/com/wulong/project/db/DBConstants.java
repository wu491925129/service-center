package com.wulong.project.db;

/**
 * @Author: wulong
 * @Date: 2019/3/14 17:07
 * @Email: 491925129@qq.com
 *
 * DB常量类
 */
public class DBConstants {

    /**
     * 数据库类型：mysql
     */
    public final static String MYSQL = "MySQL";

    /**
     * 数据库类型：oracle
     */
    public final static String ORACLE = "Oracle";

    /**
     * 数据库类型：SQLServer
     */
    public final static String SQL_SERVER = "SQLServer";

    /**
     * mysql数据库driver
     */
    public final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    /**
     * oracle数据库driver
     */
    public final static String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";

    /**
     * SQLServer数据库driver
     */
    public final static String SQL_SERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /**
     * mysql测试连接
     */
    public final static String MYSQL_VALIDATIONQUERY = "select 1";

    /**
     * oracle测试连接
     */
    public final static String ORACLE_VALIDATIONQUERY = "select 1 from dual";

    /**
     * SQLServer测试连接
     */
    public final static String SQL_SERVER_VALIDATIONQUERY = "select 1";

}
