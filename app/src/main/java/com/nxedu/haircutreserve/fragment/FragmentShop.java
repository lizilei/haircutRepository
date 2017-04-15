package com.nxedu.haircutreserve.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nxedu.haircutreserve.activity.MainActivity;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.adapter.CommonAdapter;
import com.nxedu.haircutreserve.adapter.ViewHolder;
import com.nxedu.haircutreserve.bean.OrderList;
import com.nxedu.haircutreserve.contacts.Contacts;
import com.nxedu.haircutreserve.net.KJHttpUtil;
import com.nxedu.haircutreserve.utils.UserUtils;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

import java.util.ArrayList;
import java.util.List;

/**商品订单
 * @author dupeng
 * @version 1.0.0
 * @since 2017/3/22 10:08
 */

public class FragmentShop  extends SupportFragment {
    @BindView(id = R.id.layout_empty)
    private RelativeLayout empty;
    @BindView(id = R.id.lv_shopping_cart)
    private ListView listView;
    private MainActivity aty;
    private View view;
    private List<OrderList.BodyBean> data = new ArrayList<>();
    private CommonAdapter<OrderList.BodyBean> adapter;
    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        aty = (MainActivity) getActivity();
        view = View.inflate(aty, R.layout.fragment_shop, null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        getOrderList(UserUtils.getTel(aty));
    }
    private void getOrderList(String phone) {
        KJHttpUtil.getHttp(Contacts.GET_SHOP_ORDER + phone, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);

                Log.i("---=-data", t);
                OrderList lists = JSON.parseObject(t, OrderList.class);
                if (lists.getBody()!=null){
                    data.addAll(lists.getBody());
                    empty.setVisibility(View.GONE);
                    adapter.getDatas(data);
                }else {
                    empty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                Log.i("error", strMsg);
            }
        });
    }
    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        adapter = new CommonAdapter<OrderList.BodyBean>(aty,R.layout.item_shop_order) {
            @Override
            public void convert(ViewHolder helper, OrderList.BodyBean item) {
                helper.setText(R.id.order_type, item.getBusiness_name());

                TextView order_state = helper.getView(R.id.order_state);

                if (item.getOrder_status() == 1) {
                    order_state.setSelected(true);
                    order_state.setText("待付款");
                }else {
                    order_state.setSelected(false);
                    order_state.setText("已付款");
                }
                helper.setText(R.id.order_time, item.getCreated());
                helper.setText(R.id.order_num, item.getOrder_id() + "");
                helper.setText(R.id.order_title, item.getProject_title());
                helper.setText(R.id.order_price, "￥" + item.getOrder_price());
                helper.setImageByUrl(R.id.order_cover, item.getCover_pic());
            }
        };
        listView.setAdapter(adapter);
    }
}
