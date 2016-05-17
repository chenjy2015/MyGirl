package app.originality.com.originality.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yzw
 * @version V1.0
 * @company 跨越速运
 * @Description
 * @date 2015/12/15
 */
public class FileUtils {

    public FileUtils() {
    }

    public static File getSDCardFolder() {
        File root = null;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            root = Environment.getExternalStorageDirectory();
        } else {
            root = Environment.getDataDirectory();
        }

        return root;
    }

    public static String getSDCardPath() {
        return getSDCardFolder().getAbsolutePath();
    }

    public static File getCiwongRootFolder() {
        File root = new File(getSDCardFolder(), "kye");
        if (!root.exists()) {
            root.mkdirs();
        }

        return root;
    }

    public static String formatDate(long dateLong) {
        try {
            Date e = new Date(dateLong);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(e);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static Bitmap getImageFromAssetsFile(Resources resources, String fileName) {
        Bitmap bitmap = null;
        InputStream is = null;
        AssetManager am = resources.getAssets();

        try {
            is = am.open(fileName);
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException var14) {
            var14.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException var13) {
                    var13.printStackTrace();
                }
            }

        }

        return bitmap;
    }

    /**
     * 从文件路径中提取文件名称
     *
     * @param pathandname
     * @return
     */
    public static String getFileName(String pathandname) {

        int start = pathandname.lastIndexOf("/");
        int end = pathandname.length();
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return null;
        }

    }

    /**
     * 判断文件是否存在
     *
     * @return
     */
    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void copyBigDataToSD(Context context, String fileName, String strOutFileName) throws IOException {
        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(strOutFileName);
        myInput = context.getAssets().open(fileName);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }

        myOutput.flush();
        myInput.close();
        myOutput.close();
    }

}
