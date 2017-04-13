package com.nxedu.haircutreserve.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.utils.ToastUtils;
import com.nxedu.haircutreserve.view.CircleImageView;

import org.kymjs.kjframe.ui.BindView;

/**
 * <p>@description:个人中心-用户信息设置</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/3/24
 */

public class PersonalDetailsActivity extends BaseActivity {

    @BindView(id = R.id.person_detail_avatar, click = true)
    private RelativeLayout person_detail_avatar;
    @BindView(id = R.id.person_detail_nickname, click = true)
    private LinearLayout person_detail_nickname;
    @BindView(id = R.id.person_detail_sex, click = true)
    private LinearLayout person_detail_sex;
    @BindView(id = R.id.person_detail_phone, click = true)
    private LinearLayout person_detail_phone;
    @BindView(id = R.id.person_detail_diff, click = true)
    private LinearLayout person_detail_diff;
    @BindView(id = R.id.person_detail_signature, click = true)
    private LinearLayout person_detail_signature;
    @BindView(id = R.id.app_back_im, click = true)
    private ImageView iv_back;
    @BindView(id = R.id.app_top_text)
    private TextView tv_center;
    @BindView(id = R.id.person_detail_avatar_img)
    private CircleImageView iv_avatar;

    private RelativeLayout rl_popup;
    private static final int TAKE_PICTURE = 0x000001;
    private static final int Choose_PICTURE = 0x000002;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_personal_details);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        tv_center.setText("个人中心");
        tv_center.setVisibility(View.VISIBLE);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        String msg = null;
        switch (v.getId()) {
            case R.id.app_back_im:
                finish();
                return;
            case R.id.person_detail_avatar:
                rl_popup.startAnimation(AnimationUtils.loadAnimation(this, R.anim.activity_translate_in));
                showPopupWindows();
                break;
            case R.id.person_detail_nickname:
                msg = "换昵称啦";
                break;
            case R.id.person_detail_sex:
                msg = "换性别啦";
                break;
            case R.id.person_detail_phone:
                msg = "换电话号啦";
                break;
            case R.id.person_detail_diff:
                msg = "换个人信息啦";
                break;
            case R.id.person_detail_signature:
                msg = "换个性签名啦";
                break;
        }
        ToastUtils.showToast(this, msg);
    }

    /**
     * 选择更换方式
     */
    private void showPopupWindows() {
        final PopupWindow pop = new PopupWindow(this);
        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        rl_popup = (RelativeLayout) view.findViewById(R.id.rl_popup);

        pop.setWidth(LayoutParams.MATCH_PARENT);
        pop.setHeight(LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        TextView tv_photo = (TextView) view
                .findViewById(R.id.tv_photo);
        TextView tv_phone = (TextView) view
                .findViewById(R.id.tv_phone);
        TextView tv_cancel = (TextView) view
                .findViewById(R.id.tv_cancel);

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                rl_popup.clearAnimation();
            }
        });
        tv_photo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(openCameraIntent, TAKE_PICTURE);
                pop.dismiss();
                rl_popup.clearAnimation();
            }
        });

        tv_phone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");


                startActivityForResult(intent, Choose_PICTURE);
                overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                pop.dismiss();
                rl_popup.clearAnimation();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                rl_popup.clearAnimation();
            }
        });
    }
}