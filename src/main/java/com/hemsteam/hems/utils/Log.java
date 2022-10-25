package com.hemsteam.hems.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public static String getNowTime(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(date);
    }
    public static void d(String tag,String str){
        System.out.println("\033[32m [DEBUG/"+tag+"] "+str);
    }
}
