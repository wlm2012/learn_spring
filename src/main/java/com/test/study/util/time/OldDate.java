package com.test.study.util.time;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.test.study.util.StringUtil.StringUtil;

/**
 * OldStringToDate
 */
public class OldDate {

    public static Date  stringToDate(String s, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(s);
        return date;
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(date);
        return s;
    }

    public static Timestamp stringToTimestamp(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(s);
        s = sdf.format(date);
        // 注：String的类型必须形如： yyyy-MM-dd HH:mm:ss[.f...] 这样的格式，中括号表示可选，否则报错！！
        return Timestamp.valueOf(s);
    }

    public static String timestampToString(Timestamp ts, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(ts);
    }

    public static Date timestampToDate(Timestamp ts) {
        return new Date(ts.getTime());
    }

    public static Timestamp dateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }


    public static boolean isValidDate(String dateStr, String format) {
        if (StringUtil.isEmpty(dateStr) || StringUtil.isEmpty(format)
                || dateStr.length() != format.length()) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(dateStr);
        } catch (Exception e) {
            return false;
        }
        return true;
    }



}