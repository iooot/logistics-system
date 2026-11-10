package common.usingtool;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * 传入对象（obj）的字段，必须与数据库的字段保持一直（大小写一致）
 * */

public class BeanUtil {
    public static void loadEntity(Object obj, Map map) {
        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
