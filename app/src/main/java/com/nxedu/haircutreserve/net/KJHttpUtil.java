package com.nxedu.haircutreserve.net;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.http.HttpParams;


/**
 * 时间： 2016/11/10 10:58
 * ---ZWQ---
 */
public class KJHttpUtil {
    private static final String TAG = "KJHttpUtil";

    private static KJHttp kjh;

    private static KJHttp getKjHttp() {
        if (kjh == null) {
            HttpConfig config = new HttpConfig();
            //超时时间
            config.TIMEOUT = 10000000;
            kjh = new KJHttp();
        } else {
            kjh.cleanCache();
        }
        return kjh;
    }

    public static void getHttp(String url, HttpParams httpParams, boolean isCache,
                               HttpCallBack callBack) {
        getKjHttp().get(url, httpParams, isCache, callBack);
    }

    public static void getHttp(String url, HttpCallBack callBack) {
        getKjHttp().get(url, callBack);
    }

    public static void postHttp(String url, HttpParams httpParams, boolean isCache,
                                HttpCallBack callBack) {
        getKjHttp().post(url, httpParams, isCache, callBack);
    }

    public static void postHttp(String url, HttpParams params, HttpCallBack callBack) {
        getKjHttp().post(url, params, callBack);
    }


    /**
     * 下载apk
     */
    public static void upAPK(String path, String url, HttpCallBack callBack) {
        kjh.download(path, url, callBack);
    }
}
