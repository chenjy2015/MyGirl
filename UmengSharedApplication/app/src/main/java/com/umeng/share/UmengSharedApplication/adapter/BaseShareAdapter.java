package com.umeng.share.UmengSharedApplication.adapter;

import android.app.Activity;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class BaseShareAdapter<T> extends BaseAdapter {
    protected List<T> mList;

    protected Activity mAct;

    public BaseShareAdapter(Activity act, List<T> list) {
        this.mAct = act;
        this.mList = list;
    }

    protected BaseShareAdapter(List<T> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}

