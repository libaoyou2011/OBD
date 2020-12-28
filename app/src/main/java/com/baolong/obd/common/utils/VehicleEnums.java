package com.baolong.obd.common.utils;

import java.util.HashMap;

public class VehicleEnums {

    public static String cpys2Chinese(String key) {
        HashMap map = new HashMap();
        map.put("0", "蓝牌");
        map.put("1", "黄牌");
        map.put("2", "白牌");
        map.put("3", "黑牌");
        map.put("4", "绿牌");
        return (String) map.get(key);
    }

    public static String cpys2Bs(String value) {
        HashMap map = new HashMap();
        map.put("0", "蓝牌");
        map.put("1", "黄牌");
        map.put("2", "白牌");
        map.put("3", "黑牌");
        map.put("4", "绿牌");

        for(Object key: map.keySet()){
            if(map.get(key).equals(value)){
                return (String) key;
            }
        }

        return  null;
    }
}
