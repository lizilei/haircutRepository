package com.nxedu.haircutreserve.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.nxedu.haircutreserve.R;

/**
 * <p>@description:可控制图片大小的TextView</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/23
 */
public class DrawableTextView extends TextView {

    // 图片资源的宽度。默认为25dp
    private int mDrawableWidth;

    // 图片资源的高度。默认为25dp
    private int mDrawableHeight;

    private Drawable[] drawables;

    private Drawable left, top, right, bottom;

	/*
     * Drawable[] d=getCompoundDrawables();通过xml设置的Drawable资源的数组下标与方位的对照
	 *
	 * 0 -----》left 1 -----》top 2 -----》right 3 -----》bottom
	 */

    public DrawableTextView(Context context) {
        super(context);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getDrawableSize(context, attrs);
    }

    public DrawableTextView(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getDrawableSize(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        drawables = getCompoundDrawables();

        left = drawables[0];

        top = drawables[1];

        right = drawables[2];

        bottom = drawables[3];

        if (left != null) {
            left.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        } else if (top != null) {
            top.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        } else if (right != null) {
            right.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        } else if (bottom != null) {
            bottom.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }

        setCompoundDrawables(left, top, right, bottom);

        super.onDraw(canvas);
    }

    /**
     * 获取自定义属性的值
     *
     * @param context
     * @param attrs
     */
    private void getDrawableSize(Context context, AttributeSet attrs) {

        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.DrawableTextView);
        // 获取布局中设置的数值。如布局中未设置，则默认为25
        mDrawableWidth = ta.getDimensionPixelOffset(
                R.styleable.DrawableTextView_drawable_width, 25);
        // 获取布局中设置的数值。如布局中未设置，则默认为25
        mDrawableHeight = ta.getDimensionPixelOffset(
                R.styleable.DrawableTextView_drawable_height, 25);

        ta.recycle();
    }
}
