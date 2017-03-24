package com.nxedu.haircutreserve.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nxedu.haircutreserve.utils.MyImageLoaderUtils;

/**
 * <p>@description:Holder</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/23
 */

public class ViewHolder {
    private final SparseArray<View> mViews;
    private View mConverView;
    private int mPosition;
    private Context context;

    private ViewHolder(Context context, ViewGroup parent, int layoutID,
                       int position) {
        this.context = context;
        this.mViews = new SparseArray<>();
        mConverView = LayoutInflater.from(context).inflate(layoutID, parent,
                false);
        mPosition = position;
        // setTag
        mConverView.setTag(this);
    }

    // 拿到一个ViewHolder对象
    public static ViewHolder get(Context context, View converView,
                                 ViewGroup parent, int layoutID, int position) {
        ViewHolder myViewHolder = null;
        if (converView == null) {
            myViewHolder = new ViewHolder(context, parent, layoutID, position);
        } else {
            myViewHolder = (ViewHolder) converView.getTag();
        }
        return myViewHolder;
    }

    // 通过控件的Id获取对应的控件，如果没有则加入views

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConverView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConverView() {
        return mConverView;
    }

    // 为TextView设置字符串
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }
    // 为ExpandableTextView设置字符串

    public ViewHolder setExText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    // 为ImageView设置图片
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    // 为ImageView设置图片
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    // 为ImageView设置图片
    public ViewHolder setImageByUrl(int viewId, String url) {
//        Picasso.with(context).load(url).error(R.drawable.default_pic).into((ImageView) getView(viewId));
        MyImageLoaderUtils.displayHeadIcon(url,(ImageView) getView(viewId));
        return this;
    }

    // 为ImageView设置图片
    public ViewHolder setImageByUrl(int viewId, String url, int error) {
//        Picasso.with(context).load(url).error(error).into((ImageView) getView(viewId));
        return this;
    }

    // 为ImageView设置图片
    public ViewHolder setImageByUrl(int viewId, String url, int error, int place) {
//        Picasso.with(context).load(url).placeholder(place).error(error).into((ImageView) getView(viewId));
        return this;
    }

    public int getPosition() {
        return mPosition;
    }
}
