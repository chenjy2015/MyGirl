package app.originality.com.originality.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.originality.com.originality.R;
import app.originality.com.originality.application.OAHelper;
import app.originality.com.originality.application.OApplication;
import app.originality.com.originality.bean.UserInfor;
import app.originality.com.originality.storage.OSPUtils;
import app.originality.com.originality.util.JumpManager;
import app.originality.com.originality.util.StringUtils;
import app.originality.com.originality.util.ToastUtils;

/**
 * @author cjy
 * @version V1.0
 * @company Chenjy_Studio
 * @Description 登录页面
 * @date 2016/4/5
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mAccountEdit;
    private EditText mPasswordEdit;
    private String mAccount;
    private String mPassword;
    private TextView mRegsiterText;
    private TextView mForgetText;
    private TextView mLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initEvent();
    }

    @Override
    protected int setView() {
        return R.layout.activity_login;
    }

    @Override
    protected void findViews() {
        mAccountEdit = (EditText) this.findViewById(R.id.id_login_account);
        mPasswordEdit = (EditText) this.findViewById(R.id.id_login_password);
        mRegsiterText = (TextView) this.findViewById(R.id.id_login_register);
        mForgetText = (TextView) this.findViewById(R.id.id_login_forget);
        mLoginText = (TextView) this.findViewById(R.id.id_login_submit);
    }

    @Override
    protected void initEvent() {
        mRegsiterText.setOnClickListener(this);
        mForgetText.setOnClickListener(this);
        mLoginText.setOnClickListener(this);

        mAccountEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    ToastUtils.showBigVioletToastOnBottom(getApplicationContext(),StringUtils.checkStringIsNull(checkRequire()));
            }
        });

        mPasswordEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    ToastUtils.showBigVioletToastOnBottom(getApplicationContext(),StringUtils.checkStringIsNull(checkRequire()));
            }
        });
    }

    @Override
    protected void init() {
        mTitleBar.hideTitleLayout();
    }

    @Override
    protected void loadData() {

    }

    public String checkRequire() {
        String error = "";
        mAccount = mAccountEdit.getText().toString().trim();
        mPassword = mPasswordEdit.getText().toString().trim();
        if (StringUtils.isNullOrEmpty(mAccount)) {
            return "请输入账号!";
        } else if (!StringUtils.checkAccount(mAccount)) {
            return "请按账号规则输入!";
        }

        if (StringUtils.isNullOrEmpty(mPassword)) {
            return "请输入密码!";
        } else if (!StringUtils.checkPassword(mPassword)) {
            return "请按密码规则输入!";
        }

        return error;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.id_login_register) {
            JumpManager.jumpRegisterActivity(this);
        } else if (v.getId() == R.id.id_login_forget) {

        } else if (v.getId() == R.id.id_login_submit) {
            if (!checkRequire().equals("")) {
                ToastUtils.showBigVioletToastOnBottom(getApplicationContext(), checkRequire());
            } else {
                JumpManager.jumpHomeActivity(this);
            }
        }
    }
}
