package app.originality.com.originality.adapter;

import android.app.Activity;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author cjy
 * @version V1.0
 * @company 创意APP
 * @Description 适配器基础类
 * @date 2016/4/6
 */
public abstract class OriginalityBaseAdapter<T> extends BaseAdapter {

    protected Activity mAct;
    protected List<T> mData;

    public OriginalityBaseAdapter(Activity act, List<T> data) {
        this.mAct = act;
        this.mData = data;
    }


    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}