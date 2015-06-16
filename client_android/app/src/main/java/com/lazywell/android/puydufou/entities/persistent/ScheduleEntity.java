package com.lazywell.android.puydufou.entities.persistent;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
public class ScheduleEntity extends SugarRecord<ScheduleEntity> {

    protected String name;
    protected List<SessionEntity> sessionEntities;

    public ScheduleEntity(){
        this.sessionEntities = new ArrayList<>();
    }

    public ScheduleEntity(String name, List<SessionEntity> sessionEntities){
        this.name = name;
        this.sessionEntities = sessionEntities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SessionEntity> getSessionEntities() {
        return sessionEntities;
    }

    public void setSessionEntities(List<SessionEntity> sessionEntities) {
        this.sessionEntities = sessionEntities;
    }
}
