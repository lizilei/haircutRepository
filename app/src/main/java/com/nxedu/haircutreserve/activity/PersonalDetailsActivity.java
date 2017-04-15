package com.nxedu.haircutreserve.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nxedu.haircutreserve.R;
import com.nxedu.haircutreserve.bean.ReturnMsg;
import com.nxedu.haircutreserve.bean.User;
import com.nxedu.haircutreserve.contacts.Contacts;
import com.nxedu.haircutreserve.net.KJHttpUtil;
import com.nxedu.haircutreserve.utils.PreferenceUtils;
import com.nxedu.haircutreserve.utils.ToastUtils;
import com.nxedu.haircutreserve.utils.UserUtils;
import com.nxedu.haircutreserve.view.CircleImageView;
import com.nxedu.haircutreserve.view.DrawableTextView;

import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.FileUtils;

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

    @BindView(id = R.id.person_detail_nickname_tv)
    private DrawableTextView nickname;
    @BindView(id = R.id.person_detail_sex_tv)
    private DrawableTextView gender;
    @BindView(id = R.id.person_detail_phone_tv)
    private DrawableTextView phone;
    @BindView(id = R.id.person_detail_self_tv)
    private DrawableTextView userState;
    @BindView(id = R.id.person_detail_signature_tv)
    private DrawableTextView userSignature;

    private RelativeLayout rl_popup;
    private static final int TAKE_PICTURE = 0x000001;
    private static final int Choose_PICTURE = 0x000002;
    private User user;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_personal_details);
    }

    @Override
    public void initData() {
        super.initData();

        String ss = PreferenceUtils.getPrefString(this, "avatarPath", null);
        if (ss != null) {
            iv_avatar.setImageBitmap(BitmapFactory.decodeFile(ss));
        }

        user = UserUtils.getUser(this);

        nickname.setText(user.getNickname() == null ? "" : user.getNickname());
        phone.setText(user.getTel() == null ? "" : user.getTel());
        gender.setText(user.getGender() == null ? "" : user.getGender());
        userState.setText(user.getUserstate() == null ? "" : user.getUserstate());
        userSignature.setText(user.getUsersignature() == null ? "" : user.getUsersignature());
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
        switch (v.getId()) {
            case R.id.app_back_im:
                finish();
                return;
            case R.id.person_detail_avatar:
                showPopupWindows();
                break;
            case R.id.person_detail_nickname:
                showDialog("nickname", "昵称", nickname.getText().toString());
                break;
            case R.id.person_detail_sex:
                showGenderDialog("gender", gender.getText().toString());
                break;
            case R.id.person_detail_diff:
                showDialog("userstate", "个人信息", userState.getText().toString());
                break;
            case R.id.person_detail_signature:
                showDialog("usersignature", "我的签名", userSignature.getText().toString());
                break;
        }
    }

    /**
     * 修改性别展示对话框
     *
     * @param key
     * @param value
     */
    public void showGenderDialog(final String key, final String value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        final CheckBox cb1 = new CheckBox(this);
        final CheckBox cb2 = new CheckBox(this);
        cb1.setText("男");
        cb2.setText("女");

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    cb2.setChecked(false);
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    cb1.setChecked(false);
            }
        });

        if (value.equals("男")) {
            cb1.setChecked(true);
        } else {
            cb2.setChecked(true);
        }

        layout.addView(cb1);
        layout.addView(cb2);
        layout.setGravity(Gravity.CENTER);

        builder.setView(layout);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = cb1.isChecked() ? "男" : "女";

                if (!value.equals("")) {
                    updateUserInfo(key, value);
                    dialog.dismiss();
                    user.setGender(value);
                    gender.setText(value);

                    PreferenceUtils.setPrefString(PersonalDetailsActivity.this, "userInfo", JSON.toJSONString(user));
                } else {
                    dialog.dismiss();
                }
            }
        }).show();
    }


    /**
     * 对话框 输入内容调取修改方法
     *
     * @param key
     * @param hint
     */
    public void showDialog(final String key, String hint, String value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText et = new EditText(this);
        et.setHint("请输入" + hint);
        et.setText(value);
        et.setHintTextColor(getResources().getColor(R.color.commonly_text_color6));
        et.setPadding(10, 10, 10, 10);
        builder.setView(et);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = et.getText().toString().trim();
                if (!value.equals("")) {
                    updateUserInfo(key, value);
                    dialog.dismiss();
                    switch (key) {
                        case "nickname":
                            user.setNickname(value);
                            nickname.setText(value);
                            break;
                        case "userstate":
                            user.setUserstate(value);
                            userState.setText(value);
                            break;
                        case "usersignature":
                            user.setUsersignature(value);
                            userSignature.setText(value);
                            break;
                    }
                    PreferenceUtils.setPrefString(PersonalDetailsActivity.this, "userInfo", JSON.toJSONString(user));
                } else {
                    dialog.dismiss();
                }
            }
        }).show();
    }

    /**
     * 修改信息的方法
     *
     * @param key
     * @param value
     */
    public void updateUserInfo(final String key, String value) {
        String phone = UserUtils.getTel(this);

        KJHttpUtil.getHttp(Contacts.GET_UPDATE_USER + phone + "&" + key + "=" + value, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);

                ReturnMsg msg = JSON.parseObject(t, ReturnMsg.class);
                if (msg.getCode() == 0) {
                    ToastUtils.showToast(aty, msg.getMsg());
                } else if (msg.getCode() == 1) {
                    ToastUtils.showToast(aty, msg.getMsg());
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
            }
        });
    }


    /**
     * 选择更换方式
     */
    private void showPopupWindows() {
        final PopupWindow pop = new PopupWindow(this);
        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

        rl_popup = (RelativeLayout) view.findViewById(R.id.rl_popup);
        rl_popup.startAnimation(AnimationUtils.loadAnimation(this, R.anim.activity_translate_in));

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
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

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

        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Intent intent = new Intent("com.change.avatar");
            switch (requestCode) {
                case TAKE_PICTURE:
                    Bitmap bm = (Bitmap) data.getExtras().get("data");

                    String path = Contacts.PictureDir + System.currentTimeMillis() + ".jpg";
                    FileUtils.bitmapToFile(bm, path);
                    iv_avatar.setImageBitmap(bm);
                    PreferenceUtils.setPrefString(PersonalDetailsActivity.this, "avatarPath", path);
                    updateUserInfo("usericon", path);
                    intent.putExtra("path", path);
                    sendBroadcast(intent);
                    break;
                case Choose_PICTURE:
                    Uri selectedImage = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePathColumns[0]);
                    String imagePath = c.getString(columnIndex);
                    Bitmap bm1 = BitmapFactory.decodeFile(imagePath);
                    iv_avatar.setImageBitmap(bm1);
                    PreferenceUtils.setPrefString(PersonalDetailsActivity.this, "avatarPath", imagePath);
                    updateUserInfo("usericon", imagePath);
                    intent.putExtra("path", imagePath);
                    sendBroadcast(intent);
                    c.close();
                    break;
            }
        }
    }
}