package app.originality.com.originality.modules.photo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import app.originality.com.originality.R;
import app.originality.com.originality.bean.SpaceImageVO;
import app.originality.com.originality.factory.EventFactory;
import app.originality.com.originality.modules.photo.bean.PhotoBeanVO;
import app.originality.com.originality.widget.zoomimage.ImageViewTouch;
import app.originality.com.originality.widget.zoomimage.SmoothImageView;
import de.greenrobot.event.EventBus;

public class ParallaxViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    public List<PhotoBeanVO> mData;
    private boolean isFirst;

    public ParallaxViewPagerAdapter(Context context, List<PhotoBeanVO> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_parallax_viewpager_adapter, null);
        ImageViewTouch imageView = (ImageViewTouch) view.findViewById(R.id.id_touch_img);
        PhotoBeanVO photoBeanVO = mData.get(position);
        ImageLoader.getInstance().displayImage(photoBeanVO.getUrl(), imageView);
        container.addView(view);
        return view;
    }

}