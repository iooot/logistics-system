package common.SqlHelper;


import com.domain.SqlCustom;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataService {
    private static final String LOCAL = "local";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static List<String> driverList;

    static {
        driverList = new ArrayList<>();
        driverList.add(DRIVER_NAME);
    }

    /**
     * 获取Connect对象
     *
     * @return 链接对象
     * @Param Dbid /config/DbResource.xml 里的id
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DruidService.getDs().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static Connection getConnection(String Dbid) {
        return getConnection();
    }

    /**
     * 获取数据库首行首位
     */
    public static String getFirstValue(String sql, String[] sqlParam) {
        return getFirstValue(LOCAL, sql, sqlParam);
    }

    /**
     * 获取数据库首行首位
     */
    public static String getFirstValue(String dbId, String sql, String[] sqlParam) {

        Connection con = null;
        PreparedStatement stmt = null;
        String str = null;
        ResultSet rs = null;
        if (dbId.equals(LOCAL)) {
            con = getConnection();
        } else {
            con = getConnection(dbId);
        }
        try {
            stmt = con.prepareStatement(sql);
            addCondition(stmt, sqlParam);
            rs = stmt.executeQuery();
            if (rs.next()) {
                str = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt, con);
        }
        return str;
    }

    /**
     * 执行INSERT UPDATE DELETE
     *
     * @Param sql 要执行的SQL语句
     * @Return 受影响行数
     */
    public static int execute(String sql) {
        Connection con = getConnection();
        return getEffectNum(con, sql);
    }

    /**
     * 执行INSERT UPDATE DELETE
     *
     * @Param config/DbResource.xml 配置的id
     * @Param 要执行的SQL语句
     * @Return 受影响行数
     */
    public static int execute(String dbId, String sql) {
        Connection con = getConnection(dbId);
        return getEffectNum(con, sql);
    }

    public static int execute(String sql, String[] sqlParam) {
        Connection con = getConnection(LOCAL);
        return getEffectNum(con, sql, sqlParam);
    }

    public static int execute(String dbId, String sql, String[] sqlParam) {
        Connection con = getConnection(dbId);
        return getEffectNum(con, sql, sqlParam);
    }

    private static int getEffectNum(Connection con, String sql) {
        return getEffectNum(con, sql, null);
    }

    private static int getEffectNum(Connection con, String sql, String[] sqlParam) {
        PreparedStatement stmt = null;
        int result = 0;
        int count = 0;
        try {
            count = sqlParam.length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (count == 0) {
                stmt = con.prepareStatement(sql);
                result = stmt.executeUpdate();
            } else {
                stmt = con.prepareStatement(sql);
                addCondition(stmt, sqlParam);
                result = stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(null, null, stmt, con);
        }
        return result;
    }

    private static void addCondition(PreparedStatement stmt, String[] sqlParam) throws Exception {
        if (sqlParam != null) {
            for (int i = 1; i <= sqlParam.length; i++) {
                stmt.setString(i, sqlParam[i - 1]);
            }
        }
    }

    /**
     * 执行SELECT ,获取DataTable集合,默认搜索local的库
     *
     * @Param config/DbResource.xml 配置的id
     * @Param 要执行的SQL语句
     * @Parma sqlParam sql语句的参数
     * @Return 返回查询的集合
     */
    public static List<Map<String, Object>> getDataTable(String sql, String[] sqlParam) {
        Connection con = getConnection();
        return loadRSToCollect(con, sql, sqlParam);
    }

    /**
     * 执行SELECT ,获取DataTable集合,默认搜索local的库
     *
     * @Param dbId config/DbResource.xml 配置的id
     * @Param sql 要执行的SQL语句
     * @Parma sqlParam sql语句的参数
     * @Return 返回查询的集合
     */
    public static List<Map<String, Object>> getDataTable(String dbId, String sql, String[] sqlParam) {
        Connection con = getConnection(dbId);
        return loadRSToCollect(con, sql, sqlParam);
    }


    private static List<Map<String, Object>> loadRSToCollect(Connection con, String sql, String... sqlParam) {
        List<Map<String, Object>> lists = null;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            addCondition(stmt, sqlParam);
            ResultSet rs = stmt.executeQuery();
            lists = DataTable.convertList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt, con);
        }
        return lists;
    }


    public static void close(Statement stmt, Connection conn) {
        close(null, null, stmt, conn);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        close(rs, null, stmt, conn);
    }

    public static void close(ResultSet rs, PreparedStatement pstmt, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行事务（自动提交，如果有受影响行数为0的sql，自动不生效）
     *
     * @Param sql 需要执行的SQL的数组
     * @Param paramList 每个sql需要添加的参数（无参数时，添加空的数组）
     */
    public static boolean runTransaction(List<SqlCustom> customList) {
        return runTransaction(LOCAL, customList);
    }

    /**
     * 执行事务（自动提交，如果有受影响行数为0的sql，自动不生效）,已经释放资源
     *
     * @Parma Dbid /config/DbResource.xml配置的id
     * @Param sql 需要执行的SQL的数组
     * @Param paramList 每个sql需要添加的参数（无参数时，添加空的数组）
     */
    public static boolean runTransaction(String Dbid, List<SqlCustom> customList) {
        boolean flag = false;
        Connection con = null;
        PreparedStatement stmt = null;
        String sql;
        SqlCustom custom;
        String[] condition;
        List<String> singleCustomConditionList = null;
        try {
            if (Dbid.equals(LOCAL)) {
                con = getConnection();
            } else {
                con = getConnection(Dbid);
            }
            con.setAutoCommit(false);//开启事务
            for (int i = 0; i < customList.size(); i++) {//一共在执行的sql个数
                //获得执行sql的对象
                custom = customList.get(i);
                singleCustomConditionList = custom.getCondition();
                sql = custom.getSql();
                if (sql.trim().isEmpty()) {
                    throw new Exception("事务执行SQL为空");
                }
                stmt = con.prepareStatement(sql);
                //设置参数
                condition = new String[singleCustomConditionList.size()];
                singleCustomConditionList.toArray(condition);
                //String[] condition = paramList.get(i);
                for (int y = 1; y <= condition.length; y++) {
                    stmt.setString(y, condition[y - 1]);
                }
                int result = stmt.executeUpdate();
                //  if (result == 0) {
                //throw new Exception("事务执行影响行数为0");
                //  }
            }
            con.commit();
            flag = true;
        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            close(stmt, con);
        }
        return flag;
    }


    /**
     * 根据表名获得Comment
     *
     * @return Str1:字段   Str2:字段备注
     * @Param tableName 表名
     */
    public static Map<String, String> getListStr(String tableName) {
        Connection conn = null;
        ResultSet rs = null;
        Map<String, String> map = new HashMap<>();
        try {
            conn = getConnection();
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getColumns(null, "%", tableName, "%");
            while (rs.next()) {
                map.put(rs.getString("COLUMN_NAME"), rs.getString("REMARKS"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, null, null, conn);
        }
        return map;
    }

}
