package common.SqlHelper;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * 连接池技术
 */
public class DruidService {
    private static final String CONFIG_PATH = "config/druid.properties";
    private static DataSource ds;

    static {
        try {
            getDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getDataSource() throws Exception {
        Properties pro = new Properties();
        InputStream resourceAsStream =
                DruidService.class.getClassLoader().
                        getResourceAsStream(CONFIG_PATH);
        pro.load(resourceAsStream);
        ds = DruidDataSourceFactory.createDataSource(pro);
    }

    //获取连接池
    public static DataSource getDs() {
        return ds;
    }

    //获取工厂对象
    private static void setDs(DataSource ds) {
        DruidService.ds = ds;
    }

}
