package com.lazywell.android.puydufou.webservices;

import com.lazywell.android.puydufou.business.Rate;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;
import com.lazywell.android.puydufou.webservices.deserializers.DeserializerBase;
import com.lazywell.android.puydufou.webservices.deserializers.SessionDeserializer;
import com.lazywell.android.puydufou.webservices.deserializers.ShowDeserializer;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 16/06/2015.
 */
public class ShowClient {

    private static final String NAMESPACE = "http://www.w3schools.com/webservices/";

    public List<ShowEntity> getShows(){
        List<ShowEntity> showEntities = getServiceResponseList(
                WebServices.NAMESPACE,
                WebServices.Shows.METHOD,
                WebServices.Shows.Actions.GET_SHOWS,
                WebServices.Shows.WSDL_URL,
                null,
                new ShowDeserializer());

        for (ShowEntity showEntity : showEntities)
            showEntity.save();

        showEntities = ShowEntity.listAll(ShowEntity.class);

        return showEntities;
    }

    public void rateShow (Rate rate)
    {

    }

    public List<SessionEntity> getBestPlanning(){
        List<PropertyInfo> propertyInfos = new ArrayList<>();

        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName("");
        propertyInfo.setValue("");
        propertyInfo.setType(String.class);

        propertyInfos.add(propertyInfo);

        return getServiceResponseList(
                WebServices.NAMESPACE,
                WebServices.Shows.METHOD,
                WebServices.Shows.Actions.GET_SHOWS,
                WebServices.Shows.WSDL_URL,
                null,
                new SessionDeserializer());
    }

    private SoapObject getServiceResponse(String nameSpace, String methodName,
                                     String soapAction, String WsdlUrl, List<PropertyInfo> mPropertyInfo) {
        SoapObject response = null;
        SoapObject request = new SoapObject(nameSpace, methodName);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        if (mPropertyInfo != null) {
            for (PropertyInfo propertyInfo : mPropertyInfo) {
                request.addProperty(propertyInfo);
            }
        }
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE ht = new HttpTransportSE(WsdlUrl);
        ht.debug = true;
        try {
            ht.call(soapAction, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            SoapObject resultRequestSOAP = (SoapObject) envelope.bodyIn;
            response = (SoapObject) resultRequestSOAP.getProperty(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public <T> T getServiceResponseObject(String nameSpace, String methodName,
                                          String soapAction, String wsdlUrl, List<PropertyInfo> propertyInfo, DeserializerBase<T> deserializer){
        SoapObject root = getServiceResponse(
                nameSpace,
                methodName,
                soapAction,
                wsdlUrl,
                propertyInfo);
        return deserializer.deserialize(root);
    }

    public <T> List<T> getServiceResponseList(String nameSpace, String methodName,
                                              String soapAction, String wsdlUrl, List<PropertyInfo> propertyInfo, DeserializerBase<T> deserializer){
        SoapObject root = getServiceResponse(
                nameSpace,
                methodName,
                soapAction,
                wsdlUrl,
                propertyInfo);
        return deserializer.deserializeList(root);
    }
}
