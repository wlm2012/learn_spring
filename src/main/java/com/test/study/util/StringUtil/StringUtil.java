package com.test.study.util.StringUtil;

/**
 * StringUtil
 */
public class StringUtil {

    public static boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }
        if (s.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String[] ss){
        for (String s:ss ){
            if (isEmpty(s)){
                return true;
            }
        }
        return false;
    }

    
}