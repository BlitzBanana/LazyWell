package com.lazywell.android.puydufou.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
public class DialogSessionAdapter extends BaseAdapter {

    Context context;
    List<SessionEntity> sessions;

    public DialogSessionAdapter(Context context, List<SessionEntity> sessions){
        this.context = context;
        this.sessions = sessions;
    }

    @Override
    public int getCount() {
        return sessions.size();
    }

    @Override
    public Object getItem(int position) {
        return sessions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return sessions.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.dialog_session_item, parent, false);

        SessionEntity session = (SessionEntity) getItem(position);
        Date date = session.getTime();
        TextView showName = (TextView) rootView.findViewById(R.id.dateView);
        showName.setText(date.getHours() + ":" + date.getMinutes());

        return rootView;
    }
}
