package com.nxedu.haircutreserve.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nxedu.haircutreserve.R;

import org.kymjs.kjframe.ui.BindView;

import static android.R.attr.id;
import static com.nxedu.haircutreserve.R.id.btn_splash_to_home;
import static com.nxedu.haircutreserve.R.id.iv_choumei;

public class SplashActivity extends AppCompatActivity {
    @BindView(id = iv_choumei)
    private ImageView imageView;
    @BindView(id = R.id.layout_splash_in)
    private LinearLayout linearLayout;
    @BindView(id = btn_splash_to_home)
    private Button btn_Splash_To_Home;
    @BindView(id = R.id.btn_splash_to_login)
    private Button btn_Splash_To_Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }
}
