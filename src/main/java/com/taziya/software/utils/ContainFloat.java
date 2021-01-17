package com.taziya.software.utils;

public class ContainFloat {
    /**
     * 保留两位小数的函数
     * @param flo
     * @return
     */
    public float containTwoFloat(float flo){
        float date2 = (float) Math.round(flo*100)/100;
        return date2;
    }

    /**
     * 保留一位小数函数
     * @param flo
     * @return
     */
    public float containOnoFloat(float flo){
        float date1 = (float) Math.round(flo*10)/10;
        return date1;
    }



    /**
     * 测试
     * @param args
     */
//    public static void main(String[] args) {
//        ContainFloat containFloat = new ContainFloat();
//        float a = (float) 12.3665;
//        float b = (float) 12.3665;
//        float v2 = containFloat.containTwoFloat(a);
//        float v1 = containFloat.containOnoFloat(b);
//
//        System.out.println(v2);
//        System.out.println(v1);
//
//    }
}
