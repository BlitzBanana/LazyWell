package com.company;

import com.company.exceptions.NoNextBreakException;

import javax.xml.stream.Location;
import java.util.*;

/**
 * Created by blacksheep on 14/06/15.
 */
public class PlanningCalcul {
    private Calendar startDate;
    private Calendar lunchDate;
    private Calendar endDate;
    private int breakNumber;
    private int breakMinuteDuration;
    private int breakMinuteVariation;
    private int lunchDuration;
    private Planning generatedPlanning;
    private List<BreakTime> calculatedBreaks;
    private Calendar currentTime;

    /*CONSTRUCTION*/
    public PlanningCalcul(){

    }
    public PlanningCalcul(Calendar startDate, Calendar lunchDate, Calendar endDate, int breakNumber, int breakMinuteDuration,  int breakMinuteVariation) {
        this.startDate = startDate;
        this.lunchDate = lunchDate;
        this.endDate = endDate;
        this.breakNumber = breakNumber;
        this.breakMinuteDuration = breakMinuteDuration;
        this.breakMinuteVariation = breakMinuteVariation;
    }



    /* WEBSERVICES PART */
    /* à coder sur le webservice*/
    //TODO : retourner les activités qui sont faisables durant minutesBeforeNextBreak. Le temps de trajet est pris en comtpe par le serveur.
    private List<Show> getAvailableActivities(final int minutesBeforeNextBreak, final Location currentLocation){
        return new ArrayList<>();
    }
    //TODO : parmi la liste d'activité en paramètre, retourner uniquement celle(s) de la priorité la plus importante
    private List<Show> filterActivitiesByPriority(final List<Show> input){
        return input;
    }




    /* SERVICE PART*/
    /* Fait ailleurs dans l'application*/
    private Location getCurrentLocation(){
        return new Location() {
            @Override
            public int getLineNumber() {
                return 0;
            }

            @Override
            public int getColumnNumber() {
                return 0;
            }

            @Override
            public int getCharacterOffset() {
                return 0;
            }

            @Override
            public String getPublicId() {
                return null;
            }

            @Override
            public String getSystemId() {
                return null;
            }
        };
    }



    /* ALGORITHM PART - /!\ SET LOCATION TO ANDROID PACKAGE*/
    /*Pour lire l'algorithme, commencer ici*/
    public  void calculate() {
        final int morningDurationMinute = getMorningMinuteDuration();
        final int afternoonDurationMinute = getAfternoonMinuteDuration();
        final int morningBreakIntervalMinute = morningDurationMinute / (breakNumber/2 + 1);
        final int afternoonBreakIntervalMinute = afternoonDurationMinute / (breakNumber/2 + 1);
        try {
            planMorning(morningBreakIntervalMinute);
        }
        catch (NoNextBreakException ex) {
            generatedPlanning.activities.add(new LunchTime(lunchDuration, lunchDate.getTime()));
            try {
                planAfternoon(afternoonBreakIntervalMinute);
            } catch (NoNextBreakException e1) {
                e1.printStackTrace();
            }
        }
    }

    private int getMorningMinuteDuration(){
        return getSubstractMinute(startDate, lunchDate);
    }

    private int getAfternoonMinuteDuration(){
        Calendar lunchEndDate = lunchDate;
        lunchEndDate.add(Calendar.MINUTE, lunchDuration);
        return lunchEndDate.get(Calendar.HOUR) * 60 + lunchEndDate.get(Calendar.MINUTE);
    }

    private int getSubstractMinute(final Calendar hour1, final Calendar hour2){
        final int substractedHours = hour1.get(Calendar.HOUR) - hour2.get(Calendar.HOUR);
        final int substractedminutes = hour1.get(Calendar.MINUTE) - hour2.get(Calendar.MINUTE);
        return substractedminutes + substractedHours * 60;
    }

    private Show selectActivity(final List<Show> activities){
        Random rand = new Random();
        int nombreAleatoire = rand.nextInt(activities.size() - 0 + 1) + 0;
        return activities.get(nombreAleatoire);
    }

