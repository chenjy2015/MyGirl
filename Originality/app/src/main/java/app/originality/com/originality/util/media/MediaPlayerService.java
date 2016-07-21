package app.originality.com.originality.util.media;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import app.originality.com.originality.util.LogOut;


/**
 * @author cjy
 * @version V1.0
 * @company 跨越速运
 * @Description 媒体播放器后台运行服务
 * @date 2016/3/17
 */
public class MediaPlayerService extends Service {


    private MediaHelper mMediaHelper;
    private MediaType mediaType;
    private String path;
    private MediaPlayerProgressListenner mediaPlayerProgressListenner;

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaHelper = MediaHelper.getInstance();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            mediaType = (MediaType) intent.getSerializableExtra("MediaType");
            path = intent.getStringExtra("path");
            mediaPlayerProgressListenner = (MediaPlayerProgressListenner) intent.getSerializableExtra("MediaPlayerProgressListenner");
            mMediaHelper.player(mediaType, path, mediaPlayerProgressListenner);
        } catch (Exception e) {
            LogOut.printStackTrace(e);
        }
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaHelper != null) {
            mMediaHelper.onDestroy();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}