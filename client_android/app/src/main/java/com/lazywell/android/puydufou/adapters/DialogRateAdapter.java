package com.lazywell.android.puydufou.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.business.Rate;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;

import java.util.Date;

/**
 * Created by dherent on 17/06/2015.
 */
public class DialogRateAdapter extends BaseAdapter
{
    Context context;
    Rate[] rates;

    public DialogRateAdapter( Context context, Rate[] rates)
    {
        this.context = context;
        this.rates = rates;
    }

    public void UpdateRate(Rate[] rates)
    {
        this.rates = rates;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
                return rates.length;
    }

    @Override
    public Object getItem(int position)
    {
        return rates[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.dialog_rate_item, parent, false);

        Rate rate = (Rate) getItem(position);

        TextView showName = (TextView) rootView.findViewById(R.id.rate_view);
        showName.setText(rate.toString());

        return rootView;
    }
}
