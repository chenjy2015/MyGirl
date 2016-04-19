package app.originality.com.originality.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.originality.com.originality.R;
import app.originality.com.originality.util.AndroidSystemHelper;
import app.originality.com.originality.util.StringUtils;


/**
 * @company: Chenjy_Studio
 * @Description: 自定义Title布局
 * @author: cjy
 * @date: 2015-11-24
 * @version: v1.0
 */
public class TitleBar extends RelativeLayout {

    private LayoutInflater mLayoutInflater;
    private LinearLayout mParentView;
    private RelativeLayout mLeftLayout;
    private RelativeLayout mRightLayout;

    private RelativeLayout mCenterLayout; // title 中心位置布局父View
    private RelativeLayout mCenterEditLayout; // title 中心位置输入框父View
    private EditText mCenterEditText;// title 中心位置输入框
    private TextView mCenterText;// title 中心位置标题
    private RelativeLayout mCenterBtnDel;// title 中心位置输入框删除控件父View
    private ImageView mLeftImg;// title 左侧退出按钮
    private ImageView mRightImg;// title 右侧图标
    private TextView mRightText;// title 右侧文字

    private Context mContext;

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        initView(context);
    }

    public TitleBar(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        initView(context);
    }

    /**
     * 初始化所有View
     *
     * @param context
     */
    private void initView(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mParentView = (LinearLayout) mLayoutInflater.inflate(
                R.layout.title_layout, null);
        this.addView(mParentView);
        mParentView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

        mCenterLayout = (RelativeLayout) mParentView
                .findViewById(R.id.title_center_layout);
        mCenterEditLayout = (RelativeLayout) mParentView
                .findViewById(R.id.edit_layout);
        mCenterText = (TextView) mParentView
                .findViewById(R.id.title_center_text);
        mCenterEditText = (EditText) mParentView
                .findViewById(R.id.title_center_edit);
        mCenterBtnDel = (RelativeLayout) mParentView
                .findViewById(R.id.title_center_btn_del);

        mLeftLayout = (RelativeLayout) mParentView
                .findViewById(R.id.title_left_layout);
        mLeftImg = (ImageView) mParentView.findViewById(R.id.title_left_img);

        mRightLayout = (RelativeLayout) mParentView
                .findViewById(R.id.title_right_layout);
        mRightImg = (ImageView) mParentView.findViewById(R.id.title_right_img);
        mRightText = (TextView) mParentView.findViewById(R.id.title_right_text);
    }


    public void hideTitleLayout() {
        mParentView.setVisibility(View.GONE);
    }

    /**
     * 设置title标题内容
     *
     * @param content
     */
    public void setCenterText(String content) {
        mCenterText.setText(content);
        mCenterText.setVisibility(View.VISIBLE);
        mCenterEditLayout.setVisibility(View.GONE);
    }

    /**
     * 隐藏 左侧按钮
     */
    public void setLeftLayoutHint() {
        mLeftLayout.setVisibility(View.INVISIBLE);
    }

    /**
     * 设置title搜索框背景
     */
    public void setCenterEditBackground() {
        mCenterText.setVisibility(View.GONE);
        mCenterEditLayout.setVisibility(View.VISIBLE);
        mCenterBtnDel.setOnClickListener(new onTitleViewClickListenner());
        int sdk = android.os.Build.VERSION.SDK_INT;// API4.0以前的没有setbackground方法
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {

        } else {

        }
        AndroidSystemHelper.ShowKeyboard(mCenterEditText);
    }

    /**
     * 设置title搜索框提示语
     *
     * @param hintText
     */
    public void setCenterEditHint(String hintText) {
        mCenterEditText.setHint(hintText);
        mCenterEditText.setSelection(mCenterEditText.getText().toString()
                .trim().length());
    }

    /**
     * 设置输入框中内容
     *
     * @param content
     */
    public void setCenterEditTextContent(String content) {
        mCenterEditText.setText(StringUtils.checkStringIsNull(content));
        mCenterEditText.setSelection(content.length());
    }

    /**
     * 得到 输入框文本信息
     *
     * @return
     */
    public String getCenterEditTextContent() {
        return StringUtils.checkStringIsNull(mCenterEditText.getText()
                .toString().trim());
    }

    /**
     * 设置输入框不可编辑 当图片使用点击事件
     *
     * @param listenner
     */
    public void setCenterEditClickListenner(OnClickListener listenner, String editHintText) {
        mCenterText.setVisibility(View.GONE);
        mCenterEditLayout.setVisibility(View.VISIBLE);
        mCenterEditText.setEnabled(false);
        mCenterEditText.setFocusable(false);
        mCenterEditText.setHint(editHintText);
        mCenterBtnDel.setVisibility(View.GONE);
        mCenterEditText.setVisibility(View.VISIBLE);
        mCenterEditLayout.setVisibility(View.VISIBLE);
        mCenterLayout.setOnClickListener(listenner);
    }

    /**
     * 设置title 左边 ImageView 背景 自动设置左边Layout背景
     *
     * @param
     */
    public void setLeftImgBackground() {
        int sdk = android.os.Build.VERSION.SDK_INT;// API4.0以前的没有setbackground方法
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            mLeftImg.setBackgroundResource(
                    R.mipmap.icon_btn_back);
            mLeftLayout.setBackgroundResource(
                    R.drawable.background_title_click);
        } else {
            mLeftImg.setBackgroundResource(
                    R.mipmap.icon_btn_back);
            mLeftLayout.setBackgroundResource(
                    R.drawable.background_title_click);
        }
        mLeftImg.setVisibility(View.VISIBLE);
        mLeftLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置 title 右侧TextView内容
     *
     * @param content
     */
    public void setRightText(String content) {
        mRightText.setText(content);
        mRightText.setVisibility(View.VISIBLE);
        mRightImg.setVisibility(View.GONE);
        int sdk = android.os.Build.VERSION.SDK_INT;// API4.0以前的没有setbackground方法
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            mRightLayout.setBackgroundDrawable(getResources().getDrawable(
                    R.drawable.background_title_click));
        } else {
            mRightLayout.setBackgroundResource(
                    R.drawable.background_title_click);
        }
        mRightLayout.setVisibility(View.VISIBLE);
    }

    public void setRightTextSize(int size) {
        mRightText.setTextSize(size);
    }

    /**
     * 设置title 右边 ImageView 背景 并自动隐藏右边textview文字 视图 自动设置右边Layout背景
     *
     * @param resId
     */
    public void setRightImgBackground(int resId) {
        int sdk = android.os.Build.VERSION.SDK_INT;// API4.0以前的没有setbackground方法
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            mRightImg.setBackgroundResource(resId);
            mRightLayout.setBackgroundResource(
                    R.drawable.background_title_click);
        } else {
            mRightImg.setBackgroundResource(resId);
            mRightLayout.setBackgroundResource(
                    R.drawable.background_title_click);
        }
        mRightText.setVisibility(View.GONE);
        mRightImg.setVisibility(View.VISIBLE);
        mRightLayout.setVisibility(View.VISIBLE);
    }

    /**
     * 设置 左右两边按钮点击事件
     *
     * @param listenner
     */
    public void setRightLayoutOnClickListenner(OnClickListener listenner) {
        mRightLayout.setOnClickListener(listenner);
    }

    public void setLeftLayoutClickListenner(OnClickListener listenner) {
        mLeftLayout.setOnClickListener(listenner);
    }

    /**
     * 头部 布局点击事件处理
     */
    private class onTitleViewClickListenner implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.title_center_btn_del: // 输入框删除按钮
                    mCenterEditText.setText("");
                    break;
            }
        }
    }

}
