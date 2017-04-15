package com.nxedu.haircutreserve.contacts;

import android.os.Environment;

import java.io.File;

/**
 * <p>@description: 常量</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/29
 */

public class Contacts {

    /**
     * 登录页短信验证
     */
    public static final String sms_app_key = "1c8ef502dfe08";
    public static final String sms_app_secret = "c61f4fa1bb029a75f6cff94166a33ebc";

    public static final String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    public static String PictureDir = ROOT_PATH + "Haircut/image/";

    public static final String[] imgs = new String[]{
            "http://www.faxingzhan.com/uploads/170320/65_100823_1.jpg",
            "http://img.faxingzhan.com/allimg/161223/65-161223163T60-L_260_336.jpg",
            "http://www.faxingzhan.com/uploads/170310/65_090929_1.jpg",
            "http://img.faxingzhan.com/161214/65-161214150320438_260_336.jpg",
            "http://img.faxingzhan.com/160603/54-160603092U2E5_257_385.png",
            "http://img.faxingzhan.com/161118/65-16111Q0242N14_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170324/65-1F3241011360-L_257_385.jpg",
            "http://img.faxingzhan.com/allimg/161116/65-1611161142380-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170322/65-1F322100P40-L_257_385.jpg",
            "http://img.faxingzhan.com/allimg/161116/65-1611161142380-L_260_336.jpg",
            "http://img.faxingzhan.com/150822/52_093750_1_lit_257_385.jpg",
            "http://img.faxingzhan.com/allimg/170118/65-1F11Q606360-L_260_336.jpg",
            "http://img.faxingzhan.com/160527/12_105524_1_lit_257_385.jpg",
            "http://img.faxingzhan.com/allimg/170116/65-1F1161604540-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170324/65-1F3240929190-L_320_420.jpg",
            "http://img.faxingzhan.com/allimg/170209/65-1F20ZS6480-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170321/65-1F3210U3030-L_320_420.jpg",
            "http://img.faxingzhan.com/allimg/170210/65-1F2101453350-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170317/65-1F31F924100-L_320_420.jpg",
            "http://img.faxingzhan.com/allimg/170222/65-1F2221431070-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170320/65-1F3200941420-L_320_420.jpg",
            "http://img.faxingzhan.com/170223/65-1F2230U40C59_260_336.jpg",
            "http://img.faxingzhan.com/170325/12-1F325164G1928_330_350.jpg",
            "http://img.faxingzhan.com/allimg/170223/65-1F2230UP50-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170323/65-1F323144R80-L_260_336.jpg",
            "http://img.faxingzhan.com/170228/65-1F22Q0420a35_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170321/65-1F3211114270-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170307/65-1F30G40R50-L_260_336.jpg",
            "http://img.faxingzhan.com/170303/65-1F30315250H43_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170317/65-1F31G433400-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170302/65-1F3020SI50-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170315/65-1F3150943570-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170306/65-1F3061022150-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170306/65-1F3061522280-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170314/65-1F3141055230-L_260_336.jpg",
            "http://img.faxingzhan.com/allimg/170314/65-1F3141055230-L_260_336.jpg"};


    //http://localhost:8080/haircutht/OrderListInfos?phone=17301207022

    /**
     * 内网地址
     */
    public static final String ROOT_URL = "http://10.0.2.2:8080/haircutht/";


    /**
     * 登录接口
     */
    public static final String GET_USER_LOGIN = ROOT_URL + "QUserinfos?tel=";

    /**
     * 获取订单列表
     * GET 请求
     * params tel  用户电话号码
     */
    public static final String GET_ORDER_LIST = ROOT_URL + "OrderListInfos?tel=";

    /**
     * 添加新用户  在发送验证码之前调用
     */
    public static final String GET_ADD_USER = ROOT_URL + "AddUserInfo?tel=";

    /**
     * 修改用户信息
     * 需要参数：用户属性值
     */
    public static final String GET_UPDATE_USER = ROOT_URL + "UpdateUserInfo?tel=";
    /**
     * 购物车待支付订单
     * 需要参数：用户属性值
     */
    public static final String GET_SHOP_ORDER = ROOT_URL + "OrderPayList?tel=";
    /**
     * 获取商铺列表
     */
    public static final String GET_GOOD_SHOP = ROOT_URL + "GoodShop";
    /**
     * 获取首页轮播图
     */
    public static final String GET_HEAD_CAROUSEL = ROOT_URL + "HeadCarousel";
    /**
     * 获取店铺员工
     * 需要参数：shop_id
     */
    public static final String GET_HAIRCUT_LIST = ROOT_URL + "HaircutList";
    /**
     * 获取发型类别
     */
    public static final String GET_HAIR_STYLE = ROOT_URL + "Hairstyle";
    /**
     * 获取首页发型师
     */
    public static final String GET_HAIRSTYLIST = ROOT_URL + "HairStylist";
}
