package com.nxedu.haircutreserve.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * <p>@description:继承ListView，重写onMeasure方法，防止和ScrollView造成的冲突</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/23
 */
public class ListViewNoScroll extends ListView {

    public ListViewNoScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewNoScroll(Context context) {
        super(context);
    }

    public ListViewNoScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
