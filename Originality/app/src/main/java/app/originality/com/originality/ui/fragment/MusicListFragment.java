package app.originality.com.originality.ui.fragment;

import app.originality.com.originality.R;
import app.originality.com.originality.util.media.MediaHelper;
import app.originality.com.originality.util.media.MediaManager;
import app.originality.com.originality.util.media.MediaPlayerProgressListenner;
import app.originality.com.originality.util.media.MediaType;
import app.originality.com.originality.widget.HorizontalProgressBar;

import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicListFragment extends BaseFragment implements MediaPlayerProgressListenner, View.OnClickListener {

    private HorizontalProgressBar mHorizontalProgressBar;
    private Button mStartBtn, mSeekToBtn, mSeekBackBtn;
    private int fileLenght;
    private int mCurrentProgress; //平均播放值
    private int mDuration; //播放总长度值
    private int mCurrentPosition; //播放进度值

    @Override
    protected int setView() {
        return R.layout.fragment_music_list;
    }

    @Override
    protected void findViews() {
        mHorizontalProgressBar = (HorizontalProgressBar) this.findViewById(R.id.id_horiziontal_progress);
        mStartBtn = (Button) this.findViewById(R.id.pause);
        mSeekToBtn = (Button) this.findViewById(R.id.seekTo);
        mSeekBackBtn = (Button) this.findViewById(R.id.seekBack);
    }

    @Override
    protected void initEvent() {
        mHorizontalProgressBar.setProgressType(1);
        mStartBtn.setOnClickListener(this);
        mSeekToBtn.setOnClickListener(this);
        mSeekBackBtn.setOnClickListener(this);
    }

    @Override
    protected void init() {
        MediaHelper.getInstance().player(MediaType.MUSIC_RAW, R.raw.dawn_of_heroes + "", this);
    }

    @Override
    protected void loadData() {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int progress = 0;
//                while (progress <= 100) {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Message msg = Message.obtain();
//                    msg.arg1 = progress;
//                    mHandler.sendMessage(msg);
//                    progress += 1;
//                }
//            }
//        }).start();
    }

//    public Handler mHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            int progress = msg.arg1;
//            mHorizontalProgressBar.setProgress(progress);
//        }
//    };

    @Override
    public void onStart(int duration) {
        mDuration = duration;
        initProgress(duration);
        mHorizontalProgressBar.setMaxProgress(duration);
    }

    @Override
    public void onChange(int currentPosition) {
        mCurrentPosition = currentPosition;
        mHorizontalProgressBar.setProgress(currentPosition);
    }

    /**
     * 初始化 文件长度、整除100后的平均值
     *
     * @param duration
     */
    public void initProgress(int duration) {
        mCurrentProgress = duration / 100;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pause:
                if (mStartBtn.getText().equals("暂停")) {
                    mStartBtn.setText("继续");
                    MediaHelper.getInstance().pause();
                } else {
                    mStartBtn.setText("暂停");
                    MediaHelper.getInstance().start();
                }
                break;

            case R.id.seekTo:
                int position = mCurrentProgress * 10 + mCurrentPosition;
                MediaHelper.getInstance().seekTo(position > mDuration ? mDuration : position);
                break;

            case R.id.seekBack:
                int position1 = mCurrentPosition - mCurrentProgress * 10;
                MediaHelper.getInstance().seekTo(position1 < -0 ? 0 : position1);
                break;

        }
    }
}