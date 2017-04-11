package com.nxedu.haircutreserve.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 郭君华 on 2017/3/29.
 * Email：guojunhua3369@163.com
 */

public class GuidViewPagerAdapter extends PagerAdapter {
    private List<View> data;

    public GuidViewPagerAdapter(List<View> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {

        return arg1 == arg0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(data.get(position));
        return data.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(data.get(position));
    }
}
