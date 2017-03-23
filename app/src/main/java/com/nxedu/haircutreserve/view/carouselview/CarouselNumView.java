package com.nxedu.haircutreserve.view.carouselview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.nxedu.haircutreserve.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 右下角数字的
 */
public class CarouselNumView extends RelativeLayout implements ViewPager.OnPageChangeListener {

    private Context context;
    private int totalCount = 100;
    private int showCount;
    private int currentPosition = 0;
    private ViewPager viewPager;
    private TextView indexTv;
    private Adapter adapter;
    private boolean isUserTouched = false;
    private Timer mTimer = new Timer();

    //布局高度，单位px
    private int height = 400;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (currentPosition == totalCount - 1) {
                viewPager.setCurrentItem(showCount - 1, false);
            } else {
                viewPager.setCurrentItem(currentPosition);
            }
        }
    };
    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (!isUserTouched) {
                currentPosition = (currentPosition + 1) % totalCount;
                handler.sendEmptyMessage(100);
            }
        }
    };

    public CarouselNumView(Context context) {
        super(context);
        this.context = context;
        height = getMeasuredHeight();
    }

    public CarouselNumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        height = getMeasuredHeight();
    }

    public CarouselNumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        height = getMeasuredHeight();
    }

    public void cancelTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
        }
    }

    private void init() {
        viewPager.setAdapter(null);
//        carouselLayout.removeAllViews();
        if (adapter.isEmpty()) {
            return;
        }
        showCount = adapter.getCount();
        indexTv.setText("1/" + showCount);
        viewPager.setAdapter(new ViewPagerAdapter());
        viewPager.setCurrentItem(0);
        this.viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isUserTouched = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isUserTouched = false;
                        break;
                }
                return false;
            }
        });
        mTimer.schedule(mTimerTask, 3000, 3000);
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        if (adapter != null) {
            init();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(context).inflate(R.layout.carousel_num_layout, null);
        this.viewPager = (ViewPager) view.findViewById(R.id.gallery);
        this.indexTv = (TextView) view.findViewById(R.id.carousel_page_tv);
        this.viewPager.addOnPageChangeListener(this);
        addView(view);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("CarouselView", "onPageScrolled was invoke()");
    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
        int index = (position % showCount) + 1;
        indexTv.setText(index + "/" + showCount);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("CarouselView", "onPageScrollStateChanged was invoke()");
    }

    public interface Adapter {
        boolean isEmpty();

        View getView(int position);

        int getCount();
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return totalCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= showCount;
            View view = adapter.getView(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            int position = viewPager.getCurrentItem();
            if (position == 0) {
                position = showCount;
                viewPager.setCurrentItem(position, false);
            } else if (position == totalCount - 1) {
                position = showCount - 1;
                viewPager.setCurrentItem(position, false);
            }
        }
    }
}
