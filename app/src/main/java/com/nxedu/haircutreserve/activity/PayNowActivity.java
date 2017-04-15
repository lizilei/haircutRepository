package com.nxedu.haircutreserve.activity;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.bean.IdCard;
import com.nxedu.haircutreserve.bean.OrderList;
import com.nxedu.haircutreserve.bean.OrderList.BodyBean;
import com.nxedu.haircutreserve.utils.ConversionUtil;
import com.nxedu.haircutreserve.view.DrawableTextView;

import org.kymjs.kjframe.ui.BindView;

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
    @BindView(id = R.id.payment_detail_country_name_tv)
    private TextView payment_detail_country_name_tv;
    @BindView(id = R.id.payment_detail_mode_tv)
    private TextView payment_detail_mode_tv;
    @BindView(id = R.id.payment_detail_detail_tv, click = true)
    private TextView payment_detail_detail_tv;

    private BodyBean ol;
    private PopupWindow mPopupWindow;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_pay_now);
    }

    @Override
    public void initData() {
        super.initData();

        ol = (BodyBean) getIntent().getSerializableExtra("order");
    }

    @Override
    public void initWidget() {
        super.initWidget();

        tv_center.setText("立即付款");
        tv_center.setVisibility(View.VISIBLE);
        pay_wechat_img.setSelected(true);

        if (ol != null) {
            cost_num.setText(ol.getOrder_price());
            payment_detail_country_name_tv.setText(ol.getOrder_id()+"");
            payment_detail_mode_tv.setText(ol.getProject_title());
        }
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
            case R.id.payment_detail_detail_tv:
                showPopUpWindow();
                break;
        }
    }

    private void showPopUpWindow() {

        View view = LayoutInflater.from(this).inflate(R.layout.popup_payment_detail, null);

        DrawableTextView dtv = (DrawableTextView) view.findViewById(R.id.popup_payment_detail_up_dtv);


        TextView order_name = (TextView) view.findViewById(R.id.popup_payment_detail_title);
        TextView order_type = (TextView) view.findViewById(R.id.popup_payment_detail_home);
        //联系人
        TextView user_name = (TextView) view.findViewById(R.id.popup_payment_detail_contact_name);
        TextView user_phone = (TextView) view.findViewById(R.id.popup_payment_detail_contact_phone);
        //订单总价
        TextView order_total = (TextView) view.findViewById(R.id.popup_payment_detail_deposit);
        TextView order_num = (TextView) view.findViewById(R.id.popup_payment_detail_order_id);
        TextView order_create = (TextView) view.findViewById(R.id.popup_payment_detail_create_time);

        user_name.setText(ol.getUser_name());
        user_phone.setText(ol.getTel());

        order_name.setText(ol.getProject_title());
        order_type.setText(ol.getBusiness_name());
        order_total.setText("￥"+ol.getOrder_price());
        order_num.setText(ol.getOrder_id()+"");
        order_create.setText(ol.getCreated());

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
