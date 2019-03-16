package com.wulong.project.db.impl;

import com.wulong.project.db.DBConstants;
import com.wulong.project.db.DataBase;

/**
 * @Author: wulong
 * @Date: 2019/3/14 10:53
 * @Email: 491925129@qq.com
 */
public class MySQLBase implements DataBase {

    @Override
    public String getDriver() {
        return DBConstants.MYSQL_DRIVER;
    }

    @Override
    public String getDbType() {
        return DBConstants.MYSQL;
    }

    @Override
    public String getLinkSql() {
        return DBConstants.MYSQL_VALIDATIONQUERY;
    }
}
