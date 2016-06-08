package com.umeng.share.UmengSharedApplication.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.umeng.share.UmengSharedApplication.R;
import com.umeng.share.UmengSharedApplication.bean.PlatformVO;

import java.util.List;
import java.util.ArrayList;

public class ShareHelper {

    public static List<PlatformVO> getShareResource(Context context) {
        String[] descriptions = context.getResources().getStringArray(R.array.share_platform);
        int[] icons = new int[]{R.drawable.btn_share_qq, R.drawable.btn_share_qq_zone, R.drawable.btn_share_weixin, R.drawable.btn_share_pengyouquan, R.drawable.btn_share_sina};
        List<PlatformVO> mData = new ArrayList<PlatformVO>();
        for (int i = 0; i < descriptions.length; i++) {
            PlatformVO platformBean = new PlatformVO();
            platformBean.setIcon(icons[i]);
            platformBean.setDescription(descriptions[i]);
            mData.add(platformBean);
        }
        return mData;
    }

    /**
     * @param listView ListView 或者是GridView
     * @param col      当前为GridView时 标识每行列数
     */
    public static void setListViewHeightBasedOnChildren(GridView listView, int col) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
//        int col = listView.getColumnCout();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度总和
            totalHeight += listItem.getMeasuredHeight() + (listItem.getMeasuredHeight()/3);
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }
}