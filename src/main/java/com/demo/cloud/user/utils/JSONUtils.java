package com.demo.cloud.user.utils;


import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;


public class JSONUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 对象字段全部列入
        objectMapper.setSerializationInclusion(Inclusion.NON_NULL);

        // 取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 忽略空bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        // 统一日期格式yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        // 忽略在json字符串中存在,但是在java对象中不存在对应属性的情况
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T parse(String string, TypeReference<T> t) {
        try {
            return objectMapper.readValue(string, t);
        } catch (IOException var3) {
            return null;
        }
    }

    public static <T> T parse(String string, Class<T> t) {
        try {
            return objectMapper.readValue(string, t);
        } catch (IOException var3) {
            return null;
        }
    }

    public static <T> T importJson(String path, Class<T> tClass) throws IOException {
        InputStream is = JSONUtils.class.getResourceAsStream(path);
        return parse(IOUtils.toString(is), tClass);
    }

    public static <T> T importJson(String path, TypeReference<T> t) throws IOException {
        InputStream is = JSONUtils.class.getResourceAsStream(path);
        return parse(IOUtils.toString(is), t);
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writer().writeValueAsString(object);
        } catch (IOException var2) {
            var2.printStackTrace();
            return "{}";
        }
    }

}
