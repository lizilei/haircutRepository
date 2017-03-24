package com.nxedu.haircutreserve.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nxedu.haircutreserve.activity.MainActivity;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.adapter.HairCutHomeMultipleItemAdapter;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

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

    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        aty = (MainActivity) getActivity();
        view = View.inflate(aty, R.layout.fragment_home, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        lay_fresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        lay_fresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(aty, 2, GridLayoutManager.VERTICAL, false));
        adapter = new HairCutHomeMultipleItemAdapter(aty);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new HairCutHomeMultipleItemAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(aty, ""+position, Toast.LENGTH_SHORT).show();
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
