package com.nxedu.haircutreserve.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/23
 */

public class ToastUtils {
    private static long lastClickTime;

    public static void showToast(final Activity context, final String msg) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!isFastClick()) {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}
