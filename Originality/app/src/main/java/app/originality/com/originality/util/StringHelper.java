package app.originality.com.originality.util;

import android.content.Context;

import app.originality.com.originality.R;
import java.util.ArrayList;

/**
 * @author cjy
 * @version V1.0
 * @company 创意APP
 * @Description 获取应用配置的文字信息集合辅助类
 * @date 2016/4/1
 */
public class StringHelper {

    /**
     * 获取 引导页面的引导语
     *
     * @param context
     * @return
     */
    public static String[] getGuideHintContent(Context context) {
        return context.getResources().getStringArray(R.array.guide_hint_content);
    }

    /**
     * 获取 任意StringArray内容
     *
     * @param context
     * @return
     */
    public static ArrayList<String> getMenusContent(Context context, int resourceId) {
        ArrayList<String> mData = new ArrayList<String>();
        String[] menus = context.getResources().getStringArray(resourceId);
        for(String s: menus){
            mData.add(s);
        }
        return mData;
    }

}