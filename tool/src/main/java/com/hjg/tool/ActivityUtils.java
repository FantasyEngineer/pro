package com.hjg.tool;

import android.app.Activity;

import java.util.ArrayList;

/**
 * @author houjiguo
 * @data 2018/11/29 13:54
 * @description Activity相关的方法
 */
public class ActivityUtils {

    private static ArrayList<Activity> list = new ArrayList<Activity>();

    /**
     * 增加活动对象
     * 需要在基类的onCreat中添加该方法
     */
    public static void add(Activity activity) {
        list.add(activity);
    }

    /**
     * 移除活动对象
     * 需要在基类的onDestroy中添加该方法
     *
     * @param activity
     */
    public static void remove(Activity activity) {
        list.remove(activity);
    }

    /**
     * 关闭所有activity
     */
    public static void removeAll() {
        for (Activity activity : list) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
