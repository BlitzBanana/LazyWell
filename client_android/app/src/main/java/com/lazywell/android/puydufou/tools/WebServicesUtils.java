package com.lazywell.android.puydufou.tools;

import android.util.Log;

import com.lazywell.android.puydufou.webservices.deserializers.DeserializerBase;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by victor on 18/06/2015.
 */
public class WebServicesUtils {

    public static SoapObject getServiceResponse(String nameSpace, String methodName,
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
            Object tmpResponse = (SoapObject) envelope.bodyIn;
            if (tmpResponse instanceof SoapObject){
                response = (SoapObject) tmpResponse;
                Log.d("SOAP", response.toString());
            }
            else if (tmpResponse instanceof SoapFault)
                throw new Exception(((SoapFault)tmpResponse).getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static  <T> T getServiceResponseObject(SoapObject response, DeserializerBase<T> deserializer){
        return deserializer.deserialize(response);
    }

    public static  <T> List<T> getServiceResponseList(SoapObject response, DeserializerBase<T> deserializer){
        return deserializer.deserializeList(response);
    }
}
