package app.originality.com.originality.ui.fragment;

import app.originality.com.originality.R;
import app.originality.com.originality.util.media.MediaHelper;
import app.originality.com.originality.util.media.MediaManager;
import app.originality.com.originality.util.media.MediaType;
import app.originality.com.originality.widget.HorizontalProgressBar;

import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicListFragment extends BaseFragment {

    private HorizontalProgressBar mHorizontalProgressBar;
    private int fileLenght;

    @Override
    protected int setView() {
        return R.layout.fragment_music_list;
    }

    @Override
    protected void findViews() {
        mHorizontalProgressBar = (HorizontalProgressBar) this.findViewById(R.id.id_horiziontal_progress);
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.dawn_of_heroes);
            //获取文件的字节数
             fileLenght = inputStream.available();

        } catch (IOException e) {
            e.printStackTrace();
        }

        mHorizontalProgressBar.setMaxProgress(100);
        mHorizontalProgressBar.setProgress(50);
        MediaHelper.getInstance().player(MediaType.MUSIC_RAW, R.raw.dawn_of_heroes + "");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(MediaHelper.getInstance().getCurrentPosition() < fileLenght){
                    handler.sendEmptyMessageDelayed(0, 100);
                }
            }
        }).start();
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            float currentPosition = MediaHelper.getInstance().getCurrentPosition();
            int progress =  (int)((currentPosition / fileLenght) * 100);
            mHorizontalProgressBar.setProgress(progress);
        }
    };

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
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
            }
        }).start();
    }

    public Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int progress = msg.arg1;
            mHorizontalProgressBar.setProgress(progress);
        }
    };
}