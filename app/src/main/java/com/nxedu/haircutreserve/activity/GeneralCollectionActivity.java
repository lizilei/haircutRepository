package com.nxedu.haircutreserve.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nxedu.haircutreserve.R;

import org.kymjs.kjframe.ui.BindView;

/**
 * <p>@description: 我的收藏页面</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/4/11
 */

public class GeneralCollectionActivity extends BaseActivity {

    @BindView(id = R.id.app_back_im, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.app_top_text)
    private TextView tv_center;
    @BindView(id = R.id.tv_empty)
    private TextView tv_empty;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_general_collect);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tv_center.setVisibility(View.VISIBLE);
        tv_center.setText("我的收藏");
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
