package com.lazywell.android.puydufou.webservices;

import android.util.Log;

import com.lazywell.android.puydufou.business.Rate;
import com.lazywell.android.puydufou.entities.persistent.ScheduleEntity;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;

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

        /*String xmlResponse = getServiceResponse(
                WebServices.NAMESPACE,
                WebServices.Shows.METHOD,
                WebServices.Shows.Actions.GET_SHOWS,
                WebServices.Shows.WSDL_URL,
                null);*/

        //Log.d("WEBSERVICE", xmlResponse);

        // Use asyncTask and KSOAP to retrieve all ShowEntities
        List<ShowEntity> showEntities = new ArrayList<>();

        // Save all in database
        for (ShowEntity showEntity : showEntities)
            showEntity.save();

        showEntities = ShowEntity.listAll(ShowEntity.class);

        return showEntities;
    }

    public void rateShow (Rate rate)
    {

    }

    public List<SessionEntity> getBestPlanning(){
        return new ArrayList<>();
    }

    private String getServiceResponse(String nameSpace, String methodName,
                                     String soapAction, String WsdlUrl, List<PropertyInfo> mPropertyInfo) {
        String mResponse = "";
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
            mResponse = envelope.getResponse().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mResponse;
    }
}
