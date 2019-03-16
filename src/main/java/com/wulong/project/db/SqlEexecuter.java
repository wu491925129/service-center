package com.wulong.project.db;

import com.wulong.project.model.DataInfo;
import com.wulong.project.model.DbInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wulong
 * @Date: 2019/3/14 19:15
 * @Email: 491925129@qq.com
 *
 * sql执行类
 */
public class SqlEexecuter {

    private SqlEexecuter() {

    }

    /**
     * 日志输出
     */
    private static final Logger logger = LoggerFactory.getLogger(SqlEexecuter.class);

    private volatile static SqlEexecuter sqlEexecuter = null;

    /**
     * 使用单例确保全局只有一个访问点
     * @return
     */
    public static SqlEexecuter getInstance() {
        if (sqlEexecuter == null) {
            // 加锁
            synchronized (SqlEexecuter.class) {
                if (sqlEexecuter == null) {
                    sqlEexecuter = new SqlEexecuter();
                }
            }
        }
        return sqlEexecuter;
    }

    /**
     * 获取数据项结果
     * @param dataInfo
     * @return
     */
    public List<Map<String, Object>> getResult(DataInfo dataInfo, DbInfo dbInfo){
        List<Map<String, Object>> list = executeQuery(dataInfo.getDataSql(),dbInfo);
        return list;
    }

    /**
     * 执行sql
     * @param sql
     * @param dbInfo
     * @return
     */
    public List<Map<String, Object>> executeQuery(String sql,DbInfo dbInfo){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement pstat = null;
        try {
            connection = DataBaseFactory.getInstance().getConnection(dbInfo);
            pstat = connection.prepareStatement(sql);
            resultSet = pstat.executeQuery();
            logger.info(sql+" - 执行完毕");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int cols_len = metaData.getColumnCount();
            while(resultSet.next()){
                Map<String, Object> map = new HashMap<>();
                String colName;
                for(int i = 0; i < cols_len; i++){
                    map.put(colName = metaData.getColumnLabel(i + 1), resultSet.getString(colName));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            logger.info(sql+" - 执行异常");
            e.printStackTrace();
        } finally {
            if(resultSet != null){
                try {resultSet.close();} catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(pstat != null){
                try {pstat.close();} catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {connection.close();} catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

}
