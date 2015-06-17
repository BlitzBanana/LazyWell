package com.lazywell.android.puydufou.tools;

import android.content.Context;
import android.util.Log;

import com.alamkanak.weekview.WeekViewEvent;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;

import java.util.Calendar;

/**
 * Created by victor on 17/06/2015.
 */
public class EventUtils {

    public static WeekViewEvent sessionToEvent(Context context, SessionEntity sessionEntity){
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(sessionEntity.getTime());

        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, sessionEntity.getShow().getDuration().getHours());

        WeekViewEvent event = new WeekViewEvent(1, sessionEntity.getShow().getName(), startTime, endTime);
        event.setColor(
                context.getResources().getColor(
                        getPriorityColor(
                                sessionEntity.getShow().getPriority()
                        )));

        Log.d("EVENT", "(session)Start Time: " + sessionEntity.getTime().toString());
        Log.d("EVENT", "(event)Start Time: " + event.getStartTime().getTime().toString());
        Log.d("EVENT", "(event)End Time: " + event.getEndTime().getTime().toString());
        return event;
    }

    public static int getPriorityColor(int priority){
        switch (priority){
            case 0:
                return R.color.red;
            case 1:
                return R.color.green;
            default:
                return R.color.blue;
        }
    }
}
