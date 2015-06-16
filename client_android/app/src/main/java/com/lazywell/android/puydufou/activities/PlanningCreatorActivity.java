package com.lazywell.android.puydufou.activities;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.persistent.CustomScheduleEntity;
import com.lazywell.android.puydufou.entities.persistent.GeneratedShowRepositoryService;
import com.lazywell.android.puydufou.entities.persistent.ScheduleShowEntity;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowRepository;
import com.lazywell.android.puydufou.entities.persistent.ShowRepositoryService;
import com.slimgears.slimrepo.android.core.SqliteOrmServiceProvider;
import com.slimgears.slimrepo.core.internal.interfaces.OrmServiceProvider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlanningCreatorActivity extends AppCompatActivity implements WeekView.MonthChangeListener, WeekView.EventClickListener, WeekView.EventLongPressListener {

    private WeekView mWeekView;
    private GeneratedShowRepositoryService repoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_creator);

        mWeekView = (WeekView) findViewById(R.id.weekView);
        mWeekView.setOnEventClickListener(this);
        mWeekView.setEventLongPressListener(this);

        OrmServiceProvider sqliteOrmProvider = new SqliteOrmServiceProvider(getApplicationContext());
        repoService = new GeneratedShowRepositoryService(sqliteOrmProvider);
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

        for(ShowEntity showEntity : getShows()){
            weekViewEvents.addAll(showToEvent(showEntity, getShowSessions(showEntity)));
        }

        return weekViewEvents;
    }

    public List<ShowEntity> getShows(){
        List<ShowEntity> showEntities = new ArrayList<>();
        try (ShowRepository repo = repoService.open()) {
            CustomScheduleEntity customSchedule = repo.customSchedules().query()
                    .where(CustomScheduleEntity.Id.eq(1))
                    .prepare()
                    .firstOrDefault();

            ScheduleShowEntity[] scheduleShowEntities = repo.schedulesShows().query()
                    .where(ScheduleShowEntity.Schedule.is(CustomScheduleEntity.Id.eq(customSchedule.getId())))
                    .prepare()
                    .toArray();

            for(ScheduleShowEntity scheduleShow : scheduleShowEntities){
                showEntities.add(scheduleShow.getShow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showEntities;
    }

    public SessionEntity[] getShowSessions(ShowEntity showEntity){
        SessionEntity[] sessionEntities = new SessionEntity[0];
        try (ShowRepository repo = repoService.open()) {
             sessionEntities = repo.sessions().query()
                     .where(SessionEntity.Show.is(ShowEntity.Id.eq(showEntity.getId())))
                     .prepare()
                     .toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionEntities;
    }

    public List<WeekViewEvent> showToEvent(ShowEntity showEntity, SessionEntity[] sessionEntities){
        List<WeekViewEvent> weekViewEvents = new ArrayList<>();
        for (SessionEntity sessionEntity : sessionEntities){
            Date now = new Date();
            Calendar startTime = Calendar.getInstance();
            startTime.set(Calendar.HOUR_OF_DAY, sessionEntity.getTime().getHours());
            startTime.set(Calendar.MINUTE, sessionEntity.getTime().getMinutes());
            startTime.set(Calendar.MONTH, now.getMonth());
            startTime.set(Calendar.YEAR, now.getYear());
            Calendar endTime = (Calendar) startTime.clone();
            endTime.add(Calendar.HOUR, 1);
            endTime.set(Calendar.MONTH, now.getMonth());
            WeekViewEvent event = new WeekViewEvent(1, showEntity.getName(), startTime, endTime);
            event.setColor(getResources().getColor(R.color.material_blue_grey_800));
            weekViewEvents.add(event);
        }
        return weekViewEvents;
    }
}
