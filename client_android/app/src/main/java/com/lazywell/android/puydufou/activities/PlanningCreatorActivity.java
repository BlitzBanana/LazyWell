package com.lazywell.android.puydufou.activities;

import android.content.DialogInterface;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.adapters.DialogSessionAdapter;
import com.lazywell.android.puydufou.adapters.DialogShowAdapter;
import com.lazywell.android.puydufou.entities.persistent.ScheduleEntity;
import com.lazywell.android.puydufou.entities.persistent.ScheduleSessionEntity;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;
import com.lazywell.android.puydufou.tools.EventUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PlanningCreatorActivity extends AppCompatActivity implements View.OnClickListener, WeekView.MonthChangeListener, WeekView.EventClickListener, WeekView.EventLongPressListener {

    private ScheduleEntity schedule;
    private WeekView weekView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_creator);
        weekView = (WeekView) findViewById(R.id.weekView);
        weekView.setOnEventClickListener(this);
        weekView.setEventLongPressListener(this);
        weekView.setMonthChangeListener(this);
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
        } else if (id == R.id.planning_creator_reset){
            showResetPopup();
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

    public void refreshWeekView(){
        loadEvents();
        weekView.notifyDatasetChanged();
    }

    public List<WeekViewEvent> loadEvents(){
        List<WeekViewEvent> weekViewEvents = new ArrayList<>();

        schedule = ScheduleEntity.findById(ScheduleEntity.class, 1l);

        if(schedule == null) {
            schedule = new ScheduleEntity();
            schedule.setName("Local");
            schedule.save();
        }

        for (SessionEntity sessionEntity : schedule.getSessionEntities()){
            Log.i("DB session", sessionEntity.getTime().toString());
            Log.i("DB session", sessionEntity.getShow().getName());
            weekViewEvents.add(EventUtils.sessionToEvent(this, sessionEntity));
        }

        return weekViewEvents;
    }

    public void showShowsPopup(){
        List<ShowEntity> showEntities = ShowEntity.listAll(ShowEntity.class);

        final DialogShowAdapter adapter = new DialogShowAdapter(this, showEntities);
        new MaterialDialog.Builder(this)
            .title(R.string.add_event_title)
            .adapter(adapter,
                    new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                            Toast.makeText(PlanningCreatorActivity.this, "Clicked item " + which, Toast.LENGTH_SHORT).show();
                            showSessionsPopup(adapter.getItemId(which));
                        }
                    })
            .show();
    }

    public void showSessionsPopup(long showId){
        final ShowEntity show = ShowEntity.findById(ShowEntity.class, showId);
        Log.i("POPUP", "Show ID: " + showId);
        List<SessionEntity> sessions = show.getSessionEntities();

        final DialogSessionAdapter adapter = new DialogSessionAdapter(this, sessions);
        new MaterialDialog.Builder(this)
                .title(R.string.add_event_title)
                .adapter(adapter,
                        new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                Toast.makeText(PlanningCreatorActivity.this, "Clicked item " + which, Toast.LENGTH_SHORT).show();
                                Log.i("POPUP", "Item selected:" + which);
                                long sessionId = adapter.getItemId(which);
                                SessionEntity session = SessionEntity.findById(SessionEntity.class, sessionId);
                                ScheduleSessionEntity scheduleSession = new ScheduleSessionEntity(schedule, session);
                                scheduleSession.save();
                                refreshWeekView();
                                dialog.dismiss();
                            }
                        })
                .show();
    }

    public void showResetPopup(){
        new AlertDialogWrapper.Builder(this)
                .setTitle(R.string.planning_creator_reset_title)
                .setMessage(R.string.planning_creator_reset_message)
                .setPositiveButton(R.string.planning_creator_reset_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ScheduleSessionEntity.deleteBySchedule(schedule);
                        refreshWeekView();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.planning_creator_reset_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
