package app.originality.com.originality.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import app.originality.com.originality.R;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.ui.MusicListActivity;

public class MusicListFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mMusicImg;

    @Override
    protected int setView() {
        return R.layout.fragment_music_list;
    }

    @Override
    protected void findViews() {
        mMusicImg = (ImageView) this.findViewById(R.id.id_music_img);
    }

    @Override
    protected void init() {
        ImageLoader.getInstance().displayImage(Contants.imageUrls[10], mMusicImg);
    }


    @Override
    protected void initEvent() {
        mMusicImg.setOnClickListener(this);
    }


    @Override
    protected void loadData() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_music_img:
                Intent intent = new Intent(mAct, MusicListActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}