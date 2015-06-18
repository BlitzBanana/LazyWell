package com.lazywell.android.puydufou.webservices.deserializers;

import android.util.Log;

import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;
import com.lazywell.android.puydufou.tools.EventUtils;

import org.ksoap2.serialization.SoapObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by victor on 18/06/2015.
 */
public class SessionDeserializer extends DeserializerBase<SessionEntity> {

    @Override
    public SessionEntity deserialize(SoapObject root) {
        return null;
    }

    @Override
    public List<SessionEntity> deserializeList(SoapObject root) {
        List<SessionEntity> sessions = new ArrayList<>();
        for(int p=0; p < root.getPropertyCount(); p++){
            SoapObject sessionItem = (SoapObject) root.getProperty(p);
            long id = Long.parseLong(sessionItem.getProperty("id").toString());
            long showId = Long.parseLong(sessionItem.getProperty("showId").toString());
            ShowEntity show = ShowEntity.find(ShowEntity.class, "remoteId", String.valueOf(showId)).get(0);

            SessionEntity session = SessionEntity.getByRemoteId(id);

            if(session == null)
                session = new SessionEntity();

            session.setRemoteId(id);
            session.setTime(EventUtils.getDateFromTimeString(sessionItem.getProperty("timeString").toString()));
            session.setShow(show);
            session.save();
            sessions.add(session);
        }
        return new ArrayList<>();
    }
}
