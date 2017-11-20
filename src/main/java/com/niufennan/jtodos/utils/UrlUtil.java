package com.niufennan.jtodos.utils;

public class UrlUtil {
    public static String getUserName(String url) {
        String[] temp = url.split("/");
        if(temp.length==5) {
            return temp[temp.length-1];
        }
        return "";
    }
}
