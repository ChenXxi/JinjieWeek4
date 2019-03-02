package com.example.jinjieweek4.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jinjieweek4.R;
import com.example.jinjieweek4.bean.ShopBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    private Context context;
    private List<ShopBean.ResultBean> list;

    public ShopAdapter(Context context, List<ShopBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.shop_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.careName.setText(list.get(i).getCommodityName());
        viewHolder.carePrice.setText(list.get(i).getPrice());
        viewHolder.careShu.setText(list.get(i).getCount());
        viewHolder.careCb.setChecked(list.get(i).isCk());
        Glide.with(context).load(list.get(i).getPic());
        viewHolder.careJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemOnclicked!=null){
                    int i1 = list.get(i).getCount();
                    list.get(i).setCount(i1+1);
                    itemOnclicked.jia(i,AllPrice());
                }
            }
        });
        viewHolder.careJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemOnclicked!=null){
                    if (list.get(i).getCount()==0){
                        Toast.makeText(context,"数量最低为0",Toast.LENGTH_SHORT).show();
                    }else {
                        int i1 = list.get(i).getCount();
                        list.get(i).setCount(i1-1);
                        itemOnclicked.jian(i,AllPrice());
                    }
                }
            }
        });
        viewHolder.careCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemOnclicked!=null){
                    list.get(i).setCk(!list.get(i).isCk());
                    itemOnclicked.ck(i,AllPrice(),Allchecked());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.care_cb)
        CheckBox careCb;
        @BindView(R.id.care_img)
        ImageView careImg;
        @BindView(R.id.care_name)
        TextView careName;
        @BindView(R.id.care_price)
        TextView carePrice;
        @BindView(R.id.care_jian)
        ImageView careJian;
        @BindView(R.id.care_shu)
        TextView careShu;
        @BindView(R.id.care_jia)
        ImageView careJia;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public interface ItemOnclicked{
        void delete(int i,double price);
        void jia(int i,double price);
        void jian(int i,double price);
        void ck(int i,double price,boolean Allcheck);
    }

    private ItemOnclicked itemOnclicked;

    public void getInstance(ItemOnclicked itemOnclicked) {
        this.itemOnclicked = itemOnclicked;
    }

    /**
     * 得到所选中的总价格
     * @return
     */
    private double AllPrice(){
        double price = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCk()){
                price+=list.get(i).getPrice()*list.get(i).getCount();
            }
        }
        return price;
    }

    /**
     * 判断是否所有的子条目都选中
     * @return
     */
    private boolean Allchecked(){
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isCk()){
                return false;
            }
        }
        return true;
    }
}
