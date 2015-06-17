package com.lazywell.android.puydufou.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

import java.util.Calendar;
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
        Log.i("SHOW", "Shown id: " + showEntities.get(position).getId() + "   position: " + position);
        return showEntities.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.dialog_show_item, parent, false);

        ShowEntity show = (ShowEntity) getItem(position);
        TextView showName = (TextView) rootView.findViewById(R.id.show_name_view);
        TextView descriptionView = (TextView) rootView.findViewById(R.id.description_view);
        TextView rateView = (TextView) rootView.findViewById(R.id.rate_view);
        TextView durationView = (TextView) rootView.findViewById(R.id.duration_view);

        Calendar duration = (Calendar)Calendar.getInstance().clone();
        duration.setTime(show.getDuration());

        showName.setText(show.getName());
        descriptionView.setText(show.getDescription());
        rateView.setText(show.getScore() + "/5");
        durationView.setText("(" + duration.get(Calendar.HOUR) + "h" + duration.get(Calendar.MINUTE) + ")");

        return rootView;
    }
}
