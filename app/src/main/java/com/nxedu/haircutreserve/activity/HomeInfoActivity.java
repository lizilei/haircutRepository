package com.nxedu.haircutreserve.activity;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.nxedu.haircutreserve.R;

import org.kymjs.kjframe.ui.BindView;

public class HomeInfoActivity extends BaseActivity {
    @BindView(id = R.id.web_home)
    private WebView webView;
    @BindView(id = R.id.layout_title_back_info,click = true)
    private RelativeLayout relativeLayout;
    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_home_info);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        webView.loadUrl(getIntent().getStringExtra("url"));
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        finish();
    }
}
