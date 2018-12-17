package com.hjg.tool;

import android.content.res.Resources;


/**
 * 获取string.xml中的字
 */

public class WordUtil {

    private static Resources sResources;

    static {
        /*这里需要在application中提供resource的方法*/
//        sResources = ForeoApplication.get().getResources();
    }

    public static String getString(int res) {
        return sResources.getString(res);
    }
}
