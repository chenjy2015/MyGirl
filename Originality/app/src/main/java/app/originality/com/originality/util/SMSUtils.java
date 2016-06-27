package app.originality.com.originality.util;

import android.app.Activity;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;


import java.util.List;
import java.util.ArrayList;

import app.originality.com.originality.bean.SmsInfo;


/**
 * @author cjy
 * @version V1.0
 * @Description 短信状态监听辅助类 继承CotentObserver
 * @date 2016/6/21
 */
public class SMSUtils extends ContentObserver {

    private Activity mActivity;
    private Handler mHandler;
    private static int MSG_AIRPLANE = 1;
    private List<SmsInfo> mSmsInfoList;
    private MessageListener mMessageListener;
    public static final String TAG = "SMSUtils";

    //所有短信
    public static final String SMS_URI_ALL = "content://sms/";
    //收件箱短信
    public static final String SMS_URI_INBOX = "content://sms/inbox";
    //发件箱短信
    public static final String SMS_URI_SEND = "content://sms/sent";
    //草稿箱短信
    public static final String SMS_URI_DRAFT = "content://sms/draft";

    public SMSUtils(Activity activity, Handler handler) {
        super(handler);
        mActivity = activity;
        mHandler = handler;
    }

//    @Override
//    public void onChange(boolean selfChange, Uri uri) {
//        super.onChange(selfChange, uri);
//        // 系统是否处于飞行模式下
//        try {
//            int isAirplaneOpen = Settings.System.getInt(mContext.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON);
//            mHandler.obtainMessage(MSG_AIRPLANE, isAirplaneOpen).sendToTarget();
//        } catch (Settings.SettingNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }


    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Uri uri = Uri.parse(SMS_URI_INBOX);
        mSmsInfoList = getSmsInfo(uri, mActivity);
        if (mSmsInfoList == null || mSmsInfoList.size() < 1) {
            uri = Uri.parse(SMS_URI_ALL);
            mSmsInfoList = getSmsInfo(uri, mActivity);
        }
        Message msg = mHandler.obtainMessage();
        msg.obj = mSmsInfoList.get(0).getSmsbody();
        mHandler.sendMessage(msg);
        LogOut.d(TAG, "Message content is:" + mSmsInfoList.get(0).getSmsbody());
        LogOut.d(TAG, "Message info is:" + mSmsInfoList.get(0));
    }

    /**
     * 注意:
     * 该处只用按照时间降序取出第一条即可
     * 这条当然是最新收到的消息
     */
    private List getSmsInfo(Uri uri, Activity activity) {
        List smsInfoList = new ArrayList();
        String[] projection = new String[]{"_id", "address", "person", "body",
                "date", "type"};
//        Cursor cusor = activity.managedQuery(uri, projection, null, null, "date desc limit 1");
        Cursor cusor = activity.getContentResolver().query(uri, projection, null, null, "date desc limit 1");
        int nameColumn = cusor.getColumnIndex("person");
        int phoneNumberColumn = cusor.getColumnIndex("address");
        int smsbodyColumn = cusor.getColumnIndex("body");
        int dateColumn = cusor.getColumnIndex("date");
        int typeColumn = cusor.getColumnIndex("type");
        if (cusor != null) {
            while (cusor.moveToNext()) {
                SmsInfo smsinfo = new SmsInfo();
                smsinfo.setName(cusor.getString(nameColumn));
                smsinfo.setDate(cusor.getString(dateColumn));
                smsinfo.setPhoneNumber(cusor.getString(phoneNumberColumn));
                smsinfo.setSmsbody(cusor.getString(smsbodyColumn));
                smsinfo.setType(cusor.getString(typeColumn));
                smsInfoList.add(smsinfo);
            }
            cusor.close();
        }
        System.out.println("smsInfoList.size()=" + smsInfoList.size());
        return smsInfoList;
    }

    // 回调接口
    public interface MessageListener {
        //回传短信内容 如果需要 可以将整个对象传递
        public void OnReceived(String message);
    }

    /**
     * 设置回调接口对象
     *
     * @param messageListener
     */
    public void setOnReceivedMessageListener(
            MessageListener messageListener) {
        this.mMessageListener = messageListener;
    }

    /**
     * 从短信中获取验证码
     * <p>
     * 验证码的判断方式：
     * 从第一个数字的下标codeBegin开始，到下标codeBegin之后第一个不是数字的下标codeEnd结束。
     *
     * @param messageBody
     * @return
     */
    public String getSmsVerifyCode(String messageBody) {
        int codeBegin = -1, codeEnd = -1;
        int length = messageBody.length();
        for (int i = 0; i < length; i++) {
            char ch = messageBody.charAt(i);
            if (codeBegin < 0) {
                if (ch >= '0' && ch <= '9') {
                    codeBegin = i;
                }
            } else {
                if (i == length - 1) {
                    codeEnd = length;
                    break;
                }
                if (ch < '0' || ch > '9') {
                    codeEnd = i;
                    break;
                }
            }
        }
        return messageBody.substring(codeBegin, codeEnd);
    }

}