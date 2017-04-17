package com.nxedu.haircutreserve.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.adapter.CommonAdapter;
import com.nxedu.haircutreserve.adapter.ViewHolder;
import com.nxedu.haircutreserve.bean.GoodShopBean;
import com.nxedu.haircutreserve.bean.HaircutList;
import com.nxedu.haircutreserve.bean.OrderList;
import com.nxedu.haircutreserve.bean.ReturnMsg;
import com.nxedu.haircutreserve.contacts.Contacts;
import com.nxedu.haircutreserve.net.KJHttpUtil;
import com.nxedu.haircutreserve.utils.AppUtils;
import com.nxedu.haircutreserve.utils.MyImageLoaderUtils;
import com.nxedu.haircutreserve.utils.TimeUtils;
import com.nxedu.haircutreserve.utils.ToastUtils;
import com.nxedu.haircutreserve.utils.UserUtils;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends BaseActivity {

    @BindView(id = R.id.app_top_text)
    private TextView tv_title;
    @BindView(id = R.id.app_back_layout, click = true)
    private RelativeLayout relativeLayout;
    @BindView(id = R.id.layout_phone, click = true)
    private RelativeLayout relativeLayouts;
    @BindView(id = R.id.tv_booking_money_msg)
    private TextView money;
    @BindView(id = R.id.tv_booking, click = true)
    private TextView book;
    @BindView(id = R.id.ig_shop_img)
    private ImageView imBg;
    @BindView(id = R.id.tv_shop_address)
    private TextView address;
    @BindView(id = R.id.tv_shop_comment_num)
    private TextView comment_num;
    @BindView(id = R.id.tv_shop_satisfaction)
    private TextView satisfaction;
    @BindView(id = R.id.list_shop_item_type)
    private ListView lv;
    private String shop_id;
    private List<HaircutList.BodyBean> data = new ArrayList<>();
    private CommonAdapter<HaircutList.BodyBean> adapter;
    private String tel;
    private GoodShopBean goodShopBean;
    private int haircut_id;
    private String haircut_name;
    private String price;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_shop);
        shop_id = getIntent().getStringExtra("shop_id");
        Log.e("--shop", shop_id + "--");
    }

    @Override
    public void initData() {
        super.initData();
        getGoodShop();
        getHaircut();
    }

    private void getGoodShop() {
        String url = Contacts.GET_GOOD_SHOP + "?shop_id=" + shop_id;
        KJHttpUtil.getHttp(url, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                goodShopBean = JSON.parseObject(t, GoodShopBean.class);
                tv_title.setText(goodShopBean.getBody().get(0).getName());
                tv_title.setVisibility(View.VISIBLE);
                MyImageLoaderUtils.displayHeadIcon(goodShopBean.getBody().get(0).getImageurl(), imBg);
                address.setText(goodShopBean.getBody().get(0).getAddress());
                comment_num.setText(goodShopBean.getBody().get(0).getEvaluation_number());
                satisfaction.setText(goodShopBean.getBody().get(0).getSatisfaction());
                tel = goodShopBean.getBody().get(0).getTel();
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Log.i("error", strMsg);
            }
        });
    }

    private void getHaircut() {
        String url = Contacts.GET_HAIRCUT_LIST + "?shop_id=" + shop_id;
        KJHttpUtil.getHttp(url, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                data.addAll(JSON.parseObject(t, HaircutList.class).getBody());
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
        adapter = new CommonAdapter<HaircutList.BodyBean>(this, R.layout.item_fast_cut) {
            @Override
            public void convert(ViewHolder helper, HaircutList.BodyBean item) {
                helper.setImageByUrl(R.id.fast_stylist_head, item.getImageurl());
                helper.setText(R.id.fast_item_name, item.getName());
                helper.setText(R.id.fast_salon_name, item.getShop_name());
                helper.setText(R.id.fast_salon_zone, item.getAddress());
                helper.setText(R.id.fast_far_me, item.getDistance());
                helper.setText(R.id.fast_choumei_price, item.getConcessionalprice());
                helper.setText(R.id.fast_Ori_price, item.getPrice());
            }
        };
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                double money1 = Integer.parseInt(data.get(position).getConcessionalprice()) * 0.3;

                price = Math.round(money1) + "";
                money.setText("¥" + price);
                money.setTextColor(getResources().getColor(R.color.pink));
                haircut_id = data.get(position).getId();
                haircut_name = data.get(position).getName();
            }
        });
    }

    public void getOrderList() {
        OrderList.BodyBean bodyBean = new OrderList.BodyBean();
        bodyBean.setAddress(address.getText().toString());
        bodyBean.setBusiness_name("洗剪吹");
        bodyBean.setCreated(TimeUtils.getCurrentTime());
        bodyBean.setTel(UserUtils.getUser(this).getTel());
        bodyBean.setDistance(goodShopBean.getBody().get(0).getDistance());
        bodyBean.setHaircut_id(haircut_id);
        bodyBean.setHaircut_name(haircut_name);
        bodyBean.setOrder_price(price);
        bodyBean.setUser_name(UserUtils.getUser(this).getUsername());
        bodyBean.setProject_title(tv_title.getText().toString());
        bodyBean.setCover_pic(goodShopBean.getBody().get(0).getImageurl());
        Log.e("---ERROR", bodyBean.toString());
        Log.e("---ERROR", JSON.toJSONString(bodyBean));

        HttpParams params = new HttpParams();
        params.put("addInfo", JSON.toJSONString(bodyBean));

        KJHttpUtil.postHttp(Contacts.POST_ADDORDERLIST, params, false, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                ReturnMsg msg = JSON.parseObject(t, ReturnMsg.class);
                ToastUtils.showToast(ShopActivity.this, msg.getMsg());

                Intent intent = new Intent("com.haircut.order");
                sendBroadcast(intent);
                finish();
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);

                Log.e("---ERROR", strMsg);
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.app_back_layout:
                finish();
                break;
            case R.id.layout_phone:
                AppUtils.toTel(ShopActivity.this, tel);
                break;
            case R.id.tv_booking:
                String money1 = money.getText().toString().trim();
                if (!money1.equals("")) {
                    getOrderList();
                } else {
                    ToastUtils.showToast(ShopActivity.this, "请选择发型师!!");
                }
                break;
        }
    }
}