    private void planMorning(final int breakIntervalMinute) throws NoNextBreakException {
        calculateBreaks(breakIntervalMinute, lunchDate.getTime());
        List<Show> availableActivities = null;
        do {
            availableActivities = getAvailableActivities(getMinutesBeforeNextBreak(currentTime.getTime()), getCurrentLocation());
            availableActivities = filterActivitiesByPriority(availableActivities);
            if (availableActivities.size() > 0) {
                final Show show = selectActivity(availableActivities);
                generatedPlanning.activities.add(show);
                currentTime.setTime(show.getEndDate());

            } else {
                final BreakTime breakTime = getNextBreak(currentTime.getTime());
                generatedPlanning.activities.add(breakTime);
                currentTime.add(Calendar.MINUTE, breakTime.getMinuteDuration());
            }
        } while(availableActivities != null);
    }

    private void planAfternoon(final int breakIntervalMinute) throws NoNextBreakException {
        calculateBreaks(breakIntervalMinute, lunchDate.getTime());
        List<Show> availableActivities;
        do {
            availableActivities = getAvailableActivities(getMinutesBeforeNextBreak(currentTime.getTime()), getCurrentLocation());
            availableActivities = filterActivitiesByPriority(availableActivities);
            if (availableActivities.size() > 0) {
                final Show show = selectActivity(availableActivities);
                generatedPlanning.activities.add(show);
                currentTime.setTime(show.getEndDate());
            } else {
                final BreakTime breakTime = getNextBreak(currentTime.getTime());
                generatedPlanning.activities.add(breakTime);
                currentTime.add(Calendar.MINUTE, breakTime.getMinuteDuration());
            }
        } while(availableActivities != null);
    }

    private BreakTime getNextBreak(final Date currentTime) throws NoNextBreakException {
        final Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(currentTime);
        for(BreakTime b : calculatedBreaks){
            if(b.getPlanedTime().after(currentTime)){
                return b;
            }
        }
        throw new NoNextBreakException();
    }

    private int getMinutesBeforeNextBreak(final Date currentTime) throws NoNextBreakException{
        final BreakTime nextBreak = getNextBreak(currentTime);
        final Calendar planedTime = Calendar.getInstance();
        final Calendar actualTime = Calendar.getInstance();
        planedTime.setTime(nextBreak.getPlanedTime());
        actualTime.setTime(currentTime);
        return (actualTime.get(Calendar.HOUR) * 60 + actualTime.get(Calendar.MINUTE)) - (planedTime.get(Calendar.HOUR) * 60 + planedTime.get(Calendar.MINUTE)) + breakMinuteVariation;
    }

    private void calculateBreaks(final int breakIntervalMinute, final Date endDate){
        calculatedBreaks = new ArrayList<>();
        Date previousTime = startDate.getTime();
        calculatedBreaks.add(0, new BreakTime(breakMinuteDuration, startDate.getTime()));
        for (int i = 1; i < breakNumber; i++) {
            if(i == 0){
                Calendar tempDate = startDate;
                tempDate.add(Calendar.MINUTE, breakIntervalMinute);
                calculatedBreaks.add(new BreakTime(breakMinuteDuration, tempDate.getTime()));
                previousTime = tempDate.getTime();
            }else {
                Calendar tempDate = Calendar.getInstance();
                tempDate.setTime(previousTime);
                tempDate.add(Calendar.MINUTE, breakIntervalMinute);
                calculatedBreaks.add(new BreakTime(breakIntervalMinute, tempDate.getTime()));
                previousTime = tempDate.getTime();
            }
        }
        calculatedBreaks.add(new BreakTime(breakMinuteDuration, endDate));
    }



    /* Getters & setters */
    public List<BreakTime> getCalculatedBreaks() {
        return calculatedBreaks;
    }

    public void setCalculatedBreaks(List<BreakTime> calculatedBreaks) {
        this.calculatedBreaks = calculatedBreaks;
    }

    public Calendar getLunchDate() {
        return lunchDate;
    }

    public void setLunchDate(Calendar lunchDate) {
        this.lunchDate = lunchDate;
    }

    public int getLunchDuration() {
        return lunchDuration;
    }

    public void setLunchDuration(int lunchDuration) {
        this.lunchDuration = lunchDuration;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Planning getGeneratedPlanning() {
        return generatedPlanning;
    }

    public void setGeneratedPlanning(Planning generatedPlanning) {
        this.generatedPlanning = generatedPlanning;
    }
}
