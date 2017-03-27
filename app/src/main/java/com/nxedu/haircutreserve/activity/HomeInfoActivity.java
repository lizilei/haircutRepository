package com.nxedu.haircutreserve.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nxedu.haircutreserve.R;

import org.kymjs.kjframe.ui.BindView;

public class HomeInfoActivity extends BaseActivity {
    @BindView(id = R.id.web_home)
    private WebView webView;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_home_info);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        webView.loadUrl("http://m.faxingzhan.com/a/45052.html");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
