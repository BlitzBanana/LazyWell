package com.company;

import java.util.Date;

/**
 * Created by blacksheep on 14/06/15.
 */
public class Show implements Plannifiable {
    private Date startDate;
    private Date endDate;

    private int minuteDuration;


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getMinuteDuration() {
        return minuteDuration;
    }

    public void setMinuteDuration(int minuteDuration) {
        this.minuteDuration = minuteDuration;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
