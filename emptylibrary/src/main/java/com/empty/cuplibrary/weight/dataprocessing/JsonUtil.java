package com.empty.cuplibrary.weight.dataprocessing;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Java对象和JSON字符串相互转化工具类
 */
public class JsonUtil {

    private JsonUtil(){}

    /**
     * 对象转换成json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * json字符串转成对象
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * json字符串转成对象
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * List 列表数据处理 返回MAP
     * @param jsonArray
     * @return
     * @throws JSONException
     */
    public static  ArrayList<Map<String,String>> rentrunJsonBeen(JSONArray jsonArray) throws JSONException {

        ArrayList<Map<String,String>> mapslist = new ArrayList<>();
        for (int i = 0;i<jsonArray.length();i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Map<String, String> maplist = new HashMap<>();
            Iterator iterator = json.keys();
            while (iterator.hasNext()) {
                String key = iterator.next() + "";
                maplist.put(key, json.getString(key));
            }

            mapslist.add(maplist);
        }
        return mapslist;
    }

}