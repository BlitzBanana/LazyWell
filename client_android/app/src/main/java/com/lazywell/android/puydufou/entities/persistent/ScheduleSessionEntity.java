package com.lazywell.android.puydufou.entities.persistent;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
public class ScheduleSessionEntity extends SugarRecord<ScheduleSessionEntity> {

    private ScheduleEntity schedule;
    private SessionEntity session;

    public ScheduleSessionEntity() {}

    public ScheduleSessionEntity(ScheduleEntity schedule, SessionEntity session){
        this.schedule = schedule;
        this.session = session;
    }

    public static List<ScheduleSessionEntity> getScheduleSession(ScheduleEntity schedule){
        return Select.from(ScheduleSessionEntity.class)
                .where(Condition.prop("schedule").eq(schedule.getId().toString()))
                .list();
    }

    public static List<ScheduleSessionEntity> getScheduleSession(SessionEntity session){
        return Select.from(ScheduleSessionEntity.class)
                .where(Condition.prop("session").eq(session.getId().toString()))
                .list();
    }

    public static List<ScheduleSessionEntity> getScheduleSession(ScheduleEntity schedule, SessionEntity session){
        List<ScheduleSessionEntity> scheduleSessions = new ArrayList<>();

        scheduleSessions.addAll(
                Select.from(ScheduleSessionEntity.class)
                .where(
                        Condition.prop("session").eq(session.getId().toString()),
                        Condition.prop("schedule").eq(schedule.getId().toString())
                )
                .list());
        return scheduleSessions;
    }

    public static void deleteBySchedule(ScheduleEntity schedule){
        deleteAll(ScheduleSessionEntity.class, "schedule = ?", schedule.getId().toString());
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }
}
