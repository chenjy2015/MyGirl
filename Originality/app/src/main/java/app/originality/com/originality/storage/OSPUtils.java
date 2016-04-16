package app.originality.com.originality.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import app.originality.com.originality.bean.PersonalInforVO;
import app.originality.com.originality.bean.UserInfor;
import app.originality.com.originality.util.StringUtils;

/**
 * @author cjy
 * @version V1.0
 * @company 信息存储辅助类
 * @Description
 * @date 2016/3/31
 */
public class OSPUtils {

    private static final String SP_CONFIG = "Originality";
    //当前应用是否首次安装
    private static final String SP_CONFIG_IS_FIRST_LAUNCHER = "is_first_launcher";
    //用户信息
    private static final String SP_CONFIG_USERINFOR = "UserInfor";

    private static final String SP_CONFIG_PERSONAL_INFOR = "psersonal_infor";


    /**
     * 设置 是否首次启动应用
     *
     * @param context
     * @param isFirstLauncher
     */
    public static void setFirstLauncher(Context context, boolean isFirstLauncher) {
        SharedPreferences sp = context.getSharedPreferences(SP_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(SP_CONFIG_IS_FIRST_LAUNCHER, isFirstLauncher);
        editor.commit();
    }

    /**
     * 是否第一次启动应用
     *
     * @param context
     * @return
     */
    public static boolean getFirstLauncher(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_CONFIG, Context.MODE_PRIVATE);
        return sp.getBoolean(SP_CONFIG_IS_FIRST_LAUNCHER, true);
    }


    /**
     * 保存用户信息
     *
     * @param context
     * @param userInfor
     */
    public static void setUserInfor(Context context, UserInfor userInfor) {
        SharedPreferences sp = context.getSharedPreferences(SP_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String userJson;
        if (userInfor == null) {
            userJson = "";
        } else {
            userJson = gson.toJson(userInfor);
        }
        editor.putString(SP_CONFIG_USERINFOR, userJson);
        editor.commit();
    }

    /**
     * 提取用户信息
     *
     * @param context
     * @return
     */
    public static UserInfor getUserInfor(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_CONFIG, Context.MODE_PRIVATE);
        UserInfor userInfor = null;
        Gson gson;
        String userJson = sp.getString(SP_CONFIG_USERINFOR, null);
        if (!StringUtils.isNullOrEmpty(userJson)) {
            gson = new Gson();
            userInfor = gson.fromJson(userJson, UserInfor.class);
        }
        return userInfor;
    }









    /**
     * 保存个人资料
     *
     * @param context
     * @param personalInforVO
     */
    public static void setPersonalInfor(Context context, PersonalInforVO personalInforVO) {
        SharedPreferences sp = context.getSharedPreferences(SP_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String personalJson;
        if (personalInforVO == null) {
            personalJson = "";
        } else {
            personalJson = gson.toJson(personalInforVO);
        }
        editor.putString(SP_CONFIG_USERINFOR, personalJson);
        editor.commit();
    }

    /**
     * 提取用户信息
     *
     * @param context
     * @return
     */
    public static PersonalInforVO getPersonalInfor(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_CONFIG, Context.MODE_PRIVATE);
        PersonalInforVO personalInforVO = null;
        Gson gson;
        String userJson = sp.getString(SP_CONFIG_USERINFOR, null);
        if (!StringUtils.isNullOrEmpty(userJson)) {
            gson = new Gson();
            personalInforVO = gson.fromJson(userJson, PersonalInforVO.class);
        }
        return personalInforVO;
    }
}