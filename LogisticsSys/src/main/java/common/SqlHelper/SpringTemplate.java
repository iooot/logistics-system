package common.SqlHelper;
import org.springframework.jdbc.core.JdbcTemplate;


public class SpringTemplate {

    private static JdbcTemplate temp = new JdbcTemplate(DruidService.getDs());

    //获取数据库操作对象
    public static JdbcTemplate getTemp() {
        return temp;
    }


}
