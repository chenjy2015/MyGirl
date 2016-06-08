package app.originality.com.pulgmainapplication.bean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class MyFile implements Serializable {

    Drawable apk_icon;
    /**
     * 包名
     */
    String packageName;
    /**
     * apk的绝对路劲
     */
    String filePath;

    /**
     * apk的版本名称 String
     */
    String versionName;
    /**
     * apk的版本号码 int
     */
    int versionCode;
    /**
     * 安装处理类型
     */
    int installed;

    public Drawable getApk_icon() {
        return apk_icon;
    }

    public void setApk_icon(Drawable apk_icon) {
        this.apk_icon = apk_icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public int getInstalled() {
        return installed;
    }

    public void setInstalled(int installed) {
        this.installed = installed;
    }
}