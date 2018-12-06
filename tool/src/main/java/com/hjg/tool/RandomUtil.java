package com.hjg.tool;

import java.util.List;
import java.util.Random;

import static com.hjg.tool.StrUtil.isEmpty;

/**
 * @author houjiguo
 * @data 2018/11/27 15:01
 * @description 随机相关的方法
 */

public class RandomUtil {
    private static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private final static int delta = 0x9fa5 - 0x4e00 + 1;


    /**
     * 获取随机数字和字母(会重复)
     *
     * @param length 随机的长度
     * @return
     */
    public static String getRandomNumbersAndLetters(int length) {
        return getRandom(NUMBERS_AND_LETTERS, length);
    }


    /**
     * 获取随机数字字符串
     *
     * @param length 长度
     * @return
     */
    public static String getRandomNumbers(int length) {
        return getRandom(NUMBERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是大小写字母的混合
     *
     * @param length
     * @return
     */
    public static String getRandomLetters(int length) {
        return getRandom(LETTERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是大写字母的组合
     *
     * @param length 长度
     * @return
     */
    public static String getRandomCapitalLetters(int length) {
        return getRandom(CAPITAL_LETTERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是小写字母的混合
     *
     * @param length 长度
     * @return
     */
    public static String getRandomLowerCaseLetters(int length) {
        return getRandom(LOWER_CASE_LETTERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，它是源文件中字符的混合
     *
     * @param source
     * @param length
     * @return
     */
    public static String getRandom(String source, int length) {
        return isEmpty(source) ? null : getRandom(source.toCharArray(), length);
    }


    /**
     * 获取随机中文
     *
     * @return 随机中文
     */
    public static char getRandomHan() {
        Random ran = new Random();
        return (char) (0x4e00 + ran.nextInt(delta));
    }

    /**
     * 获取一个固定长度的随机字符串，它是sourceChar中字符的混合
     *
     * @param sourceChar
     * @param length
     * @return
     */
    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }

        StringBuilder str = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

    /**
     * 获取0到最大值之间的随机整数
     *
     * @param max
     * @return <ul>
     * <li>if max <= 0, return 0</li>
     */
    public static int getRandom(int max) {
        return getRandom(0, max);
    }

    /**
     * 获取最小值和最大值之间的随机整数
     *
     * @param min
     * @param max
     * @return <ul>
     * <li>if min > max, return 0</li>
     * <li>if min == max, return min</li>
     */
    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }

    /**
     * 变换算法，使用一个默认的随机源对指定的数组进行随机置换
     *
     * @param objArray 对象数组
     * @return
     */
    public static Object[] shuffle(Object[] objArray) {
        if (objArray == null) {
            throw new IllegalArgumentException("illege input");
        }

        return shuffle(objArray, getRandom(objArray.length));
    }

    /**
     * 变换算法，对指定的数组进行随机置换
     *
     * @param objArray     指定数组
     * @param shuffleCount 指定数组长度
     * @return
     */
    public static Object[] shuffle(Object[] objArray, int shuffleCount) {
        int length;
        if (objArray == null || shuffleCount < 0 || (length = objArray.length) < shuffleCount) {
            throw new IllegalArgumentException("illege input");
        }

        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            Object temp = objArray[length - i];
            objArray[length - i] = objArray[random];
            objArray[random] = temp;
        }
        return objArray;
    }

    /**
     * 变换算法，使用默认的随机源对指定的int数组进行随机置换
     *
     * @param intArray
     * @return
     */
    public static int[] shuffle(int[] intArray) {
        if (intArray == null) {
            return null;
        }

        return shuffle(intArray, intArray.length);
    }

    /**
     * 变换算法，随机置换指定的整数数组
     *
     * @param intArray
     * @param shuffleCount
     * @return
     */
    public static int[] shuffle(int[] intArray, int shuffleCount) {
        int length;
        if (intArray == null || shuffleCount < 0 || (length = intArray.length) < shuffleCount) {
            return null;
        }

        int[] out = new int[shuffleCount];
        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            out[i - 1] = intArray[random];
            int temp = intArray[length - i];
            intArray[length - i] = intArray[random];
            intArray[random] = temp;
        }
        return out;
    }

    /**
     * 对一个数组中的数据进行乱序排列
     *
     * @param arr 输入一个需要乱序的数据
     * @return 返回乱序数组
     */
    public static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Random random = new Random();
            int p = random.nextInt(i + 1);
            int tmp = arr[i];
            arr[i] = arr[p];
            arr[p] = tmp;
        }
        return arr;
    }

    /**
     * 获取长度为length的乱序数组（数组最小值为0，最大值为length-1，数据不重复）
     *
     * @param length 数组的长度
     * @return 乱序之后的数组
     */
    public static int[] getInt(int length) {
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = i;
        }
        sort(a);
        return a;
    }

    /**
     * 对list中的数据进行随机乱序
     *
     * @param list 需要乱序的List
     * @param <A>  list中包含的类型
     * @return 乱序之后的list
     */
    public static <A> List<A> sort(List<A> list) {
        if (list == null || list.size() == 0)
            throw new IllegalArgumentException("input error");
        for (int i = 1; i < list.size(); i++) {
            Random random = new Random();
            int p = random.nextInt(i + 1);
            A tmp = list.get(i);
            list.set(i, list.get(p));
            list.set(p, tmp);
        }
        return list;
    }

    /**
     * list 限定长度内进行乱序数据
     *
     * @param list 元数据
     * @param m    乱序开始的index
     * @param n    乱序结束的index
     * @param <A>  list包含的数据类型
     * @return 乱序数据list
     */
    public static <A> List<A> sort(List<A> list, int m, int n) {
        if (list == null || list.size() == 0)
            throw new IllegalArgumentException("input error");

        if (m < 0 || n > list.size() - 1 || n - m <= 1)
            throw new IllegalArgumentException("input error");

        for (int i = m; i < n + 1; i++) {
            Random random = new Random();
            int p = random.nextInt(i - m + 1) + m;
            A tmp = list.get(i);
            list.set(i, list.get(p));
            list.set(p, tmp);
        }
        return list;
    }

}
