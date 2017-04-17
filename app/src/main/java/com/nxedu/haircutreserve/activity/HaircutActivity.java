package com.nxedu.haircutreserve.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.adapter.CommonAdapter;
import com.nxedu.haircutreserve.adapter.ViewHolder;
import com.nxedu.haircutreserve.bean.HaircutList;
import com.nxedu.haircutreserve.contacts.Contacts;
import com.nxedu.haircutreserve.net.KJHttpUtil;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

import static com.nxedu.haircutreserve.R.id.lv_arrangstylist;

public class HaircutActivity extends BaseActivity {
    @BindView(id = lv_arrangstylist)
    private ListView arrangstylist;
    @BindView(id = R.id.app_top_text)
    private TextView tv_top;
    @BindView(id = R.id.app_back_im, click = true)
    private CommonAdapter<HaircutList.BodyBean> adapter;
    private List<HaircutList.BodyBean> data = new ArrayList<>();

    BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_haircut);
        IntentFilter filter=new IntentFilter("com.haircut.order");
        registerReceiver(receiver,filter);
    }

    @Override
    public void initData() {
        super.initData();
        getHaircutList();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tv_top.setText("洗剪吹");
        tv_top.setVisibility(View.VISIBLE);
        adapter = new CommonAdapter<HaircutList.BodyBean>(this, R.layout.item_fast_cut) {
            @Override
            public void convert(ViewHolder helper, HaircutList.BodyBean item) {
                helper.setImageByUrl(R.id.fast_stylist_head,item.getImageurl());
                helper.setText(R.id.fast_item_name,item.getName());
                helper.setText(R.id.fast_salon_name,item.getShop_name());
                helper.setText(R.id.fast_salon_zone,item.getAddress());
                helper.setText(R.id.fast_far_me,item.getDistance());
                helper.setText(R.id.fast_choumei_price,item.getConcessionalprice());
                helper.setText(R.id.fast_Ori_price,item.getPrice());
            }
        };
        arrangstylist.setAdapter(adapter);
        arrangstylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HaircutActivity.this, ShopActivity.class);
                intent.putExtra("shop_id",data.get(position).getShop_id()+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        finish();
    }

    public void getHaircutList() {
        KJHttpUtil.getHttp(Contacts.GET_HAIRCUT_LIST, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                data.addAll(JSON.parseObject(t, HaircutList.class).getBody());
                adapter.getDatas(data);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}