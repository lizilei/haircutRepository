package com.nxedu.haircutreserve.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.nxedu.haircutreserve.bean.User;

/**
 * /@Description
 * /r
 *
 * @author lzl
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/4/15
 */

public class UserUtils {

    public static String getTel(Context context) {
        return PreferenceUtils.getPrefString(context, "phone", null);
    }

    public static User getUser(Context context) {
        String userInfo = PreferenceUtils.getPrefString(context, "userInfo", null);

        return JSON.parseObject(userInfo, User.class);
    }
}
