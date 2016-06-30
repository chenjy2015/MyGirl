package app.originality.com.originality.factory;

import android.graphics.Bitmap;

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
     * 图片长按事件
     */
    public static class ImageLongTouchEvent extends BaseEvent {

        public int position;

        public ImageLongTouchEvent(int position) {
            this.position = position;
        }
    }

}