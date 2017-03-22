package com.nxedu.haircutreserve.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nxedu.haircutreserve.R;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

/**
 * 这个是开始页面  哈哈
 *
 * @author dupeng
 * @version 1.0.0
 * @since 2017/3/23 10:23
 */
public class StartActivity extends BaseActivity {

    private TimeCount time;
    @BindView(id = R.id.title_middle_tv)
    private TextView mTvTitle;
    @BindView(id = R.id.edt_login_activity_user_phone)
    private EditText mEdtUserPhone;
    @BindView(id = R.id.edt_login_activity_user_verification_code)
    private EditText mEdtUserVerificationCode;
    @BindView(id = R.id.btn_login_activity_user_verification_code ,click = true)
    private Button mBtnVerificationCode;
    @BindView(id = R.id.btn_login_activity_login ,click = true)
    private Button mBtnUserLogin;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_start);
        time = new TimeCount(60000, 1000);
    }

    @Override
    public void initData() {
        super.initData();
        mTvTitle.setText("理发店管理系统");
    }

    @Override
    public void initWidget() {
        super.initWidget();

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.btn_login_activity_login:
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
                break;

            case R.id.btn_login_activity_user_verification_code:
                time.start();
                break;

            default:
                break;
        }
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            mBtnVerificationCode.setText("再次获取");
            mBtnVerificationCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            mBtnVerificationCode.setClickable(false);//防止重复点击
            mBtnVerificationCode.setText(millisUntilFinished / 1000 + "秒");
        }
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //=====双键退出==
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                ViewInject.toast("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

}

