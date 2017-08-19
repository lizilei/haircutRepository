package com.nxedu.haircutreserve.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.KJDB;
import org.kymjs.kjframe.ui.ViewInject;
/**
 * 这个是基类Activity
 *
 * @author dupeng
 * @version 1.0.0
 * @since 2017/3/23 10:23
 */
public abstract class BaseActivity extends KJActivity {
    private static final String TAG = "BaseActivity";

    public static KJDB UserDB;
    private ConnectionChangeReceiver connectionReceiver;
    private Context context;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        context = this;
    }

    @Override
    public void setRootView() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 竖屏锁定
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        //StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.colorPrimary));

    }

    public class ConnectionChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mobNetInfo = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifiNetInfo = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mobNetInfo == null && !wifiNetInfo.isConnected()) {
                ViewInject.toast("网络不可用，请检查网络设置");
                return;
            } else if (wifiNetInfo == null || (mobNetInfo != null &&
                    !mobNetInfo.isConnected() && !wifiNetInfo.isConnected())) {
                ViewInject.toast("网络不可用，请检查网络设置");
            } else {
            }
        }
    }

    @Override
    public void registerBroadcast() {
        //代码注册广播接收器
        IntentFilter filter2 = new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION);// 使用ConnectivityManager监听网络状态变化
        connectionReceiver = new ConnectionChangeReceiver();
        this.registerReceiver(connectionReceiver, filter2);
    }

    @Override
    public void unRegisterBroadcast() {
        if (connectionReceiver != null) {
            BaseActivity.this.unregisterReceiver(connectionReceiver);
            connectionReceiver = null;
        }
    }

    //滚动条显示
    public void startProgressDialog(String msg, Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(msg);
        }
        progressDialog.show();
    }
    //停止滚动条
    public void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * 获取当前版本号
     * @return
     */
    public int getVersionCode(){
        PackageManager packageManager = getPackageManager();
        int versionCodes = 0;
        try {
            PackageInfo info = packageManager.getPackageInfo(getPackageName(),0);
            versionCodes = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCodes;
    }
}
