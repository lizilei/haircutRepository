package com.nxedu.haircutreserve;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;

import com.nxedu.haircutreserve.fragment.FragmentHome;
import com.nxedu.haircutreserve.fragment.FragmentMe;
import com.nxedu.haircutreserve.fragment.FragmentShop;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.SupportFragment;

public class MainActivity extends BaseActivity {

    private SupportFragment fragmentHome;
    private SupportFragment fragmentShop;
    private SupportFragment fragmentMe;
    private Context context;
    public View include;

    @BindView(id = R.id.rb_home, click = true)
    private RadioButton mRbHome;
    @BindView(id = R.id.rb_shop, click = true)
    private RadioButton mRbShop;
    @BindView(id = R.id.rb_me, click = true)
    private RadioButton mRbMe;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_main);
        this.context = this;
    }

    @Override
    public void initData() {
        super.initData();
        //TODO 判断身份
        //1.顾客身份
        fragmentHome = new FragmentHome();
        fragmentShop = new FragmentShop();
        fragmentMe = new FragmentMe();

        include = findViewById(R.id.menu);
        changeFragment(fragmentHome);//显示默认页面
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        //选择碎片选项卡页面
        SwitchFragmentTab(v.getId());
    }

    /**
     * 选择碎片选项卡页面
     */
    private void SwitchFragmentTab(int page) {

        switch (page) {
            case R.id.rb_home:
                mRbHome.setChecked(true);
                mRbShop.setChecked(false);
                mRbMe.setChecked(false);
                changeFragment(fragmentHome);
                break;
            case R.id.rb_shop:
//                startActivity(new Intent(FragmentMainActivity.this, TeacherAssessmentActivity.class));
                mRbHome.setChecked(false);
                mRbShop.setChecked(true);
                mRbMe.setChecked(false);
                changeFragment(fragmentShop);
                break;
            case R.id.rb_me:
                mRbHome.setChecked(false);
                mRbShop.setChecked(false);
                mRbMe.setChecked(true);
                changeFragment(fragmentMe);
                break;

            default:
                break;
        }
    }

    public void changeFragment(SupportFragment targetFragment) {
        super.changeFragment(R.id.fl_content, targetFragment);
    }
}
