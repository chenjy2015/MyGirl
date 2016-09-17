package app.originality.com.originality.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Parcelable;

import app.originality.com.originality.R;
import app.originality.com.originality.ui.HomeActivity;
import app.originality.com.originality.ui.StartActivity;

public class ShortcutUtil {

    /**
     * 创建 应用快捷方式
     *
     * @param act
     * @param iconResId
     * @param appnameResId
     */
    public static void createShortCut(Activity act, int iconResId,
                                      int appnameResId) {

        // com.android.launcher.permission.INSTALL_SHORTCUT

        Intent shortcutintent = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重复创建
        shortcutintent.putExtra("duplicate", false);
        // 需要现实的名称
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, act.getString(appnameResId));
        // 快捷图片
        Parcelable icon = Intent.ShortcutIconResource.fromContext(act.getApplicationContext(), iconResId);
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        // 点击快捷图片，运行的程序主入口
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(act.getApplicationContext(), StartActivity.class));
        // 发送广播
        act.sendBroadcast(shortcutintent);
    }


    public static int count = 1;

    /**
     * 创建快捷方式
     *
     * @param activity
     */
    public void createShortCut1(Activity activity) {
        deleteShortCut(activity);
        Intent addIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        Parcelable icon = Intent.ShortcutIconResource.fromContext(activity, R.mipmap.girl_icon); //获取快捷键的图标
        Intent myIntent = new Intent(activity, HomeActivity.class);
        addIntent.putExtra("duplicate", false);  // 不允许重复创建
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "O_Short" + count);//快捷方式的标题
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);//快捷方式的图标
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);//快捷方式的动作
        activity.sendBroadcast(addIntent);//发送广播
        count++;
    }


    /**
     * 删除程序的快捷方式
     */
    public static void deleteShortCut(Activity activity) {
        Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");

        //快捷方式的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "O_Short" + (count - 1 >= 0 ? count - 1 : count));

        //指定当前的Activity为快捷方式启动的对象: 如 com.everest.video.VideoPlayer
        //注意: ComponentName的第二个参数必须是完整的类名（包名+类名），否则无法删除快捷方式
        String appClass = activity.getPackageName() + "." + activity.getLocalClassName();
        ComponentName comp = new ComponentName(activity.getPackageName(), appClass);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));
        activity.sendBroadcast(shortcut);
        count++;
    }


    public static void updateAPPIcon(Activity activity){
        Intent addIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        Parcelable icon = Intent.ShortcutIconResource.fromContext(activity, R.mipmap.girl_icon); //获取快捷键的图标
        Intent myIntent = new Intent(activity, HomeActivity.class);
        addIntent.putExtra("duplicate", false);  // 不允许重复创建
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "O_Short" + count);//快捷方式的标题
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);//快捷方式的图标
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);//快捷方式的动作
        activity.sendBroadcast(addIntent);//发送广播
    }
}