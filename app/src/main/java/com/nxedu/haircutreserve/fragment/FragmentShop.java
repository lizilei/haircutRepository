package com.nxedu.haircutreserve.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.nxedu.haircutreserve.activity.MainActivity;
import com.nxedu.haircutreserve.R;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

/**商品订单
 * @author dupeng
 * @version 1.0.0
 * @since 2017/3/22 10:08
 */

public class FragmentShop  extends SupportFragment {
    @BindView(id = R.id.layout_empty)
    private RelativeLayout empty;
    private MainActivity aty;
    private View view;
    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        aty = (MainActivity) getActivity();
        view = View.inflate(aty, R.layout.fragment_shop, null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        empty.setVisibility(View.VISIBLE);
    }
}
