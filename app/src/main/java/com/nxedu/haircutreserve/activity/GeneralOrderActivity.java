package com.nxedu.haircutreserve.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.adapter.CommonAdapter;
import com.nxedu.haircutreserve.adapter.ViewHolder;
import com.nxedu.haircutreserve.bean.IdCard;
import com.nxedu.haircutreserve.bean.OrderList;
import com.nxedu.haircutreserve.contacts.Contacts;
import com.nxedu.haircutreserve.net.KJHttpUtil;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.ui.BindView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>@description:订单列表</p>
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

        getOrderList("17301207022");

        adapter = new CommonAdapter<OrderList>(this, R.layout.order_mine_item) {
            @Override
            public void convert(ViewHolder helper, OrderList item) {
                helper.setText(R.id.order_type, item.getBusiness_name());

                TextView order_state = helper.getView(R.id.order_state);
                order_state.setText(item.getOrder_status());
                if (item.getOrder_status() == 1)
                    order_state.setSelected(true);
                else
                    order_state.setSelected(false);
                helper.setText(R.id.order_time, item.getCreated());
                helper.setText(R.id.order_num, item.getOrder_id() + "");
                helper.setText(R.id.order_title, item.getProject_title());
                helper.setText(R.id.order_price, "￥" + item.getOrder_price());
                helper.setImageByUrl(R.id.order_cover, item.getCover_pic());
            }
        };
        lv_order_mine.setAdapter(adapter);
        lv_order_mine.setEmptyView(tv_empty);
        adapter.getDatas(list);

        lv_order_mine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GeneralOrderActivity.this, GeneralOrderDetailActivity.class);
//                intent.putExtra("order_id", list.get(position).getOrder_id());
//                intent.putExtra("order_status", list.get(position).getOrder_status());
                intent.putExtra("order", list.get(position));
                startActivity(intent);
            }
        });
    }

    private void getOrderList(String phone) {
        KJHttpUtil.getHttp(Contacts.GET_ORDER_LIST + phone,new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);

                Log.i("data", t);
                list = JSON.parseArray(t, OrderList.class);
                adapter.getDatas(list);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);

                Log.i("error", strMsg);
            }
        });
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
