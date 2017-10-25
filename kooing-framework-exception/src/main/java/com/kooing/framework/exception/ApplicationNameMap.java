package com.kooing.framework.exception;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : kooing
 * @date : 2017/10/25 - 17:31
 * @desription :dubboName存放，根据启动后的进程id进行获取容器名称
 * @update by :
 */
public class ApplicationNameMap {
    private static Map<String, String> map = new HashMap<String, String>();

    public static void put(String dubboName){
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        map.put(runtimeMXBean.getName().split("@")[0], dubboName);
    }

    public static String get(){
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        return map.get(runtimeMXBean.getName().split("@")[0]);
    }
}
