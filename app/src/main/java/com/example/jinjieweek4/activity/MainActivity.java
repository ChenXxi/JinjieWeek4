package com.example.jinjieweek4.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jinjieweek4.R;
import com.example.jinjieweek4.fragment.OrderFragment;
import com.example.jinjieweek4.fragment.ShopFragment;
import com.example.jinjieweek4.fragment.ShowFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.main_rb_show)
    RadioButton mainRbShow;
    @BindView(R.id.main_rb_circle)
    RadioButton mainRbCircle;
    @BindView(R.id.main_rb_shop)
    RadioButton mainRbShop;
    @BindView(R.id.main_rb_order)
    RadioButton mainRbOrder;
    @BindView(R.id.main_rb_my)
    RadioButton mainRbMy;
    @BindView(R.id.main_rg)
    RadioGroup mainRg;
    private FragmentManager manager;
    private OrderFragment orderFragment;
    private ShopFragment shopFragment;
    private ShowFragment showFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        initData();
        manager.beginTransaction().replace(R.id.frame,showFragment).commit();
        setOnclickListener();
    }

    private void setOnclickListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_rb_show:
                        manager.beginTransaction().replace(R.id.frame,showFragment).commit();
                        break;
                    case R.id.main_rb_circle:
                        manager.beginTransaction().replace(R.id.frame,showFragment).commit();
                        break;
                    case R.id.main_rb_shop:
                        manager.beginTransaction().replace(R.id.frame,shopFragment).commit();
                        break;
                    case R.id.main_rb_order:
                        manager.beginTransaction().replace(R.id.frame,orderFragment).commit();
                        break;
                    case R.id.main_rb_my:
                        manager.beginTransaction().replace(R.id.frame,showFragment).commit();
                        break;
                }
            }
        });
    }

    private void initData() {
        showFragment = new ShowFragment();
        shopFragment = new ShopFragment();
        orderFragment = new OrderFragment();
    }
}
