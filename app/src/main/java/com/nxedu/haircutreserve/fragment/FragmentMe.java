package com.nxedu.haircutreserve.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.activity.MainActivity;
import com.nxedu.haircutreserve.activity.StartActivity;
import com.nxedu.haircutreserve.adapter.CommonAdapter;
import com.nxedu.haircutreserve.adapter.ViewHolder;
import com.nxedu.haircutreserve.bean.MeData;
import com.nxedu.haircutreserve.utils.AppUtils;
import com.nxedu.haircutreserve.utils.ToastUtils;
import com.nxedu.haircutreserve.view.CircleImageView;
import com.nxedu.haircutreserve.view.DrawableTextView;
import com.nxedu.haircutreserve.view.ListViewNoScroll;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的
 *
 * @author dupeng
 * @version 1.0.0
 * @since 2017/3/22 10:08
 */

public class FragmentMe extends SupportFragment implements AdapterView.OnItemClickListener {

    private MainActivity aty;
    private View view;
    @BindView(id = R.id.fragment_me_exit, click = true)
    private Button exit;
    @BindView(id = R.id.lv_me)
    private ListViewNoScroll lv;
    private CommonAdapter<MeData> adapter;
    private String[] textData; //存储文字
    private TypedArray arr; //存储图片id
    private List<MeData> data = new ArrayList<>();//数据源
    @BindView(id = R.id.fragment_me_avatar, click = true)
    private CircleImageView meAvatar;
    @BindView(id = R.id.fragment_me_contact, click = true)
    private DrawableTextView meContact;
    @BindView(id = R.id.fragment_me_setting, click = true)
    private DrawableTextView meSetting;
    @BindView(id = R.id.fragment_me_msg, click = true)
    private DrawableTextView meMsg;
    @BindView(id = R.id.app_back_im)
    private ImageView iv_back;

    private String userType = "1";

    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        aty = (MainActivity) getActivity();
        view = View.inflate(aty, R.layout.fragment_me, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
    }

    @Override
    protected void initData() {
        super.initData();
        iv_back.setVisibility(View.GONE);
        switch (userType) {
            case "1"://普通用户
                textData = getResources().getStringArray(R.array.general_text);
                arr = getResources().obtainTypedArray(R.array.general_img);
                break;
        }

        MeData d;
        for (int i = 0, j = textData.length; i < j; i++) {
            d = new MeData();
            d.setText(textData[i]);
            d.setImgId(arr.getResourceId(i, 0));
            data.add(d);
        }
        arr.recycle();
        adapter = new CommonAdapter<MeData>(getActivity(), data, R.layout.lv_app_me_item) {
            @Override
            public void convert(ViewHolder helper, MeData item) {
                helper.setImageResource(R.id.iv_mine, item.getImgId());
                helper.setText(R.id.tv_mine, item.getText());
                if (helper.getPosition() == data.size() - 1 && item.getText().equals("版本号")) {
                    helper.getView(R.id.tv_mine_version).setVisibility(View.VISIBLE);
                    helper.setText(R.id.tv_mine_version, AppUtils.getVersion(getActivity()));
                } else {
                    helper.getView(R.id.tv_mine_version).setVisibility(View.GONE);
                }
            }
        };
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        adapter.getDatas(data);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        Intent intent = null;
        switch (v.getId()) {
            case R.id.fragment_me_exit://退出登录
                aty.finish();
                intent = new Intent(aty, StartActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_me_avatar://头像点击事件
                ToastUtils.showToast(aty, "头像");
                break;
            case R.id.fragment_me_contact://联系我们
                AppUtils.toTel(aty, "17301207022");
                return;
            case R.id.fragment_me_setting://系统设置
                ToastUtils.showToast(aty, "系统设置");
                break;
            case R.id.fragment_me_msg://系统消息
                ToastUtils.showToast(aty, "系统消息");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}