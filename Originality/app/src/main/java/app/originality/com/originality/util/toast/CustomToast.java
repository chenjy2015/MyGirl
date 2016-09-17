package app.originality.com.originality.util.toast;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import app.originality.com.originality.R;
import app.originality.com.originality.util.AndroidSystemHelper;

public class CustomToast extends Toast {

    private Context mContext;
    private TextView mTextView;
    private View mToastView;
    private Boolean mIsShow = false;//记录状态 是否在显示
    private int mGravity;
    private WindowManager mWdm;
    private Timer mTimer;//定时器

    public static final int dbheight = 55;    //单位dp

    public CustomToast(Context context, int gravity) {
        super(context);
        this.mContext = context;
        this.mGravity = gravity;
        mWdm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //初始化计数器
        mTimer = new Timer();
    }

    public CustomToast init() {
        mToastView = LayoutInflater.from(mContext).inflate(R.layout.item_toast, null);
        mTextView = (TextView) mToastView.findViewById(R.id.id_toast_text);
        setParams();
        return this;
    }

    private WindowManager.LayoutParams mParams;

    private void setParams() {
        mParams = new WindowManager.LayoutParams();//初始化
        mParams.height = AndroidSystemHelper.dp2px(55, mContext);  //高
        mParams.width = WindowManager.LayoutParams.MATCH_PARENT;   //宽
        mParams.format = PixelFormat.TRANSLUCENT;
        mParams.windowAnimations = R.style.custom_animation_toast;// 设置进入退出动画效果
        mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        mParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        mParams.gravity = Gravity.TOP|Gravity.CENTER_HORIZONTAL;        //对其方式
        mParams.y = 5;      //下间距
    }


    public void show(String text, long mShowTime) {
        if (mIsShow) {// 如果Toast已经在显示 就先给隐藏了
            if (mWdm != null && mToastView != null)
                mWdm.removeView(mToastView);
            // 取消计时器
            if (mTimer != null) {
                mTimer.cancel();
                mTimer = new Timer();
            }
        }
        //设置显示内容
        mTextView.setText(text);
        //设置显示状态
        mIsShow = true;
        // 将其加载到windowManager上
        mWdm.addView(mToastView, mParams);

        //设置计时器
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mWdm.removeView(mToastView);
                mIsShow = false;
            }
        }, mShowTime);
    }
}
