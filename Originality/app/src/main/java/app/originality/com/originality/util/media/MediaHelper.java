package app.originality.com.originality.util.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import app.originality.com.originality.application.OApplication;
import app.originality.com.originality.bean.MusicSourceVO;
import app.originality.com.originality.util.AndroidSystemHelper;
import app.originality.com.originality.util.FileUtils;
import app.originality.com.originality.util.LogOut;

/**
 * 多媒体播放辅助类
 */
public class MediaHelper extends ActivityMethod {

    public ArrayList<MusicSourceVO> mMusicSourceData;

    public Gson mGson;

    private MediaPlayer mMediaPlayer;

    private static MediaHelper mInstance;

    private static Context mContext;

    private MediaHelper() {
        init();
        initMediaPlayer();
    }

    public synchronized static MediaHelper getInstance() {
        if (mInstance == null) {
            mInstance = new MediaHelper();
        }
        return mInstance;
    }

    public void init() {
        mContext = OApplication.mContext;
        mGson = new Gson();
    }

    /**
     * 初始化媒体播放类
     * 判断：
     * 1.如果上下文对象为空
     * 2.媒体播放器服务类不为空且已经在运行中
     * 则不进行二次初始化操作
     */
    public void initMediaPlayer() {
        if (mContext == null || mMediaPlayer != null || AndroidSystemHelper.isServiceWorked(mContext, MediaPlayerService.class.getName())) {
            return;
        }
        mMediaPlayer = new MediaPlayer();
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
        }
    }

    /**
     * 播放本地音乐文件
     *
     * @param path
     */
    private void playerMusicByLocation(String path) {
        try {
            if (mMediaPlayer == null) {
                return;
            }
            try {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
            } catch (IllegalStateException e) {
                mMediaPlayer = new MediaPlayer();
            }
            mMediaPlayer.pause();
            mMediaPlayer.seekTo(0);
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 播放本地asset音乐文件
     *
     * @param fileName asset中的文件名称
     */
    private void playerMusicByAsset(String fileName) {
        try {
            if (mMediaPlayer == null) {
                return;
            }
            try {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
            } catch (IllegalStateException e) {
                mMediaPlayer = new MediaPlayer();
            }
            mMediaPlayer.pause();
            mMediaPlayer.seekTo(0);
            AssetManager am = mContext.getAssets();//获得该应用的AssetManager
            AssetFileDescriptor afd = am.openFd(fileName);
            mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放本地asset音乐文件
     *
     * @param sourceId raw中文件id
     */
    private void playerMusicByRaw(String sourceId) {
        try {
            mMediaPlayer = MediaPlayer.create(mContext, Integer.parseInt(sourceId));
            mMediaPlayer.setLooping(false);
            mMediaPlayer.start();
        } catch (IllegalStateException e) {
            mMediaPlayer = new MediaPlayer();
        } catch (Exception e) {
            e.printStackTrace();
            mMediaPlayer = new MediaPlayer();
        }
    }


    /**
     * 播放网络资源音乐
     *
     * @param url
     */
    private void playerMusicByInterner(String url) {
        Uri uri = Uri.parse(url);
        try {
            if (mMediaPlayer == null) {
                return;
            }
            try {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                }
            } catch (IllegalStateException e) {
                mMediaPlayer = new MediaPlayer();
            }
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(mContext, uri);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            LogOut.printStackTrace(e);
            onDestroy();
        } catch (Exception e) {
            LogOut.printStackTrace(e);
            onDestroy();
        }
    }


    public void player(MediaType mediaType, String url) {
        //媒体类型 ； 音乐，资源来源：互联网
        if (mediaType == MediaType.MUSIC_INTERNER) {
            playerMusicByInterner(url);
            //媒体类型 ； 音乐，资源来源：本地
        } else if (mediaType == MediaType.MUSIC_LOCATION) {
            playerMusicByLocation(url);
            //媒体类型： 音频，资源来源：本地 asset
        } else if (mediaType == MediaType.MUSIC_ASSET) {
            playerMusicByAsset(url);
        } else if (mediaType == MediaType.MUSIC_RAW) {
            playerMusicByRaw(url);
            //媒体类型 ； 视频，资源来源：互联网
        } else if (mediaType == MediaType.VIDEO_INTERNER) {

            //媒体类型 ； 视频，资源来源：本地
        } else if (mediaType == MediaType.VIDEO_LOCATION) {

        }
    }

    public int getCurrentPosition() {
        return mMediaPlayer.getCurrentPosition();
    }
}