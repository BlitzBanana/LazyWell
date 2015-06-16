package com.lazywell.android.puydufou.activities;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.adapters.DialogSessionAdapter;
import com.lazywell.android.puydufou.adapters.DialogShowAdapter;
import com.lazywell.android.puydufou.entities.persistent.ScheduleEntity;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlanningCreatorActivity extends AppCompatActivity implements View.OnClickListener, WeekView.MonthChangeListener, WeekView.EventClickListener, WeekView.EventLongPressListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_creator);

        WeekView mWeekView = (WeekView) findViewById(R.id.weekView);
        mWeekView.setOnEventClickListener(this);
        mWeekView.setEventLongPressListener(this);
        mWeekView.setMonthChangeListener(this);
        findViewById(R.id.button_add).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_planning_creator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.planning_creator_recommended) {
            Toast.makeText(this, "Loading recommended planning...", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_add:
                showShowsPopup();
                break;
        }
    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        return loadEvents();
    }

    @Override
    public void onEventClick(WeekViewEvent weekViewEvent, RectF rectF) {
    }

    @Override
    public void onEventLongPress(WeekViewEvent weekViewEvent, RectF rectF) {

    }

    public List<WeekViewEvent> loadEvents(){
        List<WeekViewEvent> weekViewEvents = new ArrayList<>();

        ScheduleEntity schedule = ScheduleEntity.findById(ScheduleEntity.class, 1l);

        if(schedule == null) {
            schedule = new ScheduleEntity();
            schedule.setName("Local");
            schedule.save();
        }

        for (SessionEntity sessionEntity : schedule.getSessionEntities()){
            Log.i("DB session", sessionEntity.getTime().toString());
            Log.i("DB session", sessionEntity.getShow().getName());
            weekViewEvents.add(sessionToEvent(sessionEntity));
        }

        return weekViewEvents;
    }

    public WeekViewEvent sessionToEvent(SessionEntity sessionEntity){
        Date now = new Date();
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, sessionEntity.getTime().getHours());
        startTime.set(Calendar.MINUTE, sessionEntity.getTime().getMinutes());
        startTime.set(Calendar.MONTH, now.getMonth());
        startTime.set(Calendar.YEAR, now.getYear());
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, now.getMonth());
        WeekViewEvent event = new WeekViewEvent(1, sessionEntity.getShow().getName(), startTime, endTime);
        event.setColor(getResources().getColor(R.color.material_blue_grey_800));
        return event;
    }

    public void showShowsPopup(){
        List<ShowEntity> showEntities = ShowEntity.listAll(ShowEntity.class);

        new MaterialDialog.Builder(this)
            .title(R.string.add_event_title)
            .adapter(new DialogShowAdapter(this, showEntities),
                    new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                            Toast.makeText(PlanningCreatorActivity.this, "Clicked item " + which, Toast.LENGTH_SHORT).show();
                            showSessionsPopup(itemView.getId());
                        }
                    })
            .show();
    }

    public void showSessionsPopup(long showId){
        ShowEntity show = ShowEntity.findById(ShowEntity.class, showId);
        List<SessionEntity> sessions = show.getSessionEntities();

        new MaterialDialog.Builder(this)
                .title(R.string.add_event_title)
                .adapter(new DialogSessionAdapter(this, sessions),
                        new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                Toast.makeText(PlanningCreatorActivity.this, "Clicked item " + which, Toast.LENGTH_SHORT).show();
                            }
                        })
                .show();
    }
}
