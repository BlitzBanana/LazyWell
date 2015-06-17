package com.lazywell.android.puydufou.tools;

import android.content.Context;

import com.alamkanak.weekview.WeekViewEvent;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.persistent.ScheduleEntity;
import com.lazywell.android.puydufou.entities.persistent.ScheduleSessionEntity;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 17/06/2015.
 */
public class SessionEventSelector {

    Context context;
    List<WeekViewEvent> selectedEvents;
    ScheduleEntity schedule;

    public SessionEventSelector(Context context, ScheduleEntity schedule){
        this.context = context;
        this.schedule = schedule;
        this.selectedEvents = new ArrayList<>();
    }

    public void select(WeekViewEvent event){
        this.selectedEvents.add(event);
        event.setColor(context.getResources().getColor(R.color.darkgreen));
    }

    public void unselect(WeekViewEvent event){
        this.selectedEvents.remove(event);
        event.setColor(context.getResources().getColor(R.color.green));
    }

    public void unselectAll(){
        for(WeekViewEvent event : selectedEvents)
            event.setColor(context.getResources().getColor(R.color.green));
        this.selectedEvents.clear();
    }

    public void deleteSelected(){
        for (WeekViewEvent event : this.selectedEvents){
            SessionEntity session = SessionEntity.findById(SessionEntity.class, event.getId());
            List<ScheduleSessionEntity> scheduleSessions = ScheduleSessionEntity.getScheduleSession(schedule, session);

            for (ScheduleSessionEntity scheduleSession : scheduleSessions)
                scheduleSession.delete();
        }
    }

    public boolean isSelected(WeekViewEvent event){
        return this.selectedEvents.contains(event);
    }

    public boolean isEmpty(){
        return this.selectedEvents.isEmpty();
    }

    public int count(){
        return this.selectedEvents.size();
    }
}
