package com.hjg.tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author houjiguo
 * @data 2018/11/29 15:35
 * @description 文件 文件夹等相关操作
 * @about 相关文件的创建可以使用FileUtils
 */

public class FileUtil {
    //根目录
    public static String ROOTDIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath();

    /**
     * 创建目录(如果已经存在，不创建)
     *
     * @param directory 计划创建的目录
     */
    public static void creatDirectory(String directory) {
        File file = new File(directory);
        if (file.isFile()) {//是文件不创建
            return;
        }
        try {
            FileUtils.forceMkdir(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除目录（直接调用，不需要子线程）
     *
     * @param directory 计划删除的目录
     */
    public static void deleteDirectory(String directory) {
        File file = new File(directory);
        FileUtils.deleteQuietly(file);
    }

    /**
     * 对比两个文件是否相同
     *
     * @param file
     * @param file2
     * @return true 相同。 如果两个都是空，则相等
     */
    public static boolean contentEquals(File file, File file2) {
        try {
            return FileUtils.contentEquals(file, file2);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 是否包含目录或文件（判定父目录是否包含本目录内部的目录或者文件）
     *
     * @param path      父目录
     * @param chilePath 子目录 或者子文件
     */
    public static boolean directoryContains(String path, String chilePath) {
        try {
            return FilenameUtils.directoryContains(path, chilePath);
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 是否包含目录或文件（判定父目录是否包含本目录内部的目录或者文件）
     *
     * @param file
     * @param chileFile
     */
    public static boolean directoryContains(File file, File chileFile) {
        try {
            return FileUtils.directoryContains(file, chileFile);
        } catch (IOException e) {
            return false;
        }

    }

    /**
     * 创建文件
     *
     * @param directory 文件路径
     * @param name      文件名
     * @param data      内容
     * @param append    是否追加（true 追加，不覆盖原有内容;false 不追加，覆盖原有内容）
     */
    public static void creatFile(String directory, String name, CharSequence data, boolean append) {
        File file = new File(directory + File.separator + name);
        try {
            FileUtils.write(file, data, append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件
     *
     * @param directory 文件路径
     * @param name      文件名
     * @param data      内容
     */
    public static void creatFile(String directory, String name, CharSequence data) {
        FileUtil.creatFile(directory, name, data, false);
    }


    /**
     * 创建文件
     *
     * @param file   文件
     * @param data   内容
     * @param append 是否追加（true 追加，不覆盖原有内容;false 不追加，覆盖原有内容）
     */
    public static void creatFile(File file, CharSequence data, boolean append) {
        try {
            FileUtils.write(file, data, append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件
     *
     * @param file 文件
     * @param data 内容
     */
    public static void creatFile(File file, CharSequence data) {
        FileUtil.creatFile(file, data, false);
    }

    /**
     * 获取目录的size
     *
     * @return
     */
    public static long getDirectorySize(File file) {
        if (file.isDirectory()) {
            return FileUtils.sizeOfDirectory(file);
        } else {
            throw new IllegalArgumentException("file is not a directory");
        }
    }

    /**
     * 获取文件大小
     *
     * @param file
     * @return
     */
    public static long getFileSize(File file) {
        return file.length();
    }

    /**
     * 格式化空间占有量
     *
     * @param size
     * @return
     */
    public static String formatSize(long size) {
        return FileUtils.byteCountToDisplaySize(size);
    }

    public static String formatSize(BigInteger size) {
        return FileUtils.byteCountToDisplaySize(size);
    }


    /**
     * 检查SD卡是否已装载
     */
    public static boolean isExistSdCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获得SD目录路径
     */
    public static File getSD() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * 去掉文件后缀
     */
    public static String clearSuffix(String str) {
        return FilenameUtils.getBaseName(str);
    }

    /**
     * 获取文件名
     *
     * @param str
     * @return
     */
    public static String getPrefix(String str) {
        return FilenameUtils.getBaseName(str);
    }

    /**
     * 获取文件的后缀名
     */
    public static String getSuffix(String str) {
        return FilenameUtils.getExtension(str);
    }

    // 测试外置sd卡是否卸载，不能直接判断外置sd卡是否为null，因为当外置sd卡拔出时，仍然能得到外置sd卡路径。我这种方法是按照android谷歌测试DICM的方法，
    // 创建一个文件，然后立即删除，看是否卸载外置sd卡
    public static boolean checkFsWritable(String dir) {

        if (dir == null)
            return false;

        File directory = new File(dir);

        if (!directory.isDirectory()) {
            if (!directory.mkdirs()) {
                return false;
            }
        }

        File f = new File(directory, ".keysharetestgzc");
        try {
            if (f.exists()) {
                f.delete();
            }
            if (!f.createNewFile()) {
                return false;
            }
            f.delete();
            return true;

        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isValidFile(File file) {
        if (file == null) {
            return false;
        } else if (!file.exists()) {
            try {
                file.mkdirs();
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return file.canWrite();
    }

    /**
     * 获取一个文件夹下的所有文件
     *
     * @param root
     * @return
     */
    public static List<File> listPathFiles(String root) {
        List<File> allDir = new ArrayList<File>();
        SecurityManager checker = new SecurityManager();
        File path = new File(root);
        checker.checkRead(root);
        File[] files = path.listFiles();
        for (File f : files) {
            if (f.isFile())
                allDir.add(f);
            else
                listPath(f.getAbsolutePath());
        }
        return allDir;
    }

    /**
     * 列出root目录下所有子目录
     *
     * @param root
     * @return 绝对路径
     */
    public static List<String> listPath(String root) {
        List<String> allDir = new ArrayList<String>();
        SecurityManager checker = new SecurityManager();
        File path = new File(root);
        checker.checkRead(root);
        // 过滤掉以.开始的文件夹
        if (path.isDirectory()) {
            for (File f : path.listFiles()) {
                if (f.isDirectory() && !f.getName().startsWith(".")) {
                    allDir.add(f.getAbsolutePath());
                }
            }
        }
        return allDir;
    }

    /**
     * 删除空目录
     * <p/>
     * 返回 0代表成功 ,1 代表没有删除权限, 2代表不是空目录,3 代表未知错误
     *
     * @return
     */
    public static int deleteBlankPath(String path) {
        File f = new File(path);
        if (!f.canWrite()) {
            return 1;
        }
        if (f.list() != null && f.list().length > 0) {
            return 2;
        }
        if (f.delete()) {
            return 0;
        }
        return 3;
    }

    /**
     * 清空一个文件夹
     *
     * @param filePath
     */
    public static void clearFileWithPath(String filePath) {
        List<File> files = FileUtil.listPathFiles(filePath);
        if (files.isEmpty()) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                clearFileWithPath(f.getAbsolutePath());
            } else {
                f.delete();
            }
        }
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 获取目录文件大小(使用递归，弃用)
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        if (files != null) {

            for (File file : files) {
                if (file.isFile()) {
                    dirSize += file.length();
                } else if (file.isDirectory()) {
                    dirSize += file.length();
                    dirSize += getDirSize(file); // 递归调用继续统计
                }
            }
        }
        return dirSize;
    }

    /**
     * 流写入文件
     *
     * @param in   输入流
     * @param file 文件
     * @throws IOException
     */
    public static void writeFile(InputStream in, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (file != null && file.exists())
            file.delete();

        FileOutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024 * 128];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }

    /**
     * 读取Assets目录下的文件。输出字符串。
     *
     * @param mContext
     * @param fileName
     * @return
     */
    public static String getAsset(Context mContext, String fileName) {
        StringBuilder sb = new StringBuilder();
        AssetManager am = mContext.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }
}
