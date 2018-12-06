package com.hjg.tool;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import java.io.File;
import java.util.List;

/**
 * @author houjiguo
 * @data 2018/11/26 14:13
 * @description this is description
 */

public class AppUtils {
    /**
     * 取得手机系统版本号
     *
     * @return example -> 7.0
     */
    public static String getOSVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 取得手机操作系统名称
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }


    /**
     * 获取版本Name
     *
     * @return 版本name
     */
    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    /**
     * 获取版本code
     *
     * @param context 上下文对象
     * @return 版本code
     */
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    /**
     * 获取应用包名
     *
     * @param context 上下文对象
     * @return
     */
    public static String getPackageName(Context context) {
        return getPackageInfo(context).packageName;
    }

    /**
     * 获取包PackageInfo
     *
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            throw new RuntimeException("没有找到包名");
        }
    }

    /**
     * 获取签名摘要
     *
     * @param context
     * @return 摘要内容
     */
    public static String getSign(Context context) {
        String strSign = null;
        try {
            int flag = PackageManager.GET_SIGNATURES;
            PackageManager pm = context.getPackageManager();
            List<PackageInfo> apps = pm.getInstalledPackages(flag);
            Object[] objs = apps.toArray();
            for (int i = 0, j = objs.length; i < j; i++) {
                PackageInfo packageinfo = (PackageInfo) objs[i];
                String packageName = packageinfo.packageName;
                if (packageName.equals(context.getPackageName())) {
                    Signature[] temps = packageinfo.signatures;
                    Signature tmpSign = temps[0];
                    strSign = tmpSign.toCharsString();
                }
            }
        } catch (Exception e) {
        }
        return strSign;
    }

    /**
     * 判断手机是否ROOT
     *
     * @return true 已获取最高权限； false 为获取最高权限
     */
    public static boolean isSystemRoot() {
        boolean isRoot = false;
        try {
            if ((!new File("/system/bin/su").exists())
                    && (!new File("/system/xbin/su").exists())) {
                isRoot = false;
            } else {
                isRoot = true;
            }
        } catch (Exception e) {

        }
        return isRoot;
    }

    /**
     * 判断版本是否需要更新
     *
     * @param serverVersion 服务端版本
     * @return true 需要更新; false 不需要更新
     */
    public boolean checkVersion(String serverVersion) {
        if (!StrUtil.isEmpty(serverVersion) && getFloatVersion(BuildConfig.VERSION_NAME) < getFloatVersion(serverVersion)) {
            return true;
        }
        return false;
    }

    /**
     * 格式化版本信息
     *
     * @param version
     * @return
     */
    public float getFloatVersion(String version) {
        return Float.parseFloat(version.replaceFirst("\\.", "#").replaceAll("\\.", "").replace("#", "."));
    }
}
