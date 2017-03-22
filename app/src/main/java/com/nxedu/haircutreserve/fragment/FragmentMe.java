package com.nxedu.haircutreserve.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nxedu.haircutreserve.MainActivity;
import com.nxedu.haircutreserve.R;

import org.kymjs.kjframe.ui.SupportFragment;

/**我的
 * @author dupeng
 * @version 1.0.0
 * @since 2017/3/22 10:08
 */

public class FragmentMe  extends SupportFragment {

    private MainActivity aty;
    private View view;
    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        aty = (MainActivity) getActivity();
        view = View.inflate(aty, R.layout.fragment_me, null);
        return view;
    }
}
