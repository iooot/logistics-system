package common.usingtool;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json {
    /**
     * 传入对象获取json字符串
     */
    public static String getJsonStr(Object var1) {
        String rs = "";
        try {
            rs = new ObjectMapper().writeValueAsString(var1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 传入json字符串获取对象
     */
    public static <T> T getObject(String var1, Class<T> var2) throws IOException, JsonParseException, JsonMappingException {
        return new ObjectMapper().readValue(var1, var2);
    }

}
