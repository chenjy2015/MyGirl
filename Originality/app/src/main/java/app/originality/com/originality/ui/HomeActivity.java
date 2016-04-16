package app.originality.com.originality.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import app.originality.com.originality.R;
import app.originality.com.originality.ui.fragment.BaseFragment;
import app.originality.com.originality.ui.fragment.HomeFragment;
import app.originality.com.originality.ui.fragment.PersonalInforFragment;
import app.originality.com.originality.util.StringHelper;
import app.originality.com.originality.util.ToastUtils;
import app.originality.com.residemenu.ResideMenu;
import app.originality.com.residemenu.ResideMenuItem;

import java.util.ArrayList;

/**
 * @author cjy
 * @version V1.0
 * @company 创意APP 首页
 * @Description
 * @date 2016/4/7
 */
public class HomeActivity extends FragmentActivity implements View.OnClickListener, HomeFragment.OnFragmentInteractionListener {

    private ResideMenu resideMenu;
    //    private ResideMenuItem itemMenu;
    private ArrayList<String> mMenus;
    protected BaseFragment mCurrentSelectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        setUpMenu();
        if (savedInstanceState == null)
            changeFragment(new HomeFragment());
    }

    public void init() {
        mMenus = StringHelper.getMenusContent(getApplicationContext(), R.array.menus);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }


    private void setUpMenu() {
        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.mipmap.scenery);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        //add Head
        ResideMenuItem item = new ResideMenuItem(this, R.mipmap.scenery2);
        item.setOnClickListener(this);
        item.setTag(10);
        resideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT);

        // create menu items;
        for (int i = 0; i < mMenus.size(); i++) {
            ResideMenuItem itemMenu = new ResideMenuItem(this, mMenus.get(i));
            itemMenu.setOnClickListener(this);
            itemMenu.setTag(i);
            resideMenu.addMenuItem(itemMenu, ResideMenu.DIRECTION_LEFT);
        }
    }


    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(getApplicationContext(), "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(getApplicationContext(), "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(BaseFragment targetFragment) {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
        mCurrentSelectFragment = targetFragment;
    }

    // What good method is to access resideMenu锛�
    public ResideMenu getResideMenu() {
        return resideMenu;
    }

    @Override
    public void onClick(View view) {
        int tag = (Integer) view.getTag();
        if (tag == 10) {
            changeFragment(new PersonalInforFragment());
        }
        resideMenu.closeMenu();
    }

    /**
     * Fragment 调用监听
     *
     * @param uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isTackOver = false;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mCurrentSelectFragment != null) {
                isTackOver = mCurrentSelectFragment.onKeyDown(keyCode, event);
            }
            if (!isTackOver) {
                reTryExit();
            }
            return isTackOver;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long mExitTime = 0;

    private void reTryExit() {

        final int MAX_MIS = 2000;
        if ((System.currentTimeMillis() - mExitTime) > MAX_MIS) {
            // Toast.makeText(getApplicationContext(), R.string.retry_exit, Toast.LENGTH_SHORT).show();
            ToastUtils.showBigToast(getApplicationContext(), getResources().getString(R.string.retry_exit));
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }
}
