package app.originality.com.originality.ui.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import app.originality.com.originality.R;
import app.originality.com.originality.bean.PersonalInforVO;
import app.originality.com.originality.storage.OSPUtils;
import app.originality.com.originality.util.AndroidSystemHelper;
import app.originality.com.originality.util.JumpManager;
import app.originality.com.originality.util.StringUtils;

/**
 * @author cjy
 * @version V1.0
 * @company 创意APP
 * @Description 侧边菜单栏
 * @date 2016/4/6
 */
public class PersonalInforFragment extends BaseFragment implements View.OnClickListener, View.OnFocusChangeListener {

    private View mParentLayout, mAgeLayout, mSexLayout, mLabelLayout, mHomeLayout, mPlanLayout, mSettingLayout;
    private ImageView mUserImg;
    private TextView mUserNameText, mUserAgeText, mSexText, mLabelText, mHomeText, mPlanText, mSwitchText;
    private EditText mUserNameEdit, mUserAgeEdit, mLabelEdit, mHomeEdit, mPlanEdit;

    private View hideView;  //标识正在隐藏的View
    private View showView;  //标识正在显示的View

    private PersonalInforVO mPersonalVO;


    @Override
    protected int setView() {
        return R.layout.fragment_personal_infor;
    }

    @Override
    protected void findViews() {
        mParentLayout = this.findViewById(R.id.id_parent_layout);
        mAgeLayout = this.findViewById(R.id.id_personal_age_layout);
        mSexLayout = this.findViewById(R.id.id_personal_sex_layout);
        mLabelLayout = this.findViewById(R.id.id_personal_label_layout);
        mHomeLayout = this.findViewById(R.id.id_personal_home_layout);
        mPlanLayout = this.findViewById(R.id.id_personal_plan_layout);
        mSettingLayout = this.findViewById(R.id.id_personal_setting);

        mUserImg = (ImageView) this.findViewById(R.id.id_personal_img);
        mUserNameText = (TextView) this.findViewById(R.id.id_personal_name_text);
        mUserAgeText = (TextView) this.findViewById(R.id.id_personal_age_text);
        mSexText = (TextView) this.findViewById(R.id.id_personal_sex_text);
        mLabelText = (TextView) this.findViewById(R.id.id_personal_label_text);
        mHomeText = (TextView) this.findViewById(R.id.id_personal_home_text);
        mPlanText = (TextView) this.findViewById(R.id.id_personal_my_plan_text);
        mSwitchText = (TextView) this.findViewById(R.id.id_switch_account);

        mUserNameEdit = (EditText) this.findViewById(R.id.id_personal_name_edit);
        mUserAgeEdit = (EditText) this.findViewById(R.id.id_personal_age_edit);
        mLabelEdit = (EditText) this.findViewById(R.id.id_personal_label_edit);
        mHomeEdit = (EditText) this.findViewById(R.id.id_personal_home_edit);
        mPlanEdit = (EditText) this.findViewById(R.id.id_personal_my_plan_edit);
    }

    @Override
    protected void initEvent() {
        mParentLayout.setOnClickListener(this);
        mAgeLayout.setOnClickListener(this);
        mLabelLayout.setOnClickListener(this);
        mHomeLayout.setOnClickListener(this);
        mPlanLayout.setOnClickListener(this);
        mSettingLayout.setOnClickListener(this);
        mUserImg.setOnClickListener(this);
        mUserNameText.setOnClickListener(this);

        mUserNameEdit.setOnFocusChangeListener(this);
        mUserAgeEdit.setOnFocusChangeListener(this);
        mLabelEdit.setOnFocusChangeListener(this);
        mHomeEdit.setOnFocusChangeListener(this);
        mPlanEdit.setOnFocusChangeListener(this);
        mSwitchText.setOnClickListener(this);
    }

    @Override
    protected void init() {
        mPersonalVO = OSPUtils.getPersonalInfor(mAct);
        if (mPersonalVO == null) {
            mPersonalVO = new PersonalInforVO();
        }
    }

    @Override
    protected void loadData() {
        setPersonalContent();
    }

    public void setPersonalContent() {
        mUserNameText.setText(StringUtils.checkStringIsNull(mPersonalVO.getName()));
        mUserAgeText.setText(StringUtils.checkStringIsNull(mPersonalVO.getAge()));
        mSexText.setText(StringUtils.checkStringIsNull(mPersonalVO.getSex()));
        mLabelText.setText(StringUtils.checkStringIsNull(mPersonalVO.getLabel()));
        mHomeText.setText(StringUtils.checkStringIsNull(mPersonalVO.getIdea_home()));
        mPlanText.setText(StringUtils.checkStringIsNull(mPersonalVO.getPlan()));
    }

