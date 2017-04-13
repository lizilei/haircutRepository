package com.nxedu.haircutreserve.activity;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
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
    @BindView(id = R.id.layout_title_back,click = true)
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
        goodShopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(GoodShopActivity.this,ShopActivity.class));
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        finish();
    }
}
