package com.wulong.project.db.impl;

import com.wulong.project.db.DBConstants;
import com.wulong.project.db.DataBase;

/**
 * @Author: wulong
 * @Date: 2019/3/14 17:59
 * @Email: 491925129@qq.com
 */
public class SQLServerBase implements DataBase {

    @Override
    public String getDriver() {
        return DBConstants.SQL_SERVER_DRIVER;
    }

    @Override
    public String getDbType() {
        return DBConstants.SQL_SERVER;
    }

    @Override
    public String getLinkSql() {
        return DBConstants.SQL_SERVER_VALIDATIONQUERY;
    }
}
