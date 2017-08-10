package com.lk.common.util;

/**
 * Created by Administrator on 2017/4/5.
 */
public class CommonUtil {
    public static boolean isEmpty(String s) {
        if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
            return true;
        } else {
            return false;
        }
    }
}
