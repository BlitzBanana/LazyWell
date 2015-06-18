package com.lazywell.android.puydufou.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.Restaurant;
import com.lazywell.android.puydufou.entities.Shop;

import java.util.List;

/**
 * Created by victor on 19/06/2015.
 */
public class ShopAdapter extends BaseAdapter {

    Context context;
    List<Shop> shops;

    public ShopAdapter(Context context, List<Shop> shops){
        this.context = context;
        this.shops = shops;
    }


    @Override
    public int getCount() {
        return shops.size();
    }

    @Override
    public Object getItem(int position) {
        return shops.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.dialog_resto_item, parent, false);

        Shop shop = (Shop) getItem(position);

        TextView shopName = (TextView) rootView.findViewById(R.id.shop_name);
        TextView shopDescription = (TextView) rootView.findViewById(R.id.shop_description);
        TextView shopRate = (TextView) rootView.findViewById(R.id.shop_rate);

        shopName.setText(shop.getName());
        shopDescription.setText(shop.getDescription());
        shopRate.setText(shop.getScore()+"/5");

        return rootView;
    }
}
