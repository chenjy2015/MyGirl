package app.originality.com.originality.util;

import android.text.format.DateFormat;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataTimeUtils {

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate(long currentTimeMillis) {
        Date currentTime = new Date(currentTimeMillis);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间 指定格式
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate(long currentTimeMillis, String format) {
        Date currentTime = new Date(currentTimeMillis);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * 获取现在时间 年月日
     *
     * @return返回字符串格式 yyyy-MM-dd
     */
    public static String getStringDateByMounthDay(long currentTimeMillis) {
        Date currentTime = new Date(currentTimeMillis);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    public static String getStringDateTime(long currentTimeMillis) {
        Date currentTime = new Date(currentTimeMillis);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String timeString = formatter2.format(currentTime);
        return dateString + "T" + timeString;
    }


    public static String getCurrentTime() {
        String str = DateFormat.format("yyyy-M-d kk:mm:ss", Calendar.getInstance().getTime()).toString();//获得系统时间
        return str;
    }


    /**
     * 将时间格式为yyyy-MM-dd HH:mm:ss转换为long
     */
    public static long getOderTime(String time) {
        long millionSeconds;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            millionSeconds = sdf.parse(time).getTime();//毫秒
            return millionSeconds;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 将时间格式为yyyy-MM-dd HH:mm:ss转换为long
     */
    public static long getSimpleLongTime(String time) {
        long millionSeconds;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            millionSeconds = sdf.parse(time).getTime();//毫秒
            return millionSeconds;
        } catch (Exception e) {
            LogOut.printStackTrace(e);
        }
        return 0;
    }

    /**
     * 传入时间 判断是否为零点
     * @param time
     * @return
     */
    public static boolean isZeroTime(long time){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        if(hour == 23 && min == 59){
            return true;
        }
        return false;
    }
}