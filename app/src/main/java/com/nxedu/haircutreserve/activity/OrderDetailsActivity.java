package com.nxedu.haircutreserve.activity;

import android.view.View;
import android.widget.ImageView;

import com.nxedu.haircutreserve.R;

import org.kymjs.kjframe.ui.BindView;

/**
 * <p>@description: 个人中心订单详情页</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/29
 */

public class OrderDetailsActivity extends BaseActivity {

    @BindView(id = R.id.app_back_im, click = true)
    private ImageView iv_back;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_order_details);
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
        switch (v.getId()) {
            case R.id.app_back_im:
                finish();
                break;
        }
    }
}
