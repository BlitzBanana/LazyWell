package com.lazywell.android.puydufou.tools;

import android.content.Context;
import android.util.Log;

import com.alamkanak.weekview.WeekViewEvent;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.entities.ISchedulable;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        endTime.set(Calendar.YEAR, newYear);

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

    public static Date getDateFromDateString(String date){
        String[] split = date.split(":");
        int years = Integer.parseInt(split[0]);
        int months = Integer.parseInt(split[1]);
        int days = Integer.parseInt(split[2]);

        Calendar calendar = (Calendar) Calendar.getInstance(Locale.FRANCE).clone();
        calendar.set(Calendar.YEAR, years);
        calendar.set(Calendar.MONTH, months);
        calendar.set(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date getDateFromTimeString(String date){
        String[] split = date.split(":");
        int hours = Integer.parseInt(split[0]);
        int minutes = Integer.parseInt(split[1]);

        Calendar calendar = (Calendar) Calendar.getInstance(Locale.FRANCE).clone();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}
