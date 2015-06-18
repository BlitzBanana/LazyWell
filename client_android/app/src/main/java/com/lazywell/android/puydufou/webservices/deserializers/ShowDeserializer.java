package com.lazywell.android.puydufou.webservices.deserializers;

import android.util.Log;

import com.lazywell.android.puydufou.entities.persistent.CoordinatesEntity;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;
import com.lazywell.android.puydufou.tools.EventUtils;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by victor on 18/06/2015.
 */
public class ShowDeserializer extends DeserializerBase<ShowEntity> {

    @Override
    public ShowEntity deserialize(SoapObject root) {
        return null;
    }

    @Override
    public List<ShowEntity> deserializeList(SoapObject root) {
        List<ShowEntity> shows = new ArrayList<>();
        for(int i=0; i<root.getPropertyCount(); i++){
            SoapObject item = (SoapObject) root.getProperty(i);

            long id = Long.parseLong(item.getProperty("id").toString());
            int actorNumber = Integer.parseInt(item.getProperty("actorNumber").toString());
            int durationMinutes = Integer.parseInt(item.getProperty("duration").toString());
            long coordinatesId = Long.parseLong(((SoapObject) item.getProperty("coordinate")).getProperty("id").toString());
            double latitude = Double.parseDouble(((SoapObject) item.getProperty("coordinate")).getProperty("latitude").toString());
            double longitude = Double.parseDouble(((SoapObject) item.getProperty("coordinate")).getProperty("longitude").toString());
            String creationDateString = item.getProperty("creationDateString").toString();
            String description = item.getProperty("description").toString();
            String name = item.getProperty("name").toString();
            int priority = Integer.parseInt(item.getProperty("priority").toString());
            double score = Double.parseDouble(((SoapObject) item.getProperty("scores")).getProperty("value").toString());

            Calendar duration = (Calendar) Calendar.getInstance().clone();
            duration.set(Calendar.MINUTE, 0);
            duration.set(Calendar.HOUR, 0);
            duration.set(Calendar.HOUR_OF_DAY, 0);
            duration.add(Calendar.MINUTE, durationMinutes);

            CoordinatesEntity coordinatesEntity = new CoordinatesEntity(coordinatesId, latitude, longitude);
            coordinatesEntity.save();

            ShowEntity show = ShowEntity.getByRemoteId(id);

            if(show == null)
                show = new ShowEntity();

            show.setRemoteId(id);
            show.setName(name);
            show.setDescription(description);
            show.setPriority(priority);
            show.setCreationDate(EventUtils.getDateFromDateString(creationDateString));
            show.setImage(new byte[0]);
            show.setActorNumber(actorNumber);
            show.setScore(score);
            show.setCoordinates(coordinatesEntity);
            show.setDuration(duration.getTime());
            show.save();

            Log.d("SHOW OBJECT", show.toString());

            for (int p=0; p < item.getPropertyCount(); p++){
                if(!(item.getProperty(p) instanceof SoapObject))
                    continue;

                SoapObject sessionItem = (SoapObject) item.getProperty(p);
                Log.d("SESSION CREATION", "PropertyName: " + sessionItem.getName() + "  Count:" + sessionItem.getPropertyCount());

                if(!(sessionItem.getName().equals("anyType")
                            && sessionItem.getPropertyCount() == 4
                            && sessionItem.hasProperty("timeString")))
                    continue;

                Log.d("SESSION CREATION", sessionItem.toString());

                long sessionId = Long.parseLong(sessionItem.getProperty("id").toString());

                SessionEntity session = SessionEntity.getByRemoteId(sessionId);

                Log.d("SESSION CREATION", "show:" + show.getName());
                Log.d("SESSION CREATION", "id:" + sessionId);
                Log.d("SESSION CREATION", "new session:" + (session == null));

                if(session == null)
                    session = new SessionEntity();

                session.setRemoteId(sessionId);
                session.setTime(EventUtils.getDateFromTimeString(sessionItem.getProperty("timeString").toString()));
                session.setShow(show);
                session.save();
            }
            shows.add(show);
        }
        return shows;
    }
}
