package com.hemsteam.hems.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public static String getNowTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(date);
    }

    public static void i(Class<?> tag, String str) {
        System.out.println("\033[37m [INFO /" + tag.getName().split("com.hemsteam.hems.")[1] + "] " + str + "\033[37m");
    }

    public static void d(Class<?> tag, String str) {
        System.out.println("\033[32m [DEBUG/" + tag.getName().split("com.hemsteam.hems.")[1] + "] " + str + "\033[37m");
    }

    public static void w(Class<?> tag, String str) {
        System.out.println("\033[33m [WARN /" + tag.getName().split("com.hemsteam.hems.")[1] + "] " + str + "\033[37m");
    }

    public static void e(Class<?> tag, String str) {
        System.out.println("\033[31m [ERROR/" + tag.getName().split("com.hemsteam.hems.")[1] + "] " + str + "\033[37m");
    }

    public static void main(String[] args) {
        i(Log.class, "测试INFO");
        d(Log.class, "测试DEBUG");
        w(Log.class, "测试WARN");
        e(Log.class, "测试ERROR");
    }
}
