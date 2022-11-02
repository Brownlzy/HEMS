package com.hemsteam.hems.utils;

import java.text.NumberFormat;


public class Percent {
    /**
     * 计算百分比
     * @param part 计算部分
     * @param total 总体部分
     * @return String
     */
    public static String getPercent(double part,double total) {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        // 设置保留几位小数，这里设置的是保留两位小数
        percentInstance.setMinimumFractionDigits(2);
        return percentInstance.format(part/total);
    }
}
