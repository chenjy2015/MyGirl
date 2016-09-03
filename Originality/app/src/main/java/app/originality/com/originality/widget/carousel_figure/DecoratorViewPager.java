package app.originality.com.originality.widget.carousel_figure;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import app.originality.com.originality.ui.BaseActivity;


public class DecoratorViewPager extends ViewPager {

	private ViewGroup parent;

    private Context mContext;

    public DecoratorViewPager(Context context) {
        super(context);
        mContext = context;
    }

    public DecoratorViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void setNestedpParent(ViewGroup parent) {
        this.parent = parent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (parent != null) {
//            parent.requestDisallowInterceptTouchEvent(true);
//        }
        return super.dispatchTouchEvent(ev);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent arg0) {
////        if (parent != null) {
////            parent.requestDisallowInterceptTouchEvent(true);
////        }
//        return super.onInterceptTouchEvent(arg0);
//    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//		System.out.println("InfiniteLoopViewPager onInterceptTouchEvent =====>>>");
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            System.out.println("InfiniteLoopViewPager onInterceptTouchEvent =====>>> ACTION_DOWN");
        } else if(ev.getAction() == MotionEvent.ACTION_MOVE){
//			System.out.println("InfiniteLoopViewPager onInterceptTouchEvent =====>>> ACTION_MOVE");
        } else if(ev.getAction() == MotionEvent.ACTION_UP){
            System.out.println("InfiniteLoopViewPager onInterceptTouchEvent =====>>> ACTION_UP");
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
//        if (parent != null) {
//            parent.requestDisallowInterceptTouchEvent(true);
//        }
        return super.onTouchEvent(arg0);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        //设置当前展示的位置
        setCurrentItem(0);
    }

    private Handler handler;
    public void setInfinateAdapter(Activity act, Handler handler, PagerAdapter adapter){
        this.mContext = (BaseActivity)act;
        this.handler = handler;
        setAdapter(adapter);
    }

    @Override
    public void setCurrentItem(int item) {
        item = getOffsetAmount() + (item % getAdapter().getCount());
        super.setCurrentItem(item);
    }

    /**
     * 从0开始向左可以滑动的次数
     * @return
     */
    private int getOffsetAmount() {
        if (getAdapter() instanceof InfiniteLoopViewPagerAdapter) {
            InfiniteLoopViewPagerAdapter infiniteAdapter = (InfiniteLoopViewPagerAdapter) getAdapter();
            return infiniteAdapter.getRealCount() * 100000;
        } else {
            return 0;
        }
    }

    @Override
    public void setOffscreenPageLimit(int limit) {
        super.setOffscreenPageLimit(limit);
    }

}
