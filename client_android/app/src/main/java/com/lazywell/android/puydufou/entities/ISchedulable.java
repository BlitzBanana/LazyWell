package com.lazywell.android.puydufou.entities;

import java.util.Calendar;

/**
 * Created by victor on 16/06/2015.
 */
public interface ISchedulable {
    Long getId();
    String getTitle();
    Calendar getStartDate();
    Calendar getEndDate();
    int getColor();
}
