package com.nxedu.haircutreserve.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.utils.ToastUtils;
import com.nxedu.haircutreserve.view.DrawableTextView;

import org.kymjs.kjframe.ui.BindView;

/**
 * <p>@description:个人中心-系统设置</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/24
 */

public class GeneralUserCenterSettingActivity extends BaseActivity {

    @BindView(id = R.id.general_user_center_setting_password, click = true)
    private DrawableTextView general_user_center_setting_password;
    @BindView(id = R.id.general_user_center_setting_dataclean, click = true)
    private DrawableTextView general_user_center_setting_dataclean;
    @BindView(id = R.id.general_user_center_setting_fankui, click = true)
    private DrawableTextView general_user_center_setting_fankui;
    @BindView(id = R.id.app_back_im, click = true)
    private ImageView iv_back;
    @BindView(id=R.id.app_top_text)
    private TextView tv_center;
    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_general_user_center_setting);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        tv_center.setText("系统设置");
        tv_center.setVisibility(View.VISIBLE);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        String msg = null;
        switch (v.getId()) {
            case R.id.general_user_center_setting_password:
                msg = "修改密码";
                break;
            case R.id.general_user_center_setting_dataclean:
                msg = "清除缓存";
                break;
            case R.id.general_user_center_setting_fankui:
                msg = "意见反馈";
                break;
            case R.id.app_back_im:
                finish();
                return;
        }
        ToastUtils.showToast(this, msg);
    }
}
