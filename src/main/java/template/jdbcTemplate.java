package template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class jdbcTemplate {

    public int updateTemplate(String sql, Object[] params) {
        int rows = 0;
        // 一个数据库连接
        Connection conn = null;
        // 预编译对象
        PreparedStatement pre = null;
        // 结果集
        ResultSet resultSet = null;

        try {
            conn = DBUtil.getConnection();
            pre = conn.prepareStatement(sql);
            // 设置sql所需要的参数
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pre.setObject(i + 1, params[i]);
                }
            }
            resultSet = pre.getResultSet();

            while (resultSet.next()) {
                rows++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }


    public <T> List<T> queryForList(RowMapper<T> mapper, String sql, Object[] params) {
        List<T> returnResult = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);

            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pre.setObject(i + 1, params[i]);
                }
                ResultSet resultSet = pre.executeQuery();
                int rows = 0;
                while (resultSet.next()) {
                    rows++;
                    returnResult.add(mapper.mappingRows(resultSet, rows));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnResult;
    }

}
