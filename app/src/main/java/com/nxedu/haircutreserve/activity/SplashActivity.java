package com.nxedu.haircutreserve.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.utils.PreferenceUtils;

import org.kymjs.kjframe.ui.BindView;

import static com.nxedu.haircutreserve.R.id.btn_splash_to_home;
import static com.nxedu.haircutreserve.R.id.iv_choumei;

public class SplashActivity extends BaseActivity {
    @BindView(id = iv_choumei)
    private ImageView imageView;
    @BindView(id = R.id.layout_splash_in)
    private LinearLayout linearLayout;
    @BindView(id = btn_splash_to_home)
    private Button btn_Splash_To_Home;
    @BindView(id = R.id.btn_splash_to_login)
    private Button btn_Splash_To_Login;

    private Boolean isFirst;
    private final Context context = this;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = null;
            switch (msg.what) {
                case 1:
                    Log.v("------isFirst", isFirst + "");
                    if (isFirst) {
                        intent = new Intent(context, GuideActivity.class);
                        PreferenceUtils.setPrefBoolean(context, "isFirst", false);
                    } else {
                        if (PreferenceUtils.getPrefString(context, "phone", null) == null)
                            intent = new Intent(context, StartActivity.class);
                        else
                            intent = new Intent(context, MainActivity.class);
                    }
                    break;
            }
            startActivity(intent);
            finish();
        }
    };

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_splash);

        isFirst = PreferenceUtils.getPrefBoolean(context, "isFirst", true);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, 3000);
    }
}