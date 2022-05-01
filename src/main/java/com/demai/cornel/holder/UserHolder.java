package com.demai.cornel.holder;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by tfzhu on 2019/1/4.
 */
public class UserHolder {

    public static ThreadLocal<Map<String, String>> holder = new ThreadLocal<Map<String, String>>() {
        @Override
        protected Map<String, String> initialValue() {
            return Maps.newHashMap();
        }
    };

    public static Map<String, String> getMap() {
        return holder.get();
    }

    public static String getValue(String key) {
        return getMap().get(key);
    }

    public static void set(Map<String, String> map) {
        holder.set(map);
    }

    public static void add(String key,String value) {
        getMap().put(key, value);
    }

    public static void remove() {
        holder.remove();
    }

}
