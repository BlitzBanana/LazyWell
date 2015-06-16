package com.lazywell.android.puydufou.entities.persistent;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by victor on 16/06/2015.
 */
public class SessionEntity  extends SugarRecord<SessionEntity> {

    private Date time;
    private ShowEntity show;

    public SessionEntity(){
    }

    public SessionEntity(Date time, ShowEntity show){
        this.time = time;
        this.show = show;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ShowEntity getShow() {
        return show;
    }

    public void setShow(ShowEntity show) {
        this.show = show;
    }
}
