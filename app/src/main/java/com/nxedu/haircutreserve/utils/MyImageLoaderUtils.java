package com.nxedu.haircutreserve.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nxedu.haircutreserve.R;

/**
 * /@Description
 * /r
 *
 * @author lzl
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/23
 */

public class MyImageLoaderUtils {

    /**
     * 自定义Option
     *
     * @param url
     * @param iv
     * @param options
     */
    public static void loadImage(String url, ImageView iv, DisplayImageOptions options) {
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.ic_launcher) //设置图片在下载期间显示的图片
//                .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.mipmap.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
//                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
//                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
//                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
//                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
//                //设置图片加入缓存前，对bitmap进行设置
//                //.preProcessor(BitmapProcessor preProcessor)
//                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
//                .build();//构建完成
        ImageLoader.getInstance().displayImage(url, iv, options);
    }

    /**
     * 头像专用
     *
     * @param url
     * @param target
     */
    public static void displayHeadIcon(String url, ImageView target) {
        ImageLoader.getInstance().displayImage(url, target, getOptions4Header());

    }

    /**
     * 图片详情页专用
     *
     * @param url
     * @param target
     * @param loadingListener
     */
    public static void displayImage4Detail(String url, ImageView target, SimpleImageLoadingListener loadingListener) {
        ImageLoader.getInstance().displayImage(url, target, getOption4ExactlyType(), loadingListener);
    }

    /**
     * 图片列表页专用
     *
     * @param url
     * @param target
     * @param loadingResource
     * @param loadingListener
     * @param progressListener
     */
    public static void displayImageList(String url, ImageView target, int loadingResource, SimpleImageLoadingListener loadingListener, ImageLoadingProgressListener progressListener) {
        ImageLoader.getInstance().displayImage(url, target, getOptions4PictureList(loadingResource), loadingListener, progressListener);
    }

    /**
     * 自定义加载中图片
     *
     * @param url
     * @param target
     * @param loadingResource
     */
    public static void displayImageWithLoadingPicture(String url, ImageView target, int loadingResource) {
        ImageLoader.getInstance().displayImage(url, target, getOptions4PictureList(loadingResource));
    }

    /**
     * 当使用WebView加载大图的时候，使用本方法现下载到本地然后再加载
     *
     * @param url
     * @param loadingListener
     */
    public static void loadImageFromLocalCache(String url, SimpleImageLoadingListener loadingListener) {
        ImageLoader.getInstance().loadImage(url, getOption4ExactlyType(), loadingListener);
    }

    /**
     * 加载头像专用Options，默认加载中、失败和空url为 ic_loading_small
     *
     * @return
     */
    public static DisplayImageOptions getOptions4Header() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageForEmptyUri(R.mipmap.icon_default)
                .showImageOnFail(R.mipmap.icon_default)
                .showImageOnLoading(R.mipmap.icon_default)
                .build();
    }

    /**
     * 设置图片放缩类型为模式EXACTLY，用于图片详情页的缩放
     *
     * @return
     */
    public static DisplayImageOptions getOption4ExactlyType() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
    }

    /**
     * 加载图片列表专用，加载前会重置View
     * {@link com.nostra13.universalimageloader.core.DisplayImageOptions.Builder#resetViewBeforeLoading} = true
     *
     * @param loadingResource
     * @return
     */
    public static DisplayImageOptions getOptions4PictureList(int loadingResource) {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .showImageOnLoading(loadingResource)
                .showImageForEmptyUri(loadingResource)
                .showImageOnFail(loadingResource)
                .build();
    }

}
