package com.lazywell.android.puydufou.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
public class DialogShowAdapter extends BaseAdapter {

    Context context;
    List<ShowEntity> showEntities;

    public DialogShowAdapter(Context context, List<ShowEntity> showEntities){
        this.context = context;
        this.showEntities = showEntities;
    }

    public void updateShows(List<ShowEntity> shows) {
        this.showEntities = shows;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return showEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return showEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return showEntities.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.dialog_show_item, parent, false);

        ShowEntity show = (ShowEntity) getItem(position);
        TextView showName = (TextView) rootView.findViewById(R.id.show_name);
        showName.setText(show.getName());

        return rootView;
    }
}
