package com.test.study.util.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewDate {

    public static LocalDateTime stringToLocalDateTime(String s, String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(s, dtf);
    }


    public static LocalDate stringToLocalDate(String s, String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(s, dtf);
    }



    public static String localDateTimeToString(LocalDateTime date, String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return date.format(dtf);
    }


    public static String localDateToString(LocalDate date, String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        return date.format(dtf);
    }

}