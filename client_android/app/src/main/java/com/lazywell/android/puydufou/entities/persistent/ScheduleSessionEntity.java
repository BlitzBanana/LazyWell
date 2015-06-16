package com.lazywell.android.puydufou.entities.persistent;

import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

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

    public static List<ScheduleSessionEntity> getScheduleSessionEntities(ScheduleEntity scheduleEntity){
        return Select.from(ScheduleSessionEntity.class)
                .where(Condition.prop("schedule").eq(scheduleEntity.getId().toString()))
                .list();
    }

    public static List<ScheduleSessionEntity> getScheduleSessionEntities(SessionEntity sessionEntity){
        return Select.from(ScheduleSessionEntity.class)
                .where(Condition.prop("session").eq(sessionEntity.getId().toString()))
                .list();
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
