package com.test.study.util.work;

/**
 * WdAuthRecDet
 */
public class WdAuthRecDet {

    public static void main(String[] args) {
        String s = "黄岛区烟台路399号K2海棠湾F区5栋2单元2804户";
        System.out.println(getPathName(s));

        System.out.println("123".indexOf(1));
        System.out.println("123".indexOf(1+""));
        System.out.println("123".indexOf("1"));
    }

    public static void name() {
        String path = "pajfji大家覅地方342";
        char[] chars = path.toCharArray();
        for (char c : chars) {
            System.out.println(c);
        }
    }

    public static String getRoomName(String s) {
        char[] chars = s.toCharArray();
        int last = chars.length - 1;
        int first = chars.length - 1;
        String number = "0123456789";
        if ("室".equals(chars[chars.length - 1] + "") || "户".equals(chars[chars.length - 1] + "")) {
            for (int i = chars.length - 2; i > 0; i--) {
                if (number.indexOf(chars[i]) == -1) {
                    break;
                } else {
                    first = i;
                }
            }
        } else {
            for (int i = chars.length - 1; i > 0; i--) {
                if (number.indexOf(chars[i]) == -1) {
                    break;
                } else {
                    first = i;
                    last = chars.length;
                }
            }
        }

        return s.substring(first, last);
    }

    public static String getPathName(String s) {

        int last = s.length();
        if (s.lastIndexOf("路") == -1) {
            return "";
        }

        last = s.lastIndexOf("路");
        if (s.lastIndexOf("区", last) > 0) {
            return s.substring(s.lastIndexOf("区", last) + 1, last);
        } else {
            if (s.lastIndexOf("市南", last) > 0 || s.lastIndexOf("市北", last) > 0) {
                return s.substring(last - 2 > 0 ? last - 2 : 0, last);
            } else {
                if (s.lastIndexOf("市", last) > 0) {
                    return s.substring(s.lastIndexOf("市", last) + 1, last);
                } else {
                    return s.substring(last - 2 > 0 ? last - 2 : 0, last);
                }
            }
        }

    }
}