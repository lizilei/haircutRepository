package com.nxedu.haircutreserve.activity;


import android.widget.ListView;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.adapter.CommonAdapter;
import com.nxedu.haircutreserve.adapter.ViewHolder;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;


public class GoodShopActivity extends BaseActivity {
    @BindView(id = R.id.lv_shoppush_list)
    private ListView goodShopListView;
    private CommonAdapter adapter;
    private List<String> data = new ArrayList<>();
    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_good_shop);
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i<=5;i++){
            data.add("asfa"+i);
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        adapter = new CommonAdapter(this,data,R.layout.item_goodshoppush_main) {
            @Override
            public void convert(ViewHolder helper, Object item) {

            }
        };
        goodShopListView.setAdapter(adapter);
    }
}