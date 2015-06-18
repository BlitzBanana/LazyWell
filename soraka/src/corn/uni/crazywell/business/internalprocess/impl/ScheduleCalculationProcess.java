package corn.uni.crazywell.business.internalprocess.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import corn.uni.crazywell.business.internalcomponent.BreakTime;
import corn.uni.crazywell.business.internalcomponent.LocationPoint;
import corn.uni.crazywell.business.internalcomponent.LunchTime;
import corn.uni.crazywell.business.internalcomponent.Planning;
import corn.uni.crazywell.business.internalprocess.InternalProcess;
import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.SessionDTO;
import corn.uni.crazywell.common.dto.impl.ShowDTO;
import corn.uni.crazywell.common.exception.AlgorithmException;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.common.exception.NoNextBreakException;
import corn.uni.crazywell.data.dao.ShowDaoLocal;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.inject.Inject;
import java.util.*;

/**
 * Created by blacksheep on 18/06/15.
 */
public class ScheduleCalculationProcess implements InternalProcess{
    /* Required Fields */
    private Calendar startDate;
    private Calendar lunchDate;
    private Calendar endDate;
    private LocationPoint coords;
    private int breakNumber;
    private int breakMinuteDuration;
    private int breakMinuteVariation;
    private int lunchDuration;

    /* Calculated vars */
    private Planning generatedPlanning;
    private List<BreakTime> calculatedBreaks;
    private Calendar currentTime;

    /* Injections */
    @Inject private ShowDaoLocal showDaoLocal;
    @Inject private DTOConverterLocal<ShowEntity, ShowDTO> showDTOConverter;

    /* Constants */
    private static final int MAX_MINUTE_WALKING_TIME = 15;




    /*CONSTRUCTION*/
    public ScheduleCalculationProcess(){

    }
    public ScheduleCalculationProcess(Calendar startDate, Calendar lunchDate, Calendar endDate, int breakNumber, int breakMinuteDuration, int breakMinuteVariation, int lat, int lon) {
        this.startDate = startDate;
        this.lunchDate = lunchDate;
        this.endDate = endDate;
        this.breakNumber = breakNumber;
        this.breakMinuteDuration = breakMinuteDuration;
        this.breakMinuteVariation = breakMinuteVariation;
        this.coords = new LocationPoint(lat, lon);
    }



    /* WEBSERVICES PART */
    /* à coder sur le webservice*/
    private List<SessionDTO> getAvailableActivities(final int minutesBeforeNextBreak, final LocationPoint currentLocation) throws AlgorithmException {
        try{
            final List<ShowDTO> showList = showDaoLocal.getAvailableActivities(minutesBeforeNextBreak, MAX_MINUTE_WALKING_TIME, showDTOConverter);
            final List<SessionDTO> sessionsList = new ArrayList<>();
            final Calendar tempCalendar = currentTime;
            tempCalendar.add(Calendar.MINUTE, MAX_MINUTE_WALKING_TIME);
            Date expectedStartTime = tempCalendar.getTime();
            for(final ShowDTO show : showList){
                for(final SessionDTO session : show.getSessions()){
                    if(session.getTime().after(expectedStartTime)){
                        sessionsList.add(session);
                        break;
                    }
                }
            }
            return sessionsList;
        } catch (DAOException e) {
            e.printStackTrace();
            throw new AlgorithmException("Failed to get session From DAO!", e);
        }
    }
    private List<SessionDTO> filterActivitiesByPriority(final List<SessionDTO> sessionDTOs){
        final Map<SessionDTO, ShowEntity> showMap = new HashMap<>();
        int currentBestPriority = 0;
        //Obtention des priorités
        for(SessionDTO session : sessionDTOs){
            if (showMap.get(session) != null && showMap.get(session).getPriority() > currentBestPriority){
                currentBestPriority = showMap.get(session).getPriority();
            } else {
                try {
                    final ShowEntity showEntity = showDaoLocal.find(session);
                    if(showEntity.getPriority() > currentBestPriority){
                        showMap.put(session, showEntity);
                        currentBestPriority = showEntity.getPriority();
                    }
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
        }
        //Trie
        final int definitiveExpectedPriority = currentBestPriority;
        Maps.filterEntries(showMap, new Predicate<Map.Entry<SessionDTO, ShowEntity>>() {
            @Override
            public boolean apply(Map.Entry<SessionDTO, ShowEntity> input) {
                return input.getValue().getPriority() == definitiveExpectedPriority;
            }
        });
        return Lists.newArrayList(showMap.keySet());
    }




    /* ALGORITHM PART - /!\ SET LOCATION TO ANDROID PACKAGE*/
    /*Pour lire l'algorithme, commencer ici*/
    public  Planning calculate() throws AlgorithmException {
        final int morningDurationMinute = getMorningMinuteDuration();
        final int afternoonDurationMinute = getAfternoonMinuteDuration();
        final int morningBreakIntervalMinute = morningDurationMinute / (breakNumber/2 + 1);
        final int afternoonBreakIntervalMinute = afternoonDurationMinute / (breakNumber/2 + 1);
        try {
            planMorning(morningBreakIntervalMinute);
            throw new NoNextBreakException();
        }
        catch (NoNextBreakException ex) {
            try {
                planAfternoon(afternoonBreakIntervalMinute);
                throw new NoNextBreakException();
            } catch (NoNextBreakException e1) {
                return generatedPlanning;
            }
        }
        catch (Exception ex){
            throw new AlgorithmException(ex);
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

    private SessionDTO selectActivity(final List<SessionDTO> activities){
        Random rand = new Random();
        int nombreAleatoire = rand.nextInt(activities.size() - 0 + 1) + 0;
        return activities.get(nombreAleatoire);
    }

    private void planMorning(final int breakIntervalMinute) throws NoNextBreakException, AlgorithmException {
        calculateBreaks(breakIntervalMinute, lunchDate.getTime());
        List<SessionDTO> availableActivities;
        do {
            availableActivities = getAvailableActivities(getMinutesBeforeNextBreak(currentTime.getTime()), getCurrentLocation());
            availableActivities = filterActivitiesByPriority(availableActivities);
            if (availableActivities.size() > 0) {
                final SessionDTO show = selectActivity(availableActivities);
                generatedPlanning.getActivities().add(show);
                currentTime.setTime(show.getTime());

            } else {
                final BreakTime breakTime = getNextBreak(currentTime.getTime());
                currentTime.add(Calendar.MINUTE, breakTime.getMinuteDuration());
            }
        } while(availableActivities != null);
    }

    private void planAfternoon(final int breakIntervalMinute) throws NoNextBreakException, AlgorithmException {
        calculateBreaks(breakIntervalMinute, lunchDate.getTime());
        List<SessionDTO> availableActivities;
        do {
            availableActivities = getAvailableActivities(getMinutesBeforeNextBreak(currentTime.getTime()), getCurrentLocation());
            availableActivities = filterActivitiesByPriority(availableActivities);
            if (availableActivities.size() > 0) {
                final SessionDTO show = selectActivity(availableActivities);
                generatedPlanning.getActivities().add(show);
                currentTime.setTime(show.getTime());
            } else {
                final BreakTime breakTime = getNextBreak(currentTime.getTime());
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
                final Calendar tempDate = startDate;
                tempDate.add(Calendar.MINUTE, breakIntervalMinute);
                calculatedBreaks.add(new BreakTime(breakMinuteDuration, tempDate.getTime()));
                previousTime = tempDate.getTime();
            }else {
                final Calendar tempDate = Calendar.getInstance();
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

    public LocationPoint getCurrentLocation(){
        return coords;
    }
}
