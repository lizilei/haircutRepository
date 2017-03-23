package com.nxedu.haircutreserve.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;

import com.nxedu.haircutreserve.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>@description:乱写的工具类</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/23
 */
public class AppUtils {

    private static long lastClickTime;
    private static DelListener delListener;

    /**
     * 从asset中获取文件并读取数据（资源文件只能读不能写）
     *
     * @param context
     * @param fileName 文件名（yan.txt）
     * @return
     */
    public static String getAgreement(Context context, String fileName) {
        String res = "";
        try {
            InputStream is = context.getClass().
                    getClassLoader().getResourceAsStream("assets/" + fileName);
            byte[] buffer = new byte[is.available()];
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            int length = 1;
            while ((length = is.read(buffer)) != -1) {
                stream.write(buffer, 0, length);
            }
            res = stream.toString();
            stream.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获取空数据时的布局
     *
     * @param context
     * @return
     */
    public static View getEmptyView(Context context) {

        return LayoutInflater.from(context).inflate(R.layout.layout_empty, null);
    }

    /**
     * 验证手机号是否合法
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[3,7,8]))\\d{8}$");

        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 格式化数字，保留两位小数
     *
     * @param d
     * @return
     */
    public static String getDouble(Double d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String decimalBalanceString = decimalFormat.format(d);//format 返回的是字符串
        return decimalBalanceString;
    }

    /**
     * 格式化数字，不使用科学计数法
     *
     * @param d
     * @return
     */
    public static double getDouble1(Double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#######.00");
        String decimalBalanceString = decimalFormat.format(d);//format 返回的是字符串
        return Double.valueOf(decimalBalanceString);
    }

    /**
     * 格式化数字，显示为金融样式
     * <p/>
     * 9,999,999.00
     *
     * @param d
     * @return
     */
    public static String getDouble2(Double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#，###，###.00");
        String decimalBalanceString = decimalFormat.format(d);//format 返回的是字符串
        return decimalBalanceString;
    }

    /**
     * 删除图片的弹框
     *
     * @param context 上下文
     * @return true 确定删除；false不删除
     */
    public static void delPic(Context context, DelListener delListener1) {
        delListener = delListener1;
        Dialog dialog = new AlertDialog.Builder(context)
                .setMessage("确定删除该图片？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delListener.onClick(false);
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delListener.onClick(true);
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 根据Uri获取图片的绝对路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 判断网路是否可以用
     *
     * @return
     */
    public static boolean checkNetwork(Context context) {
//        return isNetworkConnected(context)
//                && (isWifiConnected(context) || isMobileConnected(context));
        if (isNetworkConnected(context)) {
            if (isWifiConnected(context) || isMobileConnected(context)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断wifi 是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断手机网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断当前应用程序处于前台还是后台
     *
     * @param context
     * @return
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断一个应用是否在运行
     */
    public static boolean isLauncherRunnig(Context context) {
        boolean result = false;
        List<String> names = getAllTheLauncher(context);
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appList = mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo running : appList) {
            if (running.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (int i = 0; i < names.size(); i++) {
                    if (names.get(i).equals(running.processName)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 判断一个Activity是否已启动
     *
     * @param mContext
     * @param activityClassName
     * @return
     */
    public static boolean isActivityRunning(Context mContext, String activityClassName) {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info = activityManager.getRunningTasks(1);
        if (info != null && info.size() > 0) {
            ComponentName component = info.get(0).topActivity;
            if (activityClassName.equals(component.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * getAllTheLauncher
     *
     * @return
     */
    private static List<String> getAllTheLauncher(Context context) {
        List<String> names = null;
        PackageManager pkgMgt = context.getPackageManager();
        Intent it = new Intent(Intent.ACTION_MAIN);
        it.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> ra = pkgMgt.queryIntentActivities(it, 0);
        if (ra.size() != 0) {
            names = new ArrayList<>();
        }
        for (int i = 0, j = ra.size(); i < j; i++) {
            String packageName = ra.get(i).activityInfo.packageName;
            names.add(packageName);
        }
        return names;
    }

    /**
     * 判断是否快速双击点击
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 300) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 是否快速的点击按钮
     *
     * @return true为快速点击
     */
    public static boolean isFastClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 拨打电话
     *
     * @return 拨打电话
     */
    public static void toTel(final Context context, final String tel, String label) {
        if (tel == null || tel.isEmpty()) {
            ToastUtils.showToast((Activity) context, "暂无电话号码");
            return;
        }
        new AlertDialog.Builder(context).setTitle("提示").setMessage("拨打" + tel + "?").setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
                context.startActivity(intent);
                dialog.dismiss();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    /**
     * 返回按钮提示
     */
    public static void showNotice(final Activity context) {
        new android.support.v7.app.AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("确定要放弃这次修改?")
                .setPositiveButton("果断放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.finish();
                    }
                })
                .setNegativeButton("继续修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    /**
     * 图片删除的回调监听接口
     */
    public interface DelListener {
        void onClick(boolean isDelete);
    }

    /**
     * 通用获取IP的方法
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            return ex.getMessage();
        }
        return null;
    }
}