package com.example.jinjieweek4.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinjieweek4.R;
import com.example.jinjieweek4.adapter.ShopAdapter;
import com.example.jinjieweek4.bean.Register;
import com.example.jinjieweek4.bean.ShopBean;
import com.example.jinjieweek4.presenter.PresenterImpl;
import com.example.jinjieweek4.utils.Api;
import com.example.jinjieweek4.utils.SpBase;
import com.example.jinjieweek4.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment implements IView, View.OnClickListener {


    @BindView(R.id.rv_shop)
    RecyclerView rvShop;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.shop_price)
    TextView shopPrice;
    @BindView(R.id.btn_shop_account)
    Button btnShopAccount;
    @BindView(R.id.car_lin)
    LinearLayout carLin;
    Unbinder unbinder;
    private PresenterImpl presenter;
    private ShopAdapter adapter;
    private List<ShopBean.ResultBean> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        presenter = new PresenterImpl(this);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> headermap = new HashMap<>();
        headermap.put("userId", Integer.parseInt(SpBase.getString(getContext(), "userId", "")));
        headermap.put("sessionId", SpBase.getString(getContext(), "sessionId", ""));
        presenter.get(Api.LookCart, headermap, map, ShopBean.class);
        rvShop.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShopAdapter(getContext(), list);
        rvShop.setAdapter(adapter);
        adapter.getInstance(new ShopAdapter.ItemOnclicked() {
            @Override
            public void delete(int i, double price) {

            }

            @Override
            public void jia(int i, double price) {
                shopPrice.setText(price+"");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void jian(int i, double price) {
                shopPrice.setText(price+"");
                adapter.notifyDataSetChanged();
            }

            @Override
            public void ck(int i, double price, boolean Allcheck) {
                list.get(i).setCk(list.get(i).isCk());
                cbAll.setChecked(Allcheck);
                shopPrice.setText(price+"");
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void success(Object success) {
        if (success instanceof ShopBean) {
            ShopBean bean = (ShopBean) success;
            if (bean.getStatus().equals("0000")) {
                Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                list.clear();
                list.addAll(bean.getResult());
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if(success instanceof Register){
            Register register = (Register) success;
            if(register.equals("0000")){
                Toast.makeText(getContext(), register.getMessage(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), register.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void error(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cb_all, R.id.btn_shop_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_all:
                break;
            case R.id.btn_shop_account:
                break;
        }
    }
}
