package app.originality.com.originality.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import app.originality.com.originality.R;
import app.originality.com.originality.adapter.MusicHomeListAdapter;
import app.originality.com.originality.bean.MusicSourceVO;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.util.SwipeMenuCreatorHelper;
import app.originality.com.originality.util.media.MediaHelper;
import app.originality.com.originality.util.media.MediaPlayerProgressListenner;
import app.originality.com.originality.util.media.MediaType;

public class MusicListActivity extends BaseActivity implements MediaPlayerProgressListenner, View.OnClickListener, SwipeMenuListView.OnMenuItemClickListener, AdapterView.OnItemClickListener {

    private Button mStartBtn, mSeekToBtn, mSeekBackBtn;
    private SeekBar mSeekBarProgress;
    private TextView mMusicName, mMusicYear, mMusicSinger, mMusicArea, mMusicSize;
    private ImageView mMusicImg;
    private SwipeMenuListView mListView;
    private int mCurrentProgress; //平均播放值
    private int mDuration; //播放总长度值
    private int mCurrentPosition; //播放进度值
    private List<MusicSourceVO> mData;
    private MusicHomeListAdapter mHomeListAdapter;
    private boolean isStart;
//    private boolean isCan;      //是否接受进度条变化操作

    @Override
    protected int setView() {
        return R.layout.activity_music_list;
    }

    @Override
    protected void findViews() {
        mListView = (SwipeMenuListView) this.findViewById(R.id.music_list);
        mMusicImg = (ImageView) this.findViewById(R.id.id_music_img);
        mSeekBarProgress = (SeekBar) this.findViewById(R.id.id_seekbar);
        mStartBtn = (Button) this.findViewById(R.id.pause);
        mSeekToBtn = (Button) this.findViewById(R.id.seekTo);
        mSeekBackBtn = (Button) this.findViewById(R.id.seekBack);
        mMusicName = (TextView) this.findViewById(R.id.music_name);
        mMusicYear = (TextView) this.findViewById(R.id.music_particular_year);
        mMusicSinger = (TextView) this.findViewById(R.id.music_singer);
        mMusicArea = (TextView) this.findViewById(R.id.music_area);
        mMusicSize = (TextView) this.findViewById(R.id.music_size);

        initEvent();
        init();
        loadData();
    }

    @Override
    protected void init() {
        mTitleBar.hideTitleLayout();
//        isCan = true;
        MediaHelper.getInstance().player(MediaType.MUSIC_RAW, R.raw.dawn_of_heroes + "", this);
        isStart = true;
        ImageLoader.getInstance().displayImage(Contants.imageUrls[10], mMusicImg);
    }


    @Override
    protected void initEvent() {
        mStartBtn.setOnClickListener(this);
        mSeekToBtn.setOnClickListener(this);
        mSeekBackBtn.setOnClickListener(this);
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = SwipeMenuCreatorHelper.createSwipeMenuCreator(MusicListActivity.this);
        // set creator
        mListView.setMenuCreator(creator);
        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(this);
        mListView.setOnItemClickListener(this);
    }


    @Override
    protected void loadData() {
        mData = new ArrayList<MusicSourceVO>();
        String[] names = getResources().getStringArray(R.array.music_list_name);
        String[] resources = getResources().getStringArray(R.array.music_list_local_resource);
        String[] singers = getResources().getStringArray(R.array.music_list_singer);
        int[] years = getResources().getIntArray(R.array.music_list_year);
        String[] areas = getResources().getStringArray(R.array.music_list_area);

        for (int i = 0; i < names.length; i++) {
            MusicSourceVO musicSourceVO = new MusicSourceVO();
            musicSourceVO.setVoiceFileName(names[i]);
            musicSourceVO.setVoiceLocationPath(resources[i]);
            musicSourceVO.setVoiceName(names[i]);
            musicSourceVO.setVoiceSinger(singers[i]);
            musicSourceVO.setVoiceYear(years[i] + "");
            musicSourceVO.setVoiceArea(areas[i]);
            musicSourceVO.setVoiceSize((3.34 + i) + "MB");
            mData.add(musicSourceVO);
        }
        mHomeListAdapter = new MusicHomeListAdapter(MusicListActivity.this, mData);
        mListView.setAdapter(mHomeListAdapter);
        mHomeListAdapter.notifyDataSetChanged();

        mMusicName.setText(mData.get(0).getVoiceName());
        mMusicYear.setText(mData.get(0).getVoiceYear());
        mMusicSinger.setText(mData.get(0).getVoiceSinger());
        mMusicArea.setText(mData.get(0).getVoiceArea());
        mMusicSize.setText(mData.get(0).getVoiceSize());
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
                    MediaHelper.getInstance().onPause();
                } else {
                    mStartBtn.setText("暂停");
                    MediaHelper.getInstance().onStart();
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

    @Override
    public void onResume() {
        super.onResume();
        if (!isStart) {
            MediaHelper.getInstance().onRestart();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MediaHelper.getInstance().onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MediaHelper.getInstance().onStop();
    }


    @Override
    public void onPlayerStart(int duration) {
        isStart = true;
        mDuration = duration;
        initProgress(duration);
        mSeekBarProgress.setMax(duration);
    }

    @Override
    public void onPlayerChange(int currentPosition) {
//        if(isCan){
        mCurrentPosition = currentPosition;
        mSeekBarProgress.setProgress(currentPosition);
//        }
    }

    @Override
    public void onPlayerPause() {

    }

    @Override
    public void onPlayerStop() {
        isStart = false;
    }

    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MediaHelper.getInstance().onDestroy();
        MediaHelper.getInstance().player(MediaType.MUSIC_ASSET, mData.get(position).getVoiceLocationPath(), this);
    }
}
