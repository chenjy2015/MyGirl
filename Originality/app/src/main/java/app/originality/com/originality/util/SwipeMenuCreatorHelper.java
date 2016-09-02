package app.originality.com.originality.util;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.blankj.utilcode.utils.SizeUtils;

import app.originality.com.originality.R;

public class SwipeMenuCreatorHelper {

    public static SwipeMenuCreator createSwipeMenuCreator(final Activity activity) {
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type
                switch (menu.getViewType()) {
                    case 0:
                        createMenu1(activity, menu);
                        break;
                    case 1:
                        createMenu2(activity, menu);
                        break;
                    case 2:
                        createMenu3(activity, menu);
                        break;
                }
            }
        };
        return creator;
    }

    private static void createMenu1(Activity mAct, SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(
                mAct.getApplicationContext());
        item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0x18,
                0x5E)));
        item1.setWidth(SizeUtils.dp2px(mAct, 90));
        item1.setIcon(R.mipmap.ic_action_favorite);
        menu.addMenuItem(item1);
        SwipeMenuItem item2 = new SwipeMenuItem(
                mAct.getApplicationContext());
        item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                0xCE)));
        item2.setWidth(SizeUtils.dp2px(mAct, 90));
        item2.setIcon(R.mipmap.ic_action_good);
        menu.addMenuItem(item2);
    }

    private static void createMenu2(Activity mAct, SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(
                mAct.getApplicationContext());
        item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xE0,
                0x3F)));
        item1.setWidth(SizeUtils.dp2px(mAct, 90));
        item1.setIcon(R.mipmap.ic_action_important);
        menu.addMenuItem(item1);
        SwipeMenuItem item2 = new SwipeMenuItem(
                mAct.getApplicationContext());
        item2.setBackground(new ColorDrawable(Color.rgb(0xF9,
                0x3F, 0x25)));
        item2.setWidth(SizeUtils.dp2px(mAct, 90));
        item2.setIcon(R.mipmap.ic_action_discard);
        menu.addMenuItem(item2);
    }

    private static void createMenu3(Activity mAct, SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(
                mAct.getApplicationContext());
        item1.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,
                0xF5)));
        item1.setWidth(SizeUtils.dp2px(mAct, 90));
        item1.setIcon(R.mipmap.ic_action_about);
        menu.addMenuItem(item1);
        SwipeMenuItem item2 = new SwipeMenuItem(
                mAct.getApplicationContext());
        item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                0xCE)));
        item2.setWidth(SizeUtils.dp2px(mAct, 90));
        item2.setIcon(R.mipmap.ic_action_share);
        menu.addMenuItem(item2);
    }
}