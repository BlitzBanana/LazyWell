package com.lazywell.android.puydufou.webservices.deserializers;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

/**
 * Created by victor on 17/06/2015.
 */
public abstract class DeserializerBase<T> {
    public abstract T deserialize(SoapObject root);
    public abstract List<T> deserializeList(SoapObject root);
}