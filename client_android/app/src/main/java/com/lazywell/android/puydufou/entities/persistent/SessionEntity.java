package com.lazywell.android.puydufou.entities.persistent;

import com.lazywell.android.puydufou.entities.ISchedulable;
import com.lazywell.android.puydufou.tools.EventUtils;
import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by victor on 16/06/2015.
 */
public class SessionEntity  extends SugarRecord<SessionEntity> implements ISchedulable {

    private long remoteId;
    private Date time;
    private ShowEntity show;

    public SessionEntity(){
    }

    public SessionEntity(long remoteId, Date time, ShowEntity show){
        this.remoteId = remoteId;
        this.time = dateToToday(time);
        this.show = show;
    }

    public long getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(long remoteId) {
        this.remoteId = remoteId;
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

    @Override
    public Calendar getStartDate() {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.setTime(this.time);
        return calendar;
    }

    @Override
    public Calendar getEndDate() {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        Calendar duration = (Calendar) Calendar.getInstance().clone();
        calendar.setTime(this.time);
        duration.setTime(getShow().getDuration());
        calendar.add(Calendar.HOUR, duration.get(Calendar.HOUR));
        calendar.add(Calendar.MINUTE, duration.get(Calendar.MINUTE));
        return calendar;
    }

    @Override
    public int getColor() {
        return EventUtils.getPriorityColor(show.getPriority());
    }

    @Override
    public String getTitle() {
        return show.getName();
    }
}
