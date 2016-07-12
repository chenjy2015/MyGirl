package app.originality.com.originality.factory;

import android.graphics.Bitmap;
import android.view.View;

import com.bm.library.Info;

import app.originality.com.originality.bean.SpaceImageVO;

public class OYEventFactory {

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
        public SpaceImageVO spaceImageVO;
        public Info photoViewInfo;

        public OnItemClickTouchEvent(int position, View view, SpaceImageVO spaceImageVO, Info photoViewInfo) {
            this.position = position;
            this.view = view;
            this.spaceImageVO = spaceImageVO;
            this.photoViewInfo = photoViewInfo;
        }
    }

}