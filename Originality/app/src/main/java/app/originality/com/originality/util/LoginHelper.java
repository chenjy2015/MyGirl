package app.originality.com.originality.util;

import android.content.Context;

import app.originality.com.originality.application.OApplication;
import app.originality.com.originality.bean.UserInfor;
import app.originality.com.originality.storage.OSPUtils;


public class LoginHelper {

    /**
     * 保存用户信息
     *
     * @param userInfor
     */
    public synchronized void setUserInfor(Context mContext,UserInfor userInfor) {
        OApplication.mUserInfor = userInfor;
        OSPUtils.setUserInfor(mContext, userInfor);
    }

    /**
     * 提取用户信息
     *
     * @return
     */
    public synchronized UserInfor getUserInfor(Context mContext) {
        UserInfor mUserInfor = OApplication.mUserInfor;
        if (mUserInfor == null) {
            OSPUtils.getUserInfor(mContext);
            OApplication.mUserInfor = mUserInfor;
        }
        return mUserInfor;
    }


}