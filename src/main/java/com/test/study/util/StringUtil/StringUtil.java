package com.test.study.util.StringUtil;

/**
 * StringUtil
 */
public class StringUtil {

	public static boolean isEmpty(String s) {
		return s == null || s.equals("");
	}

	public static boolean isEmpty(String[] ss) {
		for (String s : ss) {
			if (isEmpty(s)) {
				return true;
			}
		}
		return false;
	}


}