package com.nxedu.haircutreserve.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.utils.AppUtils;
import com.nxedu.haircutreserve.utils.PreferenceUtils;
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

    private String phone;
    private Context context = this;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    ToastUtils.showToast((Activity) context, "登陆成功");
                    PreferenceUtils.setPrefString(context, "phone", phone);
                    startActivity(new Intent(context, MainActivity.class));
                    finish();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    mEdtUserPhone.setFocusable(false);
                    mEdtUserPhone.setFocusableInTouchMode(false);
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                }
            } else {
                mEdtUserPhone.setFocusableInTouchMode(true);
                mEdtUserPhone.setFocusable(true);
                mEdtUserPhone.requestFocus();
                ((Throwable) data).printStackTrace();
                ToastUtils.showToast((Activity) context, ((Throwable) data).getMessage());
                Log.i("---log", ((Throwable) data).getMessage());
            }
        }
    };


    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_start);
        time = new TimeCount(60000, 1000);

        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = Message.obtain();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandler.sendMessage(msg);
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
        phone = mEdtUserPhone.getText().toString().trim();
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.btn_user_login:
                String verCode = mEdtUserVerificationCode.getText().toString().trim();
                if (phone.equals("") || phone == null) {
                    ToastUtils.showToast(this, "请先输入手机号");
                    return;
                }
                if (verCode.equals("") || verCode == null) {
                    ToastUtils.showToast(this, "请先输入验证码");
                    return;
                }
                SMSSDK.submitVerificationCode("86", phone, verCode);
                break;
            case R.id.btn_login_activity_user_verification_code:
                if (phone.equals("") || phone == null) {
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
                        Log.i("---log", country + "---" + phone);

//                        if (country == "cn") {//此号码无须实际接收短信
//                            return false;
//                        } else if (country == "en") {
//                            return false;
//                        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}