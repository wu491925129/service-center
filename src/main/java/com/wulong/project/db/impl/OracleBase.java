package com.wulong.project.db.impl;

import com.wulong.project.db.DBConstants;
import com.wulong.project.db.DataBase;

/**
 * @Author: wulong
 * @Date: 2019/3/14 10:53
 * @Email: 491925129@qq.com
 */
public class OracleBase implements DataBase {

    @Override
    public String getDriver() {
        return DBConstants.ORACLE_DRIVER;
    }

    @Override
    public String getDbType() {
        return DBConstants.ORACLE;
    }

    @Override
    public String getLinkSql() {
        return DBConstants.ORACLE_VALIDATIONQUERY;
    }
}
