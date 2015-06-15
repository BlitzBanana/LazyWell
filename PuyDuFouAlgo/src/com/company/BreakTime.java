package com.company;

import java.util.Date;

/**
 * Created by blacksheep on 14/06/15.
 */
public class BreakTime implements Plannifiable {
    private int minuteDuration;
    private boolean done;
    private Date planedTime;


    public BreakTime(int minuteDuration, Date planedTime) {
        this.minuteDuration = minuteDuration;
        this.planedTime = planedTime;
    }

    public int getMinuteDuration() {
        return minuteDuration;
    }

    public void setMinuteDuration(int minuteDuration) {
        this.minuteDuration = minuteDuration;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getPlanedTime() {
        return planedTime;
    }

    public void setPlanedTime(Date planedTime) {
        this.planedTime = planedTime;
    }
}
