package com.test.study.util.work;

import java.io.IOException;

import com.test.study.util.io.IoUtil;

public class Mobi {

    public static void main(String[] args) throws IOException {
        String txt = IoUtil.readFile("D:\\1.txt");

        String[] unit = txt.split("\r\n");
        for (String string : unit) {
            String[] s = string.split("\\s+");
            for (int i = 0; i < 12; i++) {
                if (s[2].equals(removeCharAt(s[1], i))) {
                    IoUtil.writeFile("D:\\2.txt", string + "\r\n");
                    break;
                }
            }
        }
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);

    }
}