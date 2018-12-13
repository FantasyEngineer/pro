package com.hjg.toolspro;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.hjg.tool.FileUtil;
import com.hjg.tool.L;
import com.hjg.tool.T;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.zip.CRC32;

import rx.functions.Action1;

import static com.hjg.tool.FileUtil.ROOTDIRECTORY;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RxPermissions.getInstance(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
//            @Override
//            public void call(Boolean aBoolean) {
//                Toast.makeText(MainActivity.this, "get permission", Toast.LENGTH_SHORT).show();
//            }
//        });
//        T.showLong(AppUtils.getOSVersion());
//        T.showLong(BuildConfig.DEBUG + "");
//        L.d(BuildConfig.DEBUG + "");
//        T.showLong(AppUtils.getPhoneModel());
//        L.d(RandomUtil.getRandomNumbersAndLetters(100));
//        L.d(RandomUtil.getRandomNumbers(10));
//        People people = new People();
//        people.setAge(12);
//        People people1 = new People();
//        people1.setAge(13);
//        People people2 = new People();
//        people2.setAge(14);
//        People people3 = new People();
//        people3.setAge(15);
//        People[] peoples = new People[]{people, people1, people2, people3};
//        List list = new ArrayList();
//        list.add(1);
//        list.add(1);
//        list.add(1);
//        list.add(1);
//        list = JsonUtils.toArrayList(JsonUtils.toJSONString(list));
//        for (int i = 0; i < list.size(); i++) {
////            L.d(list.get(i) + "");
//        }
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//        String s = gson.toJson(people);
//        Log.d("MainActivity", s);
//        L.d(JsonUtils.toObject(JsonUtils.toJSONString(people), People.class).getAge() + "");
//        L.d(RandomUtil.getRandom("我正好借着这个事情发挥", 4));
//        L.d(RandomUtil.getRandomHan() + "");
//        L.d(RandomUtil.getRandom(100) + "");
//        int[] start = new int[]{1, 2, 3, 4, 5, 6};
//        int[] end = RandomUtil.shuffle(start, 6);
//        for (int j = 0; j < end.length; j++) {
//            L.d(end[j] + "---->");
//        }
//        People[] people4 = (People[]) RandomUtil.shuffle(peoples);
//        for (int j = 0; j < people4.length; j++) {
//            L.d(people4[j].getAge() + "----------->");
//        }
//        float xdpi = getResources().getDisplayMetrics().xdpi;
//        float ydpi = getResources().getDisplayMetrics().ydpi;
//        Log.d("MainActivity", "xdpi:" + xdpi);
//        Log.d("MainActivity", "ydpi:" + ydpi);
//        final String path = ROOTDIRECTORY + "/toolspro";
//        final String path1 = ROOTDIRECTORY;
//        String imgpath = ROOTDIRECTORY + "/toolspro/img";
//        String tempPath = FileUtils.getTempDirectoryPath();///data/user/0/com.hjg.toolspro/cache
//
//        File dirPath = new File(path);
//        File file = new File(path + "/1.txt");
//        File imgFile = new File(imgpath + "/jpg.txt");
//
//        try {
//            L.d(FileUtils.contentEquals(file, imgFile) + "===》对比文件内容");
//            L.d(FileUtils.checksumCRC32(file) + "===CRC32校验结果");
//            L.d(FileUtils.sizeOf(file) + "===SIZE的大小");
////            L.d("计算大小按G MB KB B来输出====>" + FileUtil.byteCountToDisplaySize(FileUtil.sizeOfDirectory(dirPath)));
//            L.d("输出1GB====>" + FileUtils.byteCountToDisplaySize(1024 * 1024 * 1024));
//            L.d(FileUtils.getTempDirectoryPath() + "====cache目录");
//            L.d(FileUtils.getUserDirectoryPath() + "=====输出不了目录");
//            Collection<File> c = FileUtils.listFiles(new File(path), FileFilterUtils.suffixFileFilter("txt"), null);
//            /*输出所有后缀为txt的文件名字*/
//            Collection<File> c1 = FileUtils.listFiles(new File(path), FileFilterUtils.suffixFileFilter("txt"), DirectoryFileFilter.INSTANCE);
//            Iterator it = c1.iterator();
//            while (it.hasNext()) {
//                File f = (File) it.next();
//                L.d(f.getName());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        if (!file.exists()) {
////            T.showShort("file不存在");
////        }
//        /*读文件*/
////        try {
////            L.d(FileUtil.readFileToString(file, Charset.defaultCharset()) + "這裏");
////        } catch (IOException e) {
////            e.printStackTrace();
////            L.d("异常了？");
////        }
//        /*写文件*/
//        try {
//            FileUtils.write(file, "不知道是什麽情況,", "UTF-8");
//            FileUtils.write(imgFile, "不知道是什麽情況", "UTF-8");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //判断是否包含文件或者文件夹
//        try {
//            L.d(FilenameUtils.directoryContains(path, imgpath) + "");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        File imgFileDir = new File(imgpath);
//            /*创建目录，如果目录已经存在，不做任何处理,这里不能创建文件*/
//        if (imgFileDir.exists()) {
//            T.showShort("这里已经存在");
//        }
//        FileUtil.creatDirectory(path);
//        FileUtil.creatDirectory(imgpath);
//        Log.d("MainActivity", "FileUtil.getDirectorySize(new File(path)):" + FileUtil.getDirectorySize(new File(path)));
//        Log.d("MainActivity", "FileUtil" + FileUtil.formatSize(FileUtil.getFileSize(new File(path + "/1.txt"))));
//                } catch (IOException e) {
//            FileUtil.forceMkdir(imgFileDir);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    /*删除文件夹*/
//                    FileUtils.deleteDirectory(new File(path));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


    }
}
