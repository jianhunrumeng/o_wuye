package com.wuye.common.util.json;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;

import java.io.StringReader;

public class JSONUtils {

    /**
     * 将对象转换为JSON格式的字符串
     *
     * @param obj
     * @return 返回JSON字符串
     */
    public static String toJSONAsString(Object obj) {
        try {
            return JSONMapper.toJSON(obj).render(false);
        } catch (MapperException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T jsonToObject(String jsonStr, Class<T> targetClass)
            throws TokenStreamException, RecognitionException, MapperException {
        JSONValue jv = new JSONParser(new StringReader(jsonStr)).nextValue();
        return (T) JSONMapper.toJava(jv, targetClass);
    }


    // test
    public static void main(String[] args) throws Exception {
        // Person p = new Person();
        // p.setK("a");
        // p.setV("v");
        //
        // String json = toJSONAsString(p);
        // Person np = jsonToObject(json,Person.class);
        // System.out.println(np.getK()+"=="+np.getV());
    }

}
