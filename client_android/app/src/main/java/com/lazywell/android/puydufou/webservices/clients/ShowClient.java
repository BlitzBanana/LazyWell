package com.lazywell.android.puydufou.webservices.clients;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.business.Rate;
import com.lazywell.android.puydufou.entities.persistent.CoordinatesEntity;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;
import com.lazywell.android.puydufou.tools.WebServicesUtils;
import com.lazywell.android.puydufou.webservices.AsyncCall;
import com.lazywell.android.puydufou.webservices.WebServicesDirectory;
import com.lazywell.android.puydufou.webservices.deserializers.SessionDeserializer;
import com.lazywell.android.puydufou.webservices.deserializers.ShowDeserializer;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by victor on 16/06/2015.
 */
public class ShowClient {

    private final Context context;

    public ShowClient(Context context){
        this.context = context;
    }

    public List<ShowEntity> getShows(){
        List<ShowEntity> shows;

        AsyncCall asyncCall = new AsyncCall(
                context,
                WebServicesDirectory.NAMESPACE,
                WebServicesDirectory.Shows.Actions.GET_SHOWS,
                WebServicesDirectory.Shows.WSDL_URL,
                null);

        try {
            SoapObject response = asyncCall.execute().get();
            Log.d("SOAP WEBSERVICE", response.toString());
            shows = WebServicesUtils.getServiceResponseList(response, new ShowDeserializer());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

        for(ShowEntity show : shows)
            Log.d("CLIENT WS", show.getName());

        shows = ShowEntity.listAll(ShowEntity.class);

        Log.d("CHECK DB", "Show: " + ShowEntity.listAll(ShowEntity.class).size());
        Log.d("CHECK DB", "Session: " + SessionEntity.listAll(SessionEntity.class).size());
        Log.d("CHECK DB", "Coordinate: " + CoordinatesEntity.listAll(CoordinatesEntity.class).size());

        return shows;
    }

    public void rateShow (Rate rate)
    {

    }

    public List<SessionEntity> getBestPlanning(){
        List<SessionEntity> sessionEntities;

        List<PropertyInfo> propertyInfos = new ArrayList<>();
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName("");
        propertyInfo.setValue("");
        propertyInfo.setType(String.class);

        propertyInfos.add(propertyInfo);

        AsyncCall asyncCall = new AsyncCall(
                context,
                WebServicesDirectory.NAMESPACE,
                WebServicesDirectory.Shows.Actions.GET_BEST_SCHEDULE,
                WebServicesDirectory.Shows.WSDL_URL,
                null);

        try {
            SoapObject response = asyncCall.execute().get();
            sessionEntities = WebServicesUtils.getServiceResponseList(response, new SessionDeserializer());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }

        return sessionEntities;
    }
}
