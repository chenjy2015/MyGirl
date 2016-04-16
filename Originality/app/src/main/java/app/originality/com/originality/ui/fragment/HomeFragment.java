package app.originality.com.originality.ui.fragment;

import android.net.Uri;

import app.originality.com.originality.R;

public class HomeFragment extends BaseFragment {


    @Override
    protected int setView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadData() {

    }

    public interface OnFragmentInteractionListener{
        public void onFragmentInteraction(Uri uri);
    }
}
