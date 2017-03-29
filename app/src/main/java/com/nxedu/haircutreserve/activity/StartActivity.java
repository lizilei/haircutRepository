package com.nxedu.haircutreserve.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.utils.AppUtils;
import com.nxedu.haircutreserve.utils.ToastUtils;

import org.kymjs.kjframe.ui.BindView;

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

    //验证码
    private String ver_code;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_start);
        time = new TimeCount(60000, 1000);

        eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        ver_code = result + "";
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    ToastUtils.showToast(StartActivity.this, ((Throwable) data).getMessage());
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
                String verCode = mEdtUserVerificationCode.getText().toString().trim();
                if (verCode.equals(ver_code)) {
                    startActivity(new Intent(StartActivity.this, MainActivity.class));
                    ToastUtils.showToast(this, "登陆成功！");
                    finish();
                } else {
                    ToastUtils.showToast(this, "登陆失败，请输入正确的验证码...");
                }

                break;
            case R.id.btn_login_activity_user_verification_code:
                String phone = mEdtUserPhone.getText().toString().trim();
                if (phone.equals("") && phone == null) {
                    ToastUtils.showToast(this, "请先输入手机号");
                    return;
                }

                if (!AppUtils.isMobile(phone)) {
                    ToastUtils.showToast(this, "请输入有效的手机号码...");
                    return;
                }

                SMSSDK.getVerificationCode("+86", phone, new OnSendMessageHandler() {
                    @Override
                    public boolean onSendMessage(String country, String phone) {
                        Log.e("---log", country + "---" + phone);

                        if (country == "cn") {//此号码无须实际接收短信
                            return false;
                        } else if (country == "en") {
                            return false;
                        }
                        return true;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}