package com.hjg.tool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

/**
 * @author houjiguo
 * @data 2018/11/28 11:49
 * @description 部分基于gson的jar包。 对于json的处理
 */

public class JsonUtils {
    /**
     * 将对象转换成字符串(可转换的数据源有很多类型)
     *
     * @param object 需要转换对象
     * @return 对应对象的字符串
     */
    public static String toJSONString(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }


    /***
     * 将字符串转换为List对象
     * @param jsonstr
     * @return
     */
    public static <T> List<T> toArrayList(String jsonstr) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<T> arrayList = gson.fromJson(jsonstr, new TypeToken<List<T>>() {
        }.getType());
        return arrayList;
    }

    /**
     * 将json字符串转换成对应对象
     *
     * @param json json字符串
     * @param c    对象对应的class
     * @param <T>  目标对象
     * @return 目标对象的实例
     */
    public static <T> T toObject(String json, Class<T> c) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, c);
    }

    /***
     * 将json字符串转换成HashMap
     * @param jsonStr
     * @return
     */
    public static HashMap toHashMap(String jsonStr) {
        HashMap<String, Object> map = new Gson().fromJson(jsonStr, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return map;
    }


}
