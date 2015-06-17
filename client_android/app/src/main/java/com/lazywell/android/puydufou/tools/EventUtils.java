package com.lazywell.android.puydufou.tools;

import android.content.Context;
import android.util.Log;

import com.alamkanak.weekview.WeekViewEvent;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.ISchedulable;

import java.util.Calendar;

/**
 * Created by victor on 17/06/2015.
 */
public class EventUtils {

    public static WeekViewEvent schedulableToEvent(Context context, ISchedulable schedulable, int newYear, int newMonth){
        Calendar startTime = schedulable.getStartDate();
        startTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = schedulable.getEndDate();
        endTime.set(Calendar.MONTH, newMonth - 1);
        startTime.set(Calendar.YEAR, newYear);
        WeekViewEvent event = new WeekViewEvent(schedulable.getId(), schedulable.getTitle(), startTime, endTime);
        event.setColor(
                context.getResources().getColor(
                        schedulable.getColor()
                ));

        Log.d("EVENT", "(session)Start Time: " + schedulable.getStartDate().getTime().toString());
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
