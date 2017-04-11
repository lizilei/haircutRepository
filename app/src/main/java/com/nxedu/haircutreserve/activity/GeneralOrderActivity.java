package com.nxedu.haircutreserve.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.adapter.CommonAdapter;
import com.nxedu.haircutreserve.adapter.ViewHolder;
import com.nxedu.haircutreserve.bean.OrderList;

import org.kymjs.kjframe.ui.BindView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/4/11
 */

public class GeneralOrderActivity extends BaseActivity {

    @BindView(id = R.id.app_back_im, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.app_top_text)
    private TextView tv_center;
    @BindView(id = R.id.myText)
    private TextView tv_empty;
    @BindView(id = R.id.lv_order_mine)
    private ListView lv_order_mine;

    private CommonAdapter<OrderList> adapter;
    private List<OrderList> list = new ArrayList<>();

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_general_order);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tv_center.setText("我的订单");
        tv_center.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        super.initData();

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();

        for (int i = 0; i < 10; i++) {
            OrderList ol = new OrderList();
            ol.setOrder_id(format.format(now) + i);
            ol.setProject_id("1001" + i);
            ol.setProject_title("皇家理发" + i);
            ol.setCreated(format1.format(now));
            if (i % 2 == 0)
                ol.setOrder_status("未付款");
            else
                ol.setOrder_status("已付款");

            ol.setOrder_price((int) (50 * Math.random() + 50) + "");
            ol.setBusiness_id("发型设计");
            list.add(ol);
        }

        adapter = new CommonAdapter<OrderList>(this, R.layout.order_mine_item) {
            @Override
            public void convert(ViewHolder helper, OrderList item) {
                helper.setText(R.id.order_type, item.getBusiness_id());

                TextView order_state = helper.getView(R.id.order_state);
                order_state.setText(item.getOrder_status());
                if (item.getOrder_status().equals("未付款"))
                    order_state.setSelected(true);
                else
                    order_state.setSelected(false);
                helper.setText(R.id.order_time, item.getCreated());
                helper.setText(R.id.order_num, item.getOrder_id());
                helper.setText(R.id.order_title, item.getProject_title());
                helper.setText(R.id.order_price, item.getOrder_price() + "元");
            }
        };
        lv_order_mine.setAdapter(adapter);
        lv_order_mine.setEmptyView(tv_empty);
        adapter.getDatas(list);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.app_back_im:
                finish();
                break;
        }
    }
}
