package com.nxedu.haircutreserve.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.nxedu.haircutreserve.activity.HomeInfoActivity;
import com.nxedu.haircutreserve.activity.MainActivity;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.adapter.HairCutHomeMultipleItemAdapter;
import com.nxedu.haircutreserve.view.LoadingDialog;

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
    private List<String> imgList = new ArrayList<>();

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
        imgList.add("http://www.faxingzhan.com/uploads/170320/65_100823_1.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/161223/65-161223163T60-L_260_336.jpg");
        imgList.add("http://www.faxingzhan.com/uploads/170310/65_090929_1.jpg");
        imgList.add("http://img.faxingzhan.com/161214/65-161214150320438_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/160603/54-160603092U2E5_257_385.png");
        imgList.add("http://img.faxingzhan.com/161118/65-16111Q0242N14_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170324/65-1F3241011360-L_257_385.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/161116/65-1611161142380-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170322/65-1F322100P40-L_257_385.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/161116/65-1611161142380-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/150822/52_093750_1_lit_257_385.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170118/65-1F11Q606360-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/160527/12_105524_1_lit_257_385.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170116/65-1F1161604540-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170324/65-1F3240929190-L_320_420.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170209/65-1F20ZS6480-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170321/65-1F3210U3030-L_320_420.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170210/65-1F2101453350-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170317/65-1F31F924100-L_320_420.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170222/65-1F2221431070-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170320/65-1F3200941420-L_320_420.jpg");
        imgList.add("http://img.faxingzhan.com/170223/65-1F2230U40C59_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/170325/12-1F325164G1928_330_350.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170223/65-1F2230UP50-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170323/65-1F323144R80-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/170228/65-1F22Q0420a35_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170321/65-1F3211114270-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170307/65-1F30G40R50-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/170303/65-1F30315250H43_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170317/65-1F31G433400-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170302/65-1F3020SI50-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170315/65-1F3150943570-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170306/65-1F3061022150-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170306/65-1F3061522280-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170314/65-1F3141055230-L_260_336.jpg");
        imgList.add("http://img.faxingzhan.com/allimg/170314/65-1F3141055230-L_260_336.jpg");
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
//        LoadingDialog loadingDialog = new LoadingDialog(aty);
//        loadingDialog.show();
        lay_fresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        lay_fresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(aty, 2, GridLayoutManager.VERTICAL, false));
        adapter = new HairCutHomeMultipleItemAdapter(aty,imgList);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new HairCutHomeMultipleItemAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(aty, ""+position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(aty, HomeInfoActivity.class));
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
