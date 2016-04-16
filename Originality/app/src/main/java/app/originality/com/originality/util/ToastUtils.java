package app.originality.com.originality.util;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.originality.com.originality.R;


public class ToastUtils {


    public static Toast mToast;

    /**
     * 普通吐司
     *
     * @param context
     * @param content
     */
    public static void showBigToast(Context context, String content) {
//        Toast toast = new Toast(context);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        View view = LayoutInflater.from(context).inflate(R.layout.item_toast, null);
//        TextView tv = (TextView) view.findViewById(R.id.id_toast_text);
//        tv.setText(content);
//        toast.setView(view);
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.show();
        showBigVioletToast(context, content);
    }

    /**
     * 紫色背景白色字体吐司
     *
     * @param context
     * @param content
     */
    public static void showBigVioletToast(Context context, String content) {
        if (mToast == null) {
            mToast = new Toast(context);
        } else {
            mToast.cancel();
            mToast = new Toast(context);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_toast, null);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setBackground(context.getDrawable(R.drawable.background_toast));
            } else {
                view.setBackgroundResource(R.drawable.background_toast);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView tv = (TextView) view.findViewById(R.id.id_toast_text);
        tv.setText(content);
        mToast.setView(view);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }


    /**
     * 紫色背景白色字体吐司
     *
     * @param context
     * @param content
     */
    public static void showBigVioletToastOnBottom(Context context, String content) {
        if (mToast == null) {
            mToast = new Toast(context);
        } else {
            mToast.cancel();
            mToast = new Toast(context);
        }
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_toast, null);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setBackground(context.getDrawable(R.drawable.background_toast));
            } else {
                view.setBackgroundResource(R.drawable.background_toast);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView tv = (TextView) view.findViewById(R.id.id_toast_text);
        tv.setText(content);
        mToast.setView(view);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }

}