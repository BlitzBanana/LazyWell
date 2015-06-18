package com.lazywell.android.puydufou.webservices.deserializers;

import com.lazywell.android.puydufou.entities.persistent.SessionEntity;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

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
        return new ArrayList<>();
    }
}
