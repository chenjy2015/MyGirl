package app.originality.com.originality.ui.fragment;

import app.originality.com.originality.R;
import app.originality.com.originality.widget.HorizontalProgressBar;
import android.os.Handler;
import android.os.Message;

public class MusicListFragment extends BaseFragment {

    private HorizontalProgressBar mHorizontalProgressBar;


    @Override
    protected int setView() {
        return R.layout.fragment_music_list;
    }

    @Override
    protected void findViews() {
        mHorizontalProgressBar = (HorizontalProgressBar) this.findViewById(R.id.id_horiziontal_progress);
        mHorizontalProgressBar.setMaxProgress(100);
        mHorizontalProgressBar.setProgress(50);
    }

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
                int progress = 0;
                while (progress <= 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = Message.obtain();
                    msg.arg1 = progress;
                    mHandler.sendMessage(msg);
                    progress += 1;
                }
            }
        }).start();
    }

    public Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int progress = msg.arg1;
            mHorizontalProgressBar.setProgress(progress);
        }
    };
}