    @Override
    public void onClick(View v) {

        //切换账号
        if (v.getId() == R.id.id_switch_account) {
            JumpManager.jumpLoginActivity(mAct);

            //姓名
        } else if (v.getId() == R.id.id_personal_name_text) {
            showAndHideView(mUserNameEdit, v);
            setEditTextContentByText(mUserNameText, mUserNameEdit);
            AndroidSystemHelper.showSoftInput(mAct, mUserNameEdit);

            //年龄
        } else if (v.getId() == R.id.id_personal_age_layout) {
            showAndHideView(mUserAgeEdit, mUserAgeText);
            setEditTextContentByText(mUserAgeText, mUserAgeEdit);
            AndroidSystemHelper.showSoftInput(mAct, mUserAgeEdit);

            //性别
        } else if (v.getId() == R.id.id_personal_age_layout) {
//            showAndHideView(mUserAgeEdit, v);
//            AndroidSystemHelper.showSoftInput(mAct, mUserAgeEdit);
            //标签
        } else if (v.getId() == R.id.id_personal_label_layout) {
            showAndHideView(mLabelEdit, mLabelText);
            setEditTextContentByText(mLabelText, mLabelEdit);
            AndroidSystemHelper.showSoftInput(mAct, mLabelEdit);

            //理想居所
        } else if (v.getId() == R.id.id_personal_home_layout) {
            showAndHideView(mHomeEdit, mHomeText);
            setEditTextContentByText(mHomeText, mHomeEdit);
            AndroidSystemHelper.showSoftInput(mAct, mHomeEdit);

            //我的计划
        } else if (v.getId() == R.id.id_personal_plan_layout) {
            showAndHideView(mPlanEdit, mPlanText);
            setEditTextContentByText(mPlanText, mPlanEdit);
            AndroidSystemHelper.showSoftInput(mAct, mPlanEdit);

            //设置
        } else if (v.getId() == R.id.id_personal_setting) {

            //其他位置
        } else if (v.getId() == R.id.id_parent_layout) {
            AndroidSystemHelper.hideSoftInput(mAct, mParentLayout);
            if (hideView != null) {
                hideView.setVisibility(View.VISIBLE);
            }
            if (showView != null) {
                showView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 展示和隐藏的View
     *
     * @param show
     * @param hide
     */
    public void showAndHideView(View show, View hide) {
        //将之前所调用的展示与隐藏的View归位
        if (hideView != null) {
            hideView.setVisibility(View.VISIBLE);
        }
        if (showView != null) {
            showView.setVisibility(View.GONE);
        }

        //重新展示或隐藏当前所设置的View
        hide.setVisibility(View.GONE);
        hideView = hide;
        show.setVisibility(View.VISIBLE);
        showView = show;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        //姓名
        if (v.getId() == R.id.id_personal_name_edit) {
            if (!hasFocus) {
                setContentByEditText(mUserNameText, mUserNameEdit);
            }
        }

        //年龄
        if (v.getId() == R.id.id_personal_age_edit) {
            if (!hasFocus) {
                setContentByEditText(mUserAgeText, mUserAgeEdit);
            }
        }

        //标签
        if (v.getId() == R.id.id_personal_label_edit) {
            if (!hasFocus) {
                setContentByEditText(mLabelText, mLabelEdit);
            }
        }

        //理想居所
        if (v.getId() == R.id.id_personal_home_edit) {
            if (!hasFocus) {
                setContentByEditText(mHomeText, mHomeEdit);
            }
        }

        //计划
        if (v.getId() == R.id.id_personal_my_plan_edit) {
            if (!hasFocus) {
                setContentByEditText(mPlanText, mPlanEdit);
            }
        }

    }

    /**
     * 将EditText值 复制到TextView中
     *
     * @param textView
     * @param editText
     */
    public void setContentByEditText(TextView textView, EditText editText) {
        String content = editText.getText().toString().trim();
        textView.setText(StringUtils.checkStringIsNull(content));
        if (editText.getId() == R.id.id_personal_name_edit) {
            mPersonalVO.setName(StringUtils.checkStringIsNull(content));
        } else if (editText.getId() == R.id.id_personal_age_edit) {
            mPersonalVO.setAge(StringUtils.checkStringIsNull(content));
        } else if (editText.getId() == R.id.id_personal_label_edit) {
            mPersonalVO.setLabel(StringUtils.checkStringIsNull(content));
        } else if (editText.getId() == R.id.id_personal_home_edit) {
            mPersonalVO.setIdea_home(StringUtils.checkStringIsNull(content));
        } else if (editText.getId() == R.id.id_personal_my_plan_edit) {
            mPersonalVO.setPlan(StringUtils.checkStringIsNull(content));
        }
        OSPUtils.setPersonalInfor(mAct, mPersonalVO);
    }

    /**
     * 设置 EditText内容
     *
     * @param textView
     * @param editText
     */
    public void setEditTextContentByText(TextView textView, EditText editText) {
        String content = textView.getText().toString().trim();
        editText.setText(StringUtils.checkStringIsNull(content));
        editText.setSelection(StringUtils.checkStringIsNull(content).length());
    }
}
