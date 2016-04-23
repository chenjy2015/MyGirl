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
}