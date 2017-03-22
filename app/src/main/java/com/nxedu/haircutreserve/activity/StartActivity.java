package com.nxedu.haircutreserve.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.nxedu.haircutreserve.R;

import org.kymjs.kjframe.ui.BindView;

/**
 * @author dupeng
 * @version 1.0.0
 * @since 2017/3/23 10:23
 */
public class StartActivity extends BaseActivity {

    @BindView(id = R.id.tv, click = true)
    private TextView tv;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_start);
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
            case R.id.tv:
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                break;
            default:
        }
    }
}

