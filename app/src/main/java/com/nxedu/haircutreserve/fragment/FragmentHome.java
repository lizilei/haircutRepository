package com.nxedu.haircutreserve.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.activity.HomeInfoActivity;
import com.nxedu.haircutreserve.activity.MainActivity;
import com.nxedu.haircutreserve.adapter.HairCutHomeMultipleItemAdapter;
import com.nxedu.haircutreserve.bean.HeadBean;
import com.nxedu.haircutreserve.bean.OrderList;
import com.nxedu.haircutreserve.contacts.Contacts;
import com.nxedu.haircutreserve.net.KJHttpUtil;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 *
 * @author dupeng
 * @version 1.0.0
 * @since 2017/3/22 10:07
 */

public class FragmentHome extends SupportFragment implements SwipeRefreshLayout.OnRefreshListener {

    private MainActivity aty;
    private View view;
    @BindView(id = R.id.lay_refresh)
    private SwipeRefreshLayout lay_fresh;
    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;
    private HairCutHomeMultipleItemAdapter adapter;
    private List<HeadBean.BodyBean> hairStyle = new ArrayList<>();
    private List<HeadBean.BodyBean> headCarousel = new ArrayList<>();
    private List<HeadBean.BodyBean> hairStylist = new ArrayList<>();

    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        aty = (MainActivity) getActivity();
//        //透明状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = aty.getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        //透明状态栏
//        aty.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //透明导航栏
//        aty.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        view = View.inflate(aty, R.layout.fragment_home, null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        getHeadCarouesl();
        getHairStylist();
        getHairStyle();
    }
    private void getHeadCarouesl() {
        KJHttpUtil.getHttp(Contacts.GET_HEAD_CAROUSEL, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
//                Log.e("---hah",t);
                HeadBean headBean = JSON.parseObject(t,HeadBean.class);
                headCarousel.addAll(headBean.getBody());
                adapter.setDatas(headCarousel);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Log.i("error", strMsg);
            }
        });
    }
    private void getHairStyle() {
        KJHttpUtil.getHttp(Contacts.GET_HAIR_STYLE, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
//                Log.e("---hah",t);
                HeadBean headBean = JSON.parseObject(t,HeadBean.class);
                hairStyle.addAll(headBean.getBody());
                adapter.setDataStyle(hairStyle);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Log.i("error", strMsg);
            }
        });
    }
    private void getHairStylist() {
        KJHttpUtil.getHttp(Contacts.GET_HAIRSTYLIST, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
//                Log.e("---hah",t);
                HeadBean headBean = JSON.parseObject(t,HeadBean.class);
                hairStylist.addAll(headBean.getBody());
                adapter.setDataStylist(hairStylist);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Log.i("error", strMsg);
            }
        });
    }
    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
//        LoadingDialog loadingDialog = new LoadingDialog(aty);
//        loadingDialog.show();
        lay_fresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        lay_fresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(aty, 2, GridLayoutManager.VERTICAL, false));
        adapter = new HairCutHomeMultipleItemAdapter(aty);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new HairCutHomeMultipleItemAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(aty, HomeInfoActivity.class);
                intent.putExtra("url",hairStyle.get(position).getContenturl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lay_fresh.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }
}
