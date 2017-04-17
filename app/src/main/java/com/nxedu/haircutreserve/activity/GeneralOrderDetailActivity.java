package com.nxedu.haircutreserve.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.bean.HaircutList;
import com.nxedu.haircutreserve.bean.OrderList;
import com.nxedu.haircutreserve.bean.OrderList.BodyBean;
import com.nxedu.haircutreserve.contacts.Contacts;
import com.nxedu.haircutreserve.net.KJHttpUtil;
import com.nxedu.haircutreserve.utils.AppUtils;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.ui.BindView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>@description:订单详情页</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/4/11
 */

public class GeneralOrderDetailActivity extends BaseActivity {

    @BindView(id = R.id.tv_name)
    private TextView tv_name;
    @BindView(id = R.id.tv_card)
    private TextView tv_card;
    @BindView(id = R.id.tv_phone)
    private TextView tv_phone;
    @BindView(id = R.id.tv_haircut_name)
    private TextView tv_haircut_name;
    @BindView(id = R.id.tv_haircut_money)
    private TextView tv_haircut_money;
    @BindView(id = R.id.tv_total)
    private TextView tv_total;
    @BindView(id = R.id.tv_order_money)
    private TextView tv_order_money;
    @BindView(id = R.id.tv_status)
    private TextView tv_status;
    @BindView(id = R.id.tv_totalCost)
    private TextView tv_totalCost;
    @BindView(id = R.id.tv_num)
    private TextView tv_num;
    @BindView(id = R.id.tv_time)
    private TextView tv_time;

    @BindView(id = R.id.tv_order, click = true)
    private TextView tv_order;
    @BindView(id = R.id.tv_call, click = true)
    private TextView tv_call;

    @BindView(id = R.id.app_top_text)
    private TextView tv_center;
    @BindView(id = R.id.app_back_im, click = true)

    private BodyBean ol;
    private HaircutList.BodyBean haircutBean;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_general_order_detail);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        if (ol != null) {
            tv_center.setText(ol.getProject_title());
            tv_center.setVisibility(View.VISIBLE);

            tv_name.setText(ol.getUser_name());
            tv_card.setText(ol.getTel());
            tv_phone.setText(ol.getTel());

            tv_total.setText("￥" + ol.getOrder_price());
            tv_order_money.setText("￥0");
            Log.e("---ol", ol.getOrder_status() + "--");
            tv_status.setText(ol.getOrder_status() == 1 ? "待付款" : "已付款");
            tv_totalCost.setText("￥" + ol.getOrder_price());
            tv_num.setText(ol.getOrder_id() + "");
            tv_time.setText(ol.getCreated() + "");


            if (ol.getOrder_status() == 1) {
                if (getIntent().hasExtra("pay")) {
                    tv_order.setVisibility(View.GONE);
                    tv_call.setVisibility(View.GONE);
                } else {
                    tv_order.setVisibility(View.VISIBLE);
                    tv_call.setVisibility(View.GONE);
                }
            } else {
                tv_order.setVisibility(View.GONE);
                tv_call.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void initData() {
        super.initData();

        ol = (BodyBean) getIntent().getSerializableExtra("order");

        getHairCutInfo(ol.getHaircut_id());
    }

    /**
     * 获取发型师信息
     *
     * @param haircut_id
     */
    public void getHairCutInfo(int haircut_id) {
        KJHttpUtil.getHttp(Contacts.GET_HAIRCUT_LIST + "?id=" + haircut_id, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);

                haircutBean = JSON.parseObject(t, HaircutList.class).getBody().get(0);

                tv_haircut_name.setText(haircutBean.getName());
                tv_haircut_money.setText("￥"+haircutBean.getConcessionalprice());
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
            case R.id.tv_order: //立即支付
                Intent intent = new Intent(this, PayNowActivity.class);
                List<BodyBean> list = new ArrayList<>();
                list.add(ol);
                intent.putExtra("order", (Serializable) list);
                startActivity(intent);
                break;
            case R.id.tv_call: //联系我们
                AppUtils.toTel(this, "17301207022");
                break;
        }
    }
}
