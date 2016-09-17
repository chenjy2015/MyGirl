package app.originality.com.originality.util.interfaces;

import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class InterfaceUtils {

    /**
     * 动画播放进度
     */
    public static class MyAnimationListenner implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    /**
     * EditText 文本内容变化监听
     */
    public static class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    /**
     * View 普通点击事件
     */
    public static interface MyOnClick {
        public void onClick(View v, String content);
    }

    public static class OnImageLoaderListenner implements ImageLoadingListener{

        @Override
        public void onLoadingStarted(String s, View view) {

        }

        @Override
        public void onLoadingFailed(String s, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingComplete(String s, View view, Bitmap bitmap) {

        }

        @Override
        public void onLoadingCancelled(String s, View view) {

        }
    }
}