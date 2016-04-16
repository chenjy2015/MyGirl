package app.originality.com.originality.util;


import android.util.Log;
import app.originality.com.originality.config.Contants;


public class LogOut {

    public static void systemOut(String str) {
        if (Contants.LOG_FLAG && str != null) {
            System.out.println(str);
        }
    }

    public static void exceptionOut(String tag, String msg, Exception e) {
        if (Contants.LOG_FLAG) {
            Log.e(tag, msg, e);
        }
    }

    public static void v(String tag, String msg) {
        if (Contants.LOG_FLAG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (Contants.LOG_FLAG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (Contants.LOG_FLAG) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (Contants.LOG_FLAG) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (Contants.LOG_FLAG) {
            Log.e(tag, msg);
        }
    }

    public static void printStackTrace(Exception e) {
        if (Contants.LOG_FLAG) {
            e.printStackTrace();
        }
    }
}
