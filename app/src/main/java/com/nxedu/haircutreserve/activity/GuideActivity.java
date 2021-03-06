package com.nxedu.haircutreserve.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.adapter.GuidViewPagerAdapter;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;


public class GuideActivity extends BaseActivity {
    @BindView(id = R.id.viewpager_guid)
    private ViewPager viewPager;
    @BindView(id = R.id.btn_experience)
    private ImageView btn_Experience;
    @BindView(id = R.id.ll_anchor_container)
    private LinearLayout ll_Anchor_Container;
    @BindView(id = R.id.iv_anchor1)
    private ImageView iv_Anchor1;
    @BindView(id = R.id.iv_anchor2)
    private ImageView iv_Anchor2;
    @BindView(id = R.id.iv_anchor3)
    private ImageView iv_Anchor3;
    private GuidViewPagerAdapter adapter;
    private List<View> images = new ArrayList<>();

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_guide);
    }

    @Override
    public void initData() {
        super.initData();
        View view_1 = getLayoutInflater().inflate(R.layout.view_guide_second,null);
        View view_2 = getLayoutInflater().inflate(R.layout.view_guide_third,null);
        View view_3 = getLayoutInflater().inflate(R.layout.view_guide_four,null);
        images.add(view_1);
        images.add(view_2);
        images.add(view_3);
        adapter = new GuidViewPagerAdapter(images);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    if (0 == position) {
                        images.get(position).setBackgroundResource(R.mipmap.guide_bg_1);
                        iv_Anchor1.setImageResource(R.mipmap.bg_shape_guide_solid_circle);
                        iv_Anchor2.setImageResource(R.mipmap.bg_shape_guide_hollow_circle);
                        iv_Anchor3.setImageResource(R.mipmap.bg_shape_guide_hollow_circle);
                        btn_Experience.setVisibility(View.GONE);
                    } else if (1 == position){
                        images.get(position).setBackgroundResource(R.mipmap.guide_bg_2);
                        iv_Anchor2.setImageResource(R.mipmap.bg_shape_guide_solid_circle);
                        iv_Anchor1.setImageResource(R.mipmap.bg_shape_guide_hollow_circle);
                        iv_Anchor3.setImageResource(R.mipmap.bg_shape_guide_hollow_circle);
                        btn_Experience.setVisibility(View.GONE);
                    }else if (2 == position){
                        images.get(position).setBackgroundResource(R.mipmap.guide_bg_3);
                        iv_Anchor3.setImageResource(R.mipmap.bg_shape_guide_solid_circle);
                        iv_Anchor2.setImageResource(R.mipmap.bg_shape_guide_hollow_circle);
                        iv_Anchor1.setImageResource(R.mipmap.bg_shape_guide_hollow_circle);
                        btn_Experience.setVisibility(View.VISIBLE);
                        btn_Experience.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(GuideActivity.this,StartActivity.class));
                                finish();
                            }
                        });
                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
