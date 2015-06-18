package com.lazywell.android.puydufou.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.Restaurant;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by victor on 19/06/2015.
 */
public class RestaurantAdapter extends BaseAdapter {

    Context context;
    List<Restaurant> restaurants;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants){
        this.context = context;
        this.restaurants = restaurants;
    }

    @Override
    public int getCount() {
        return restaurants.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurants.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.dialog_resto_item, parent, false);

        Restaurant restaurant = (Restaurant) getItem(position);

        TextView restoName = (TextView) rootView.findViewById(R.id.resto_name);
        TextView restoDescription = (TextView) rootView.findViewById(R.id.resto_description);
        TextView restoMenu = (TextView) rootView.findViewById(R.id.resto_menu);
        TextView restoRate = (TextView) rootView.findViewById(R.id.resto_rate);

        restoName.setText(restaurant.getName());
        restoDescription.setText(restaurant.getDescription());
        restoMenu.setText(restaurant.getMenu());
        restoRate.setText(restaurant.getScore()+"/5");

        return rootView;
    }
}
