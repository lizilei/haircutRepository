package com.nxedu.haircutreserve.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.adapter.CommonAdapter;
import com.nxedu.haircutreserve.adapter.ViewHolder;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

import static com.nxedu.haircutreserve.R.id.lv_arrangstylist;

public class HaircutActivity extends BaseActivity {
    @BindView(id = lv_arrangstylist)
    private ListView arrangstylist;
    @BindView(id = R.id.iv_title_back,click = true)
    private CommonAdapter adapter;
    private List<String> data = new ArrayList<>();
    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_haircut);
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
        adapter = new CommonAdapter(this,data,R.layout.item_fast_cut) {
            @Override
            public void convert(ViewHolder helper, Object item) {
                helper.setImageByUrl(R.id.fast_stylist_head,"http://img.faxingzhan.com/170327/12-1F32F94301144_728_330.jpg");
            }
        };
        arrangstylist.setAdapter(adapter);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        finish();
    }
}