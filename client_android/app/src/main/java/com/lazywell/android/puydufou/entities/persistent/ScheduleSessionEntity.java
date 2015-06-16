package com.lazywell.android.puydufou.entities.persistent;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
public class ScheduleSessionEntity extends SugarRecord<ScheduleSessionEntity> {

    private ScheduleEntity scheduleEntity;
    private SessionEntity sessionEntity;

    public ScheduleSessionEntity() {}

    public ScheduleSessionEntity(ScheduleEntity scheduleEntity, SessionEntity sessionEntity){
        this.scheduleEntity = scheduleEntity;
        this.sessionEntity = sessionEntity;
    }

    public static List<ScheduleSessionEntity> getScheduleSessionEntities(ScheduleEntity scheduleEntity){
        return ScheduleSessionEntity.find(ScheduleSessionEntity.class, "scheduleEntity = ?", scheduleEntity.getId().toString());
    }

    public static List<ScheduleSessionEntity> getScheduleSessionEntities(SessionEntity sessionEntity){
        return ScheduleSessionEntity.find(ScheduleSessionEntity.class, "sessionEntity = ?", sessionEntity.getId().toString());
    }

    public ScheduleEntity getScheduleEntity() {
        return scheduleEntity;
    }

    public void setScheduleEntity(ScheduleEntity scheduleEntity) {
        this.scheduleEntity = scheduleEntity;
    }

    public SessionEntity getSessionEntity() {
        return sessionEntity;
    }

    public void setSessionEntity(SessionEntity sessionEntity) {
        this.sessionEntity = sessionEntity;
    }
}
