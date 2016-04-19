package app.originality.com.originality.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 *
 * @company Chenjy_Studio
 * @Description 引导界面适配器
 * @author cjy
 * @date 2016/4/6
 * @version V1.0
 */
public class GuidePageAdapter extends PagerAdapter {

    private ArrayList<View> mViews;

    public GuidePageAdapter(ArrayList<View> views) {
        this.mViews = views;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(mViews.get(position));
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}