package com.lisp.engine.base.domain;

/* author:Qinzijing
*  date: 2019/11/10
*  description:时间实体类
*/
public class Time {
    /*
    * 世界的时间
    * */
    public static final long SECOND = 1000000000L;

    public static double delta;

    /*
    * 获取当前时间
    * */
    public static long getTime() {
        return System.nanoTime();
    }

    public static long getSECOND() {
        return SECOND;
    }

    public static double getDelta() {
        return delta;
    }

    public static void setDelta(double delta) {
        Time.delta = delta;
    }
}
