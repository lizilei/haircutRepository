package com.nxedu.haircutreserve.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nxedu.haircutreserve.activity.GeneralCouponActivity;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.activity.GeneralCollectionActivity;
import com.nxedu.haircutreserve.activity.GeneralOrderActivity;
import com.nxedu.haircutreserve.activity.GeneralUserCenterSettingActivity;
import com.nxedu.haircutreserve.activity.MainActivity;
import com.nxedu.haircutreserve.activity.PersonalDetailsActivity;
import com.nxedu.haircutreserve.activity.StartActivity;
import com.nxedu.haircutreserve.adapter.CommonAdapter;
import com.nxedu.haircutreserve.adapter.ViewHolder;
import com.nxedu.haircutreserve.bean.MeData;
import com.nxedu.haircutreserve.bean.User;
import com.nxedu.haircutreserve.utils.AppUtils;
import com.nxedu.haircutreserve.utils.MyImageLoaderUtils;
import com.nxedu.haircutreserve.utils.PreferenceUtils;
import com.nxedu.haircutreserve.utils.ToastUtils;
import com.nxedu.haircutreserve.utils.UserUtils;
import com.nxedu.haircutreserve.view.CircleImageView;
import com.nxedu.haircutreserve.view.DrawableTextView;
import com.nxedu.haircutreserve.view.ListViewNoScroll;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;
import org.kymjs.kjframe.utils.FileUtils;

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
    @BindView(id = R.id.fragment_me_username)
    private TextView fragment_me_username;
    @BindView(id = R.id.fragment_me_commpany)
    private TextView fragment_me_commpany;

    private String userType = "1";

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String path = intent.getStringExtra("path");
            Bitmap bm = BitmapFactory.decodeFile(path);
            meAvatar.setImageBitmap(bm);
        }
    };

    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        aty = (MainActivity) getActivity();
        view = View.inflate(aty, R.layout.fragment_me, null);

        IntentFilter filter = new IntentFilter("com.change.avatar");
        aty.registerReceiver(receiver, filter);

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
        String ss = PreferenceUtils.getPrefString(aty, "avatarPath", null);
        if (ss != null) {
            meAvatar.setImageBitmap(BitmapFactory.decodeFile(ss));
        }

        fragment_me_username.setText(UserUtils.getTel(aty));
        fragment_me_commpany.setText(UserUtils.getUser(aty).getUsername() != null ? UserUtils.getUser(aty).getUsername() : "");
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
        switch (v.getId()) {
            case R.id.fragment_me_exit://退出登录
                PreferenceUtils.setPrefString(aty, "phone", null);
                aty.finish();
                startActivity(new Intent(aty, StartActivity.class));
                break;
            case R.id.fragment_me_avatar://头像点击事件
                startActivity(new Intent(aty, PersonalDetailsActivity.class));
                break;
            case R.id.fragment_me_contact://联系我们
                AppUtils.toTel(aty, "17301207022");
                return;
            case R.id.fragment_me_setting://系统设置
                startActivity(new Intent(aty, GeneralUserCenterSettingActivity.class));
                break;
            case R.id.fragment_me_msg://系统消息
                ToastUtils.showToast(aty, "系统消息");
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        switch (position) {
            case 0: //我的收藏
                intent = new Intent(aty, GeneralCollectionActivity.class);
                break;
            case 1: //我的订单
                intent = new Intent(aty, GeneralOrderActivity.class);
                break;
            case 2: //优惠券
                intent = new Intent(aty, GeneralCouponActivity.class);
                break;
            case 3: //去评分
                ToastUtils.showToast(aty, "该功能暂未开放");
                return;
            case 4: //版本号
                ToastUtils.showToast(aty, "当前版本：" + AppUtils.getVersion(aty));
                return;
        }
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        aty.unregisterReceiver(receiver);
    }
}