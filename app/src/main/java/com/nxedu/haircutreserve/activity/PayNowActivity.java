package com.nxedu.haircutreserve.activity;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.adapter.CommonAdapter;
import com.nxedu.haircutreserve.adapter.ViewHolder;
import com.nxedu.haircutreserve.bean.HaircutList;

import com.nxedu.haircutreserve.bean.OrderList.BodyBean;
import com.nxedu.haircutreserve.contacts.Contacts;
import com.nxedu.haircutreserve.net.KJHttpUtil;
import com.nxedu.haircutreserve.view.DrawableTextView;
import com.nxedu.haircutreserve.view.ListViewNoScroll;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>@description:通用订单支付页面</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/4/11
 */

public class PayNowActivity extends BaseActivity {

    @BindView(id = R.id.app_top_text)
    private TextView tv_center;
    @BindView(id = R.id.app_back_im, click = true)
    private ImageView iv_back;

    @BindView(id = R.id.pay_wechat_img)
    private ImageView pay_wechat_img;
    @BindView(id = R.id.pay_alipay_img)
    private ImageView pay_alipay_img;
    @BindView(id = R.id.pay_wechat, click = true)
    private RelativeLayout pay_wechat;
    @BindView(id = R.id.pay_alipay, click = true)
    private RelativeLayout pay_alipay;

    @BindView(id = R.id.cost_num)
    private TextView cost_num;
    @BindView(id = R.id.lv_order)
    private ListViewNoScroll lv_order;
    private CommonAdapter<BodyBean> adapter;
    private List<BodyBean> data = new ArrayList<>();


    private PopupWindow mPopupWindow;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_pay_now);
    }

    @Override
    public void initData() {
        super.initData();

        data = (List<BodyBean>) getIntent().getSerializableExtra("order");
    }

    @Override
    public void initWidget() {
        super.initWidget();

        tv_center.setText("立即付款");
        tv_center.setVisibility(View.VISIBLE);
        pay_wechat_img.setSelected(true);

        int orderPrice = 0;
        for (BodyBean bean : data) {
            orderPrice += Integer.parseInt(bean.getOrder_price());
        }
        cost_num.setText(orderPrice + "");

        adapter = new CommonAdapter<BodyBean>(this, data, R.layout.item_lv_order_list) {
            @Override
            public void convert(final ViewHolder helper, BodyBean item) {
                helper.setText(R.id.payment_detail_country_name_tv, item.getOrder_id() + "");
                helper.setText(R.id.payment_detail_mode_tv, item.getProject_title());

                helper.getView(R.id.payment_detail_detail_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopUpWindow(helper.getPosition());
                    }
                });
            }
        };
        lv_order.setAdapter(adapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.app_back_im:
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                } else {
                    finish();
                }
                break;
            case R.id.pay_wechat: //微信支付
                pay_wechat_img.setSelected(true);
                pay_alipay_img.setSelected(false);
                break;
            case R.id.pay_alipay: //支付宝支付
                pay_wechat_img.setSelected(false);
                pay_alipay_img.setSelected(true);
                break;
        }
    }

    private void showPopUpWindow(int position) {


        View view = LayoutInflater.from(this).inflate(R.layout.popup_payment_detail, null);

        DrawableTextView dtv = (DrawableTextView) view.findViewById(R.id.popup_payment_detail_up_dtv);


        TextView order_name = (TextView) view.findViewById(R.id.popup_payment_detail_title);
        //联系人
        TextView user_name = (TextView) view.findViewById(R.id.popup_payment_detail_contact_name);
        TextView user_phone = (TextView) view.findViewById(R.id.popup_payment_detail_contact_phone);
        //订单总价
        TextView order_total = (TextView) view.findViewById(R.id.popup_payment_detail_deposit);
        TextView order_num = (TextView) view.findViewById(R.id.popup_payment_detail_order_id);
        TextView order_create = (TextView) view.findViewById(R.id.popup_payment_detail_create_time);
        //发型师
        final TextView haircut_name = (TextView) view.findViewById(R.id.popup_haircut_name);
        final TextView haircut_money = (TextView) view.findViewById(R.id.popup_haircut_money);


        KJHttpUtil.getHttp(Contacts.GET_HAIRCUT_LIST + "?id=" + data.get(position).getHaircut_id(), new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                HaircutList.BodyBean haircutBean = JSON.parseObject(t, HaircutList.class).getBody().get(0);
                Log.e("---pay",t);
                haircut_name.setText(haircutBean.getName());
                haircut_money.setText(haircutBean.getConcessionalprice());
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);

                Log.i("error", strMsg);
            }
        });

        BodyBean bean = data.get(position);

        user_name.setText(bean.getUser_name());
        user_phone.setText(bean.getTel());

        order_name.setText(bean.getProject_title());
        order_total.setText("￥" + bean.getOrder_price());
        order_num.setText(bean.getOrder_id() + "");
        order_create.setText(bean.getCreated());

        dtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        int height = wm.getDefaultDisplay().getHeight();
//        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, (height - ConversionUtil.dip2px(this, 60)));
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mPopupWindow.showAsDropDown(iv_back);
    }
}
