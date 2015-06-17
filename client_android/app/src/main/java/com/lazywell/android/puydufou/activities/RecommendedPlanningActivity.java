package com.lazywell.android.puydufou.activities;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.persistent.ScheduleEntity;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.tools.EventUtils;
import com.lazywell.android.puydufou.webservices.ShowClient;

import java.util.ArrayList;
import java.util.List;

public class RecommendedPlanningActivity extends AppCompatActivity implements  WeekView.MonthChangeListener,
        WeekView.EventClickListener, WeekView.EventLongPressListener {

    private WeekView mWeekView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_planning);

        mWeekView = (WeekView) findViewById(R.id.weekView);
        mWeekView.setOnEventClickListener(this);
        mWeekView.setMonthChangeListener(this);
        mWeekView.setEventLongPressListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recommended_planning, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onEventClick(WeekViewEvent weekViewEvent, RectF rectF) {
        Intent intent = new Intent(RecommendedPlanningActivity.this, ShowDetailsActivity.class);
        SessionEntity session = SessionEntity.findById(SessionEntity.class, weekViewEvent.getId());
        Long showId = session.getShow().getId();
        intent.putExtra("showId", showId);
        startActivity(intent);
    }

    @Override
    public void onEventLongPress(WeekViewEvent weekViewEvent, RectF rectF) {

    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<>();
        List<SessionEntity> sessions = new ShowClient().getBestPlanning();

        for (SessionEntity session : sessions)
            events.add(EventUtils.schedulableToEvent(this, session, newYear, newMonth));

        return events;
    }
}
