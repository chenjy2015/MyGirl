package app.originality.com.originality.util.media;

import android.content.Context;
import android.content.Intent;

/**
 *
 * @company Chenjy_Studio
 * @Description 多媒体管理类
 * @author cjy
 * @date 2016/9/3 11:50
 * @version V1.0
 */
public class MediaManager {

    private static MediaManager mInstance;
    private MediaType mMediaType;                   //媒体类型
    private String path;                            //媒体播放资源路径来源
    private Intent mMediaPlayerServiceIntent; //媒体播放后台运行服务类
    private static Context mContext;

    private MediaManager() {

    }

    public synchronized static MediaManager getInstance() {
        if (mInstance == null) {
            mInstance = new MediaManager();
        }
        return mInstance;
    }

    /**
     * 启动多媒体播放服务
     *
     * @param context
     * @param mMediaType
     * @param path
     */
    public void player(Context context, MediaType mMediaType, String path, MediaPlayerProgressListenner mediaPlayerProgressListenner) {
        mContext = context;
        intentService(mMediaType, path, mediaPlayerProgressListenner);
    }

    /**
     * 关闭多媒体播放服务
     */
    public void stop() {
        if (mMediaPlayerServiceIntent != null) {
            mContext.stopService(mMediaPlayerServiceIntent);
            mMediaPlayerServiceIntent = null;
        }
    }

    private void intentService(MediaType mMediaType, String path, MediaPlayerProgressListenner mediaPlayerProgressListenner) {
        stop();
        if (mMediaPlayerServiceIntent == null) {
            mMediaPlayerServiceIntent = new Intent(mContext, MediaPlayerService.class);
            mMediaPlayerServiceIntent.putExtra("MediaType", mMediaType);
            mMediaPlayerServiceIntent.putExtra("path", path);
            mMediaPlayerServiceIntent.putExtra("MediaPlayerProgressListenner", mediaPlayerProgressListenner);
            mContext.startService(mMediaPlayerServiceIntent);
        }
    }
}