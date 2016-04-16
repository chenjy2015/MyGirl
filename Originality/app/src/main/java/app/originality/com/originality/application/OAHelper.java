package app.originality.com.originality.application;


import android.content.Context;

import app.originality.com.originality.bean.UserInfor;
import app.originality.com.originality.storage.OSPUtils;

public class OAHelper {

    /**
     * 检测 用户账号和者密码
     *
     * @param context
     * @param mAccount
     * @param mPassword
     * @return
     */
    public static String checkAcount(Context context, String mAccount, String mPassword) {
        UserInfor userInfor = OSPUtils.getUserInfor(context);
        if (userInfor != null) {
            if (!userInfor.getAccount().equals(mAccount)) {
                return "账号错误!";
            } else if (!userInfor.getPassWord().equals(mPassword)) {
                return "密码错误!";
            }
        }
        return "";
    }

}