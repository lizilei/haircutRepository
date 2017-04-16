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
import com.nxedu.haircutreserve.bean.GoodShopBean;
import com.nxedu.haircutreserve.bean.HeadBean;
import com.nxedu.haircutreserve.contacts.Contacts;
import com.nxedu.haircutreserve.net.KJHttpUtil;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;


public class GoodShopActivity extends BaseActivity {
    @BindView(id = R.id.lv_shoppush_list)
    private ListView goodShopListView;

    @BindView(id = R.id.app_top_text)
    private TextView tv_top;
    @BindView(id = R.id.app_back_im,click = true)


    private CommonAdapter<GoodShopBean.BodyBean> adapter;
    private List<GoodShopBean.BodyBean> data = new ArrayList<>();
    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_good_shop);
    }

    @Override
    public void initData() {
        super.initData();
        getGoodShop();
    }
    private void getGoodShop() {
        KJHttpUtil.getHttp(Contacts.GET_GOOD_SHOP, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                GoodShopBean goodShopBean= JSON.parseObject(t,GoodShopBean.class);
                data.addAll(goodShopBean.getBody());
                adapter.getDatas(data);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Log.i("error", strMsg);
            }
        });
    }
    @Override
    public void initWidget() {
        super.initWidget();
        tv_top.setText("身边好店");
        tv_top.setVisibility(View.VISIBLE);

        adapter = new CommonAdapter<GoodShopBean.BodyBean>(this,data,R.layout.item_goodshoppush_main) {
            @Override
            public void convert(ViewHolder helper, GoodShopBean.BodyBean item) {
                helper.setText(R.id.tv_list_salonname,item.getName());
                helper.setText(R.id.tv_list_leave_lately,item.getDistance());
                helper.setText(R.id.tv_list_localname,item.getAddress());
                helper.setText(R.id.tv_list_saynum_num,item.getEvaluation_number());
                helper.setText(R.id.tv_manyishu,item.getSatisfactory_number());
                helper.setText(R.id.tv_manyidu,item.getSatisfaction());
                helper.setImageByUrl(R.id.iv_list_bigimage,item.getImageurl());
            }
        };
        goodShopListView.setAdapter(adapter);
        goodShopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GoodShopActivity.this,ShopActivity.class);
                intent.putExtra("shop_id",data.get(position).getId()+"");
                Log.e("--shop",data.get(position).getId()+"--");
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        finish();
    }
}
