package com.lazywell.android.puydufou.webservices.clients;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.lazywell.android.puydufou.business.Score;
import com.lazywell.android.puydufou.tools.DeviceUuidFactory;
import com.lazywell.android.puydufou.webservices.AsyncCall;
import com.lazywell.android.puydufou.webservices.Bubble;
import com.lazywell.android.puydufou.webservices.WebServicesDirectory;

import org.ksoap2.serialization.PropertyInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by victor on 19/06/2015.
 */
public class ScoreClient {

    private final Context context;

    public ScoreClient(Context context){
        this.context = context;
    }

    public void rateShow (Score score, Bubble.Eval item, long remoteId)
    {
        TelephonyManager tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String uuid = tManager.getDeviceId();

        if(uuid == null || uuid.equals("")){
            uuid = new DeviceUuidFactory(context).getDeviceUuid().toString();
        }

        if(uuid.equals("")){
            uuid = DeviceUuidFactory.generateString(new Random(), "azetyiuopqsfghjklmwxcvn", 10);
        }

        Bubble bubble = new Bubble();
        bubble.getBody().add(item);
        bubble.getBody().add(uuid);
        bubble.getBody().add(remoteId);
        bubble.getBody().add(score.getValue());

        List<PropertyInfo> props = new ArrayList<>();

        PropertyInfo propType = new PropertyInfo();
        propType.setName("eval");
        propType.setValue(item.name());
        propType.setType(String.class);

        PropertyInfo propUuid = new PropertyInfo();
        propUuid.setName("uuid");
        propUuid.setValue(uuid);
        propUuid.setType(String.class);

        PropertyInfo propId = new PropertyInfo();
        propId.setName("id");
        propId.setValue(safeLongToInt(remoteId));
        propId.setType(int.class);

        PropertyInfo propScore = new PropertyInfo();
        propScore.setName("note");
        propScore.setValue(score.getValue());
        propScore.setType(int.class);

        props.add(propType);
        props.add(propUuid);
        props.add(propId);
        props.add(propScore);

        AsyncCall asyncCall = new AsyncCall(
                context,
                WebServicesDirectory.NAMESPACE,
                WebServicesDirectory.Score.Actions.SET_SCORE,
                WebServicesDirectory.Score.WSDL_URL,
                props);

        asyncCall.execute();
    }

    private static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }
}
