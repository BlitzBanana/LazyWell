package com.lazywell.android.puydufou.entities.persistent;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
public class ScheduleEntity extends SugarRecord<ScheduleEntity> {

    private String name;

    public ScheduleEntity(){}

    public ScheduleEntity(String name, List<SessionEntity> sessionEntities){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SessionEntity> getSessionEntities() {
        List<SessionEntity> sessionEntities = new ArrayList<>();
        List<ScheduleSessionEntity> scheduleSessionEntities = ScheduleSessionEntity.getScheduleSessionEntities(this);

        for(ScheduleSessionEntity scheduleSessionEntity : scheduleSessionEntities){
            sessionEntities.add(scheduleSessionEntity.getSession());
        }

        return sessionEntities;
    }
}
