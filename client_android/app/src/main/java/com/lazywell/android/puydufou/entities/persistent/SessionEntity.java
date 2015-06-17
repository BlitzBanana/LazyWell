package com.lazywell.android.puydufou.entities.persistent;

import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by victor on 16/06/2015.
 */
public class SessionEntity  extends SugarRecord<SessionEntity> {

    private Date time;
    private ShowEntity show;

    public SessionEntity(){
    }

    public SessionEntity(Date time, ShowEntity show){
        this.time = dateToToday(time);
        this.show = show;
    }

    public Date getTime() {
        return dateToToday(time);
    }

    public void setTime(Date time) {
        this.time = dateToToday(time);
    }

    public ShowEntity getShow() {
        return show;
    }

    public void setShow(ShowEntity show) {
        this.show = show;
    }

    public static Date dateToToday(Date date){
        Calendar calendar = Calendar.getInstance();
        Calendar showCalendar = (Calendar) calendar.clone();
        showCalendar.setTime(date);

        calendar.set(Calendar.HOUR, showCalendar.get(Calendar.HOUR));
        calendar.set(Calendar.MINUTE, showCalendar.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
}
