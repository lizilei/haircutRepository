package com.nxedu.haircutreserve.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.utils.ToastUtils;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/**
 * 这个是开始页面  哈哈
 *
 * @author dupeng
 * @version 1.0.0
 * @since 2017/3/23 10:23
 */
public class StartActivity extends BaseActivity {

    private TimeCount time;
    //    @BindView(id = R.id.title_middle_tv)
//    private TextView mTvTitle;
    @BindView(id = R.id.edt_login_activity_user_phone)
    private EditText mEdtUserPhone;
    @BindView(id = R.id.edt_login_activity_user_verification_code)
    private EditText mEdtUserVerificationCode;
    @BindView(id = R.id.btn_login_activity_user_verification_code, click = true)
    private Button mBtnVerificationCode;
    @BindView(id = R.id.app_back_im)
    private ImageView iv_back;
    @BindView(id = R.id.btn_user_login, click = true)
    private Button btn_user_login;
    private EventHandler eh;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_start);
        time = new TimeCount(60000, 1000);
        eh=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                    }
                }else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @Override
    public void initData() {
        super.initData();
//        mTvTitle.setText("理发店管理系统");
        iv_back.setVisibility(View.GONE);
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.btn_user_login:
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                ToastUtils.showToast(this, "登陆成功！");
                finish();
                break;
            case R.id.btn_login_activity_user_verification_code:
                ToastUtils.showToast(this, "发短信了");
                SMSSDK.getVerificationCode("+86", "17326916924", new OnSendMessageHandler() {
                    @Override
                    public boolean onSendMessage(String s, String s1) {
                        Log.e("---log",s+"---"+s1);
                        return false;
                    }
                });
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}