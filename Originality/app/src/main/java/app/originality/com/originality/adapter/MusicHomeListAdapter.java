package app.originality.com.originality.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import app.originality.com.originality.R;
import app.originality.com.originality.bean.MusicSourceVO;
import app.originality.com.originality.config.Contants;
import app.originality.com.originality.util.StringUtils;

public class MusicHomeListAdapter extends OriginalityBaseAdapter<MusicSourceVO> {

    public MusicHomeListAdapter(Activity act, List data) {
        super(act, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mAct).inflate(R.layout.item_music_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MusicSourceVO musicSourceVO = mData.get(position);
        viewHolder.name.setText(StringUtils.checkStringIsNull(musicSourceVO.getVoiceFileName()));
        ImageLoader.getInstance().displayImage(Contants.imageUrls[11], viewHolder.download);
        return convertView;
    }

    public class ViewHolder {
        //layout1
        private LinearLayout layout1;
        private TextView name;
        private ImageView download;
        //layout2
        private LinearLayout layout2;
        private TextView music_name;
        private TextView music_year;
        private TextView music_singer;
        private TextView music_area;
        private TextView music_size;

        public ViewHolder(View convertView) {
            this.layout1 = (LinearLayout) convertView.findViewById(R.id.layout1);
            this.name = (TextView) convertView.findViewById(R.id.name);
            this.download = (ImageView) convertView.findViewById(R.id.download);

            this.layout2 = (LinearLayout) convertView.findViewById(R.id.layout2);
            this.music_name = (TextView) convertView.findViewById(R.id.music_name);
            this.music_year = (TextView) convertView.findViewById(R.id.music_particular_year);
            this.music_singer = (TextView) convertView.findViewById(R.id.music_singer);
            this.music_area = (TextView) convertView.findViewById(R.id.music_area);
            this.music_size = (TextView) convertView.findViewById(R.id.music_size);

        }
    }
}