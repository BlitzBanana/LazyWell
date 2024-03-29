package com.lazywell.android.puydufou.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
import com.lazywell.android.puydufou.tools.SessionEventSelector;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PlanningCreatorActivity extends AppCompatActivity implements View.OnClickListener,
        WeekView.MonthChangeListener, WeekView.EventClickListener, WeekView.EventLongPressListener,
        WeekView.EmptyViewClickListener {

    private ScheduleEntity schedule;
    private WeekView weekView;
    private SessionEventSelector eventSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        schedule = ScheduleEntity.findById(ScheduleEntity.class, 1l);
        eventSelector = new SessionEventSelector(this, schedule);
        setContentView(R.layout.activity_planning_creator);
        weekView = (WeekView) findViewById(R.id.weekView);
        weekView.setOnEventClickListener(this);
        weekView.setEventLongPressListener(this);
        weekView.setMonthChangeListener(this);
        weekView.setEmptyViewClickListener(this);
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
        if (id == R.id.planning_creator_reset){
            showResetPopup();
            return true;
        } else if (id == R.id.planning_creator_delete) {
            eventSelector.deleteSelected();
            switchMenu();
            weekView.notifyDatasetChanged();
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
        return loadEvents(newYear, newMonth);
    }

    @Override
    public void onEmptyViewClicked(Calendar calendar) {
        eventSelector.unselectAll();
        switchMenu();
        weekView.invalidate();
    }

    @Override
    public void onEventClick(WeekViewEvent weekViewEvent, RectF rectF) {
        Toast.makeText(this, "Clicked " + weekViewEvent.getId(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ShowDetailsActivity.class);
        SessionEntity session = SessionEntity.findById(SessionEntity.class, weekViewEvent.getId());
        Long showId = session.getShow().getId();
        intent.putExtra("showId", showId);
        startActivity(intent);
    }

    @Override
    public void onEventLongPress(WeekViewEvent weekViewEvent, RectF rectF) {
        if(!eventSelector.isSelected(weekViewEvent))
            eventSelector.select(weekViewEvent);
        else
            eventSelector.unselect(weekViewEvent);
        switchMenu();
        weekView.invalidate();
    }

    public void switchMenu(){
        if(eventSelector.isEmpty())
            showStandardMenu();
        else
            showDeleteMenu();
        ((TextView)findViewById(R.id.planning_creator_delete))
                .setText(getResources().getString(R.string.planning_creator_delete) + "(" + eventSelector.count() + ")");
        invalidateOptionsMenu();
    }

    public void showDeleteMenu(){
        findViewById(R.id.planning_creator_delete).setVisibility(View.VISIBLE);
        findViewById(R.id.planning_creator_reset).setVisibility(View.INVISIBLE);
        Log.d("MENU", "showDeleteMenu");
    }

    public void showStandardMenu(){
        findViewById(R.id.planning_creator_delete).setVisibility(View.INVISIBLE);
        findViewById(R.id.planning_creator_reset).setVisibility(View.VISIBLE);
        Log.d("MENU", "showStandardMenu");
    }

    public List<WeekViewEvent> loadEvents(int newYear, int newMonth){
        List<WeekViewEvent> weekViewEvents = new ArrayList<>();

        if(schedule == null) {
            schedule = new ScheduleEntity();
            schedule.setName("Local");
            schedule.save();
        }

        for (SessionEntity sessionEntity : schedule.getSessionEntities()) {
            Log.i("DB session", sessionEntity.getTime().toString());
            Log.i("DB session", sessionEntity.getShow().getName());
            weekViewEvents.add(EventUtils.schedulableToEvent(this, sessionEntity, newYear, newMonth));
        }
        Log.d("EVENTS", "Number of events: " + weekViewEvents.size());

        for(WeekViewEvent event1: weekViewEvents) {
            Log.d("EVENTS", "--------------------------------------------");
            Log.d("EVENTS", "Name: " + event1.getName());
            Log.d("EVENTS", "Start: " + event1.getStartTime().getTime().toString());
            Log.d("EVENTS", "End: " + event1.getEndTime().getTime().toString());
            Log.d("EVENTS", "--------------------------------------------");
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
                            dialog.dismiss();
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
                                weekView.notifyDatasetChanged();
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
                        weekView.notifyDatasetChanged();
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
