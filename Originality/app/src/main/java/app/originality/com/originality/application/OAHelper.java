package app.originality.com.originality.application;


import android.content.Context;

import app.originality.com.originality.bean.UserInfor;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.storage.OSPUtils;

import java.util.ArrayList;

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

    /**
     * 获取本地图片地址 ArrayList形式
     * @param context
     * @return
     */
    public static ArrayList<String> getImageUrlsToArray(Context context) {
        ArrayList<String> data = new ArrayList<String>();
        for (int i = 0; i < Contants.imageUrls.length; i++) {
            data.add(Contants.imageUrls[i]);
        }
        return data;
    }

}