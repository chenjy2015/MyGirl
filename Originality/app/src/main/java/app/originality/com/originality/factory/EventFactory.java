package app.originality.com.originality.factory;

import android.graphics.Bitmap;
import android.view.View;

public class EventFactory {

    /**
     * 摇一摇状态处理更新事件
     */
    public static class ImageLoaderDownloadEvent extends BaseEvent {

        private Bitmap bitmap;

        public ImageLoaderDownloadEvent loaderComplete(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public void loaderFail() {

        }
    }


    /**
     * item 长按事件
     */
    public static class OnItemLongClickTouchEvent extends BaseEvent {

        public int position;
        public View view;

        public OnItemLongClickTouchEvent(int position, View view) {
            this.position = position;
            this.view = view;
        }
    }


    /**
     * item 点击事件
     */
    public static class OnItemClickTouchEvent extends BaseEvent {
        public int position;
        public View view;

        public OnItemClickTouchEvent(int position, View view) {
            this.position = position;
            this.view = view;
        }
    }

}