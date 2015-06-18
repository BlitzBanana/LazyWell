package com.lazywell.android.puydufou.webservices.deserializers;

import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

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
        return new ArrayList<>();
    }
}
