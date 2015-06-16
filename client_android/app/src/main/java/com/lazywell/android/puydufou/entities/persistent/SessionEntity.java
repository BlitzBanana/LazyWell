package com.lazywell.android.puydufou.entities.persistent;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by victor on 16/06/2015.
 */
public class SessionEntity  extends SugarRecord<SessionEntity> {

    protected Date time;
    protected ShowEntity showEntity;

    public SessionEntity(){
    }

    public SessionEntity(Date time){
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ShowEntity getShowEntity() {
        return showEntity;
    }

    public void setShowEntity(ShowEntity showEntity) {
        this.showEntity = showEntity;
    }
}
