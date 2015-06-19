package com.lazywell.android.puydufou.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.business.Score;

/**
 * Created by dherent on 17/06/2015.
 */
public class DialogRateAdapter extends BaseAdapter
{
    Context context;
    Score[] scores;

    public DialogRateAdapter( Context context, Score[] scores)
    {
        this.context = context;
        this.scores = scores;
    }

    public void UpdateRate(Score[] scores)
    {
        this.scores = scores;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
                return scores.length;
    }

    @Override
    public Object getItem(int position)
    {
        return scores[position];
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

        Score score = (Score) getItem(position);

        TextView showName = (TextView) rootView.findViewById(R.id.rate_view);
        showName.setText(score.getText());

        return rootView;
    }
}
