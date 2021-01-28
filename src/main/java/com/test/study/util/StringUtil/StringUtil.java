package com.test.study.util.StringUtil;

import org.apache.commons.lang3.StringUtils;

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

	public static boolean isEmpty2(String s) {

		return StringUtils.isEmpty(s);
	}

	public static boolean isBlank(String s) {
		return StringUtils.isBlank(s);
	}


}