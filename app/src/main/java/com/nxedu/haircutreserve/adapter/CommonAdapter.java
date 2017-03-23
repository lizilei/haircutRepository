package com.nxedu.haircutreserve.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * <p>@description:公共的Adapter</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/23
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected final int mItemLayoutId;
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    private ViewHolder viewHolder;

    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
        this.notifyDataSetChanged();
    }

    public CommonAdapter(Context context, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mItemLayoutId = itemLayoutId;
    }

    public void getDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return mDatas != null ? mDatas.size() : 0;

    }

    @Override
    public T getItem(int position) {

        return mDatas.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConverView();

    }

    public abstract void convert(ViewHolder helper, T item);

    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }
}
