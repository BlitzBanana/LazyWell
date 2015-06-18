package com.lazywell.android.puydufou.webservices;

import android.content.Context;
import android.os.AsyncTask;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lazywell.android.puydufou.R;
import com.lazywell.android.puydufou.tools.WebServicesUtils;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.List;

/**
 * Created by victor on 18/06/2015.
 */
public class AsyncCall extends AsyncTask<Void, Integer, SoapObject>
{
    Context context;
    String namespace;
    String method;
    String wsdlUrl;
    List<PropertyInfo> propertiesInfo;

    public AsyncCall(Context context, String namespace, String method,
                     String wsdlUrl, List<PropertyInfo> propertiesInfo){
        this.context = context;
        this.namespace = namespace;
        this.method = method;
        this.wsdlUrl = wsdlUrl;
        this.propertiesInfo = propertiesInfo;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected SoapObject doInBackground(Void... arg0) {
        return WebServicesUtils.getServiceResponse(namespace, method, method, wsdlUrl, propertiesInfo);
    }

    @Override
    protected void onPostExecute(SoapObject soapObject) {
        super.onPostExecute(soapObject);
    }
}