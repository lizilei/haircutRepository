package com.nxedu.haircutreserve.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.utils.ToastUtils;

import org.kymjs.kjframe.ui.BindView;

/**
 * <p>@description:个人中心-用户信息设置</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/24
 */

public class PersonalDetailsActivity extends BaseActivity {

    @BindView(id = R.id.person_detail_avatar, click = true)
    private RelativeLayout person_detail_avatar;
    @BindView(id = R.id.person_detail_nickname, click = true)
    private LinearLayout person_detail_nickname;
    @BindView(id = R.id.person_detail_sex, click = true)
    private LinearLayout person_detail_sex;
    @BindView(id = R.id.person_detail_phone, click = true)
    private LinearLayout person_detail_phone;
    @BindView(id = R.id.person_detail_diff, click = true)
    private LinearLayout person_detail_diff;
    @BindView(id = R.id.person_detail_signature, click = true)
    private LinearLayout person_detail_signature;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_personal_details);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        String msg = null;
        switch (v.getId()) {
            case R.id.person_detail_avatar:
                msg = "换头像啦";
                break;
            case R.id.person_detail_nickname:
                msg = "换昵称啦";
                break;
            case R.id.person_detail_sex:
                msg = "换性别啦";
                break;
            case R.id.person_detail_phone:
                msg = "换电话好啦";
                break;
            case R.id.person_detail_diff:
                msg = "换个人信息啦";
                break;
            case R.id.person_detail_signature:
                msg = "换个性签名啦";
                break;
        }
        ToastUtils.showToast(this, msg);
    }
}