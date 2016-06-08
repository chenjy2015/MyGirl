package app.originality.com.pulgmainapplication.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import app.originality.com.pulgmainapplication.bean.MyFile;

public class ApkSearchUtils {
    /**
     * 获取手机上apk文件信息类，主要是判断是否安装再手机上了，安装的版本比较现有apk版本信息
     * <a href="http://my.oschina.net/arthor" target="_blank" rel="nofollow">@author</a>  Dylan
     */
    public static int INSTALLED = 0; // 表示已经安装，且跟现在这个apk文件是一个版本
    public static int UNINSTALLED = 1; // 表示未安装
    public static int INSTALLED_UPDATE = 2; // 表示已经安装，版本比现在这个版本要低，可以点击按钮更新
    public static String TAG = "ApkSearchUtils";

    private Context context;
    private List<MyFile> myFiles = new ArrayList<MyFile>();


    public List<MyFile> getMyFiles() {
        return myFiles;
    }


    public void setMyFiles(List<MyFile> myFiles) {
        this.myFiles = myFiles;
    }


    public ApkSearchUtils(Context context) {
        super();
        this.context = context;
    }


    /**
     * @param file 运用递归的思想，递归去找每个目录下面的apk文件
     */
    public ApkSearchUtils FindAllAPKFile(File file) {

        // 手机上的文件,目前只判断SD卡上的APK文件
//        file = Environment.getDataDirectory();
        // SD卡上的文件目录
        if (file.isFile()) {
            addMyFile(file);
        } else {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File file_str : files) {
                    FindAllAPKFile(file_str);
                }
            }
        }
        return this;
    }

    /**
     * @param file
     * @return
     */
    public MyFile addMyFile(File file) {
        String name_s = file.getName();
        MyFile myFile = null;
        String apk_path = null;
        // MimeTypeMap.getSingleton()
        if (name_s.toLowerCase().endsWith(".apk")) {
            myFile = new MyFile();
            apk_path = file.getAbsolutePath();// apk文件的绝对路劲
            // System.out.println("----" + file.getAbsolutePath() + "" + name_s);
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageArchiveInfo(apk_path, PackageManager.GET_ACTIVITIES);
            ApplicationInfo appInfo = packageInfo.applicationInfo;

            /**获取apk的图标 */
            appInfo.sourceDir = apk_path;
            appInfo.publicSourceDir = apk_path;
            Drawable apk_icon = appInfo.loadIcon(pm);
            myFile.setApk_icon(apk_icon);
            /** 得到包名 */
            String packageName = packageInfo.packageName;
            myFile.setPackageName(packageName);
            /** apk的绝对路劲 */
            myFile.setFilePath(file.getAbsolutePath());
            /** apk的版本名称 String */
            String versionName = packageInfo.versionName;
            myFile.setVersionName(versionName);
            /** apk的版本号码 int */
            int versionCode = packageInfo.versionCode;
            myFile.setVersionCode(versionCode);
            /**安装处理类型*/
            int type = doType(pm, packageName, versionCode);
            myFile.setInstalled(type);

            myFiles.add(myFile);
        }
        return myFile;
    }
    
    /*
* 判断该应用是否在手机上已经安装过，有以下集中情况出现 
* 1.未安装，这个时候按钮应该是“安装”点击按钮进行安装
* 2.已安装，按钮显示“已安装” 可以卸载该应用 
* 3.已安装，但是版本有更新，按钮显示“更新” 点击按钮就安装应用 
*/

    /**
     * 判断该应用在手机中的安装情况
     *
     * @param pm          PackageManager
     * @param packageName 要判断应用的包名
     * @param versionCode 要判断应用的版本号
     */
    public int doType(PackageManager pm, String packageName, int versionCode) {
        List<PackageInfo> pakageinfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for (PackageInfo pi : pakageinfos) {
            String pi_packageName = pi.packageName;
            int pi_versionCode = pi.versionCode;
            //如果这个包名在系统已经安装过的应用中存在
            if (packageName.endsWith(pi_packageName)) {
                if (versionCode == pi_versionCode) {
                    return INSTALLED;
                } else if (versionCode > pi_versionCode) {
                    return INSTALLED_UPDATE;
                }
            }
        }
        return UNINSTALLED;
    }

    //======================寂寞安装==============
    //通过上面代码可以得到哪些apk是可以更新的，然后可以用以下代码进行寂寞安装
    private void silenceInstall(String apkInstallPath) {
        String[] args = {"pm", "install", "-r", apkInstallPath};
        String result = "";
        ProcessBuilder processBuilder = new ProcessBuilder(args);
        Process process = null;
        InputStream errIs = null;
        InputStream inIs = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int read = -1;
            process = processBuilder.start();
            errIs = process.getErrorStream();
            while ((read = errIs.read()) != -1) {
                baos.write(read);
            }
            baos.write('\n');

            inIs = process.getInputStream();
            while ((read = inIs.read()) != -1) {
                baos.write(read);
            }

            byte[] data = baos.toByteArray();
            result = new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (errIs != null) errIs.close();
                if (inIs != null) inIs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (process != null) process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, "result==：" + result);
        if (result.contains("Success")) {
            Log.d(TAG, "===========install success========");
        } else {
            Log.d(TAG, "===========install fail========");
        }
    }

    /**
     * (检测应用是否已经安装.)
     * <h3>Version</h3>1.0
     * <h3>CreateTime</h3> 2016/5/21,10:11
     * <h3>UpdateTime</h3> 2016/5/21,10:11
     * <h3>CreateAuthor</h3>（Geoff）
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     *
     * @param packageName 路径包名
     * @return true 已经安装 ， false 未安装
     */
    public static boolean checkPackageInstall(Context context, String packageName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * (安装apk.)
     * <h3>Version</h3>1.0
     * <h3>CreateTime</h3> 2016/5/21,10:13
     * <h3>UpdateTime</h3> 2016/5/21,10:13
     * <h3>CreateAuthor</h3>（Geoff）
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     *
     * @param file apk安装文件
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

}