package com.lazywell.android.puydufou.webservices.deserializers;

import com.lazywell.android.puydufou.entities.Shop;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;
import com.lazywell.android.puydufou.tools.EventUtils;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 19/06/2015.
 */
public class ShopDeserializer extends DeserializerBase<Shop> {
    @Override
    public Shop deserialize(SoapObject root) {
        return null;
    }

    @Override
    public List<Shop> deserializeList(SoapObject root) {
        List<Shop> shops = new ArrayList<>();
        for(int p=0; p < root.getPropertyCount(); p++){
            SoapObject shopSoap = (SoapObject) root.getProperty(p);
            String name = shopSoap.getProperty("name").toString();
            String description = shopSoap.getProperty("description").toString();
            double score = Double.parseDouble(((SoapObject) shopSoap.getProperty("scores")).getProperty("value").toString());

            Shop shop = new Shop(name, description, score);

            shops.add(shop);
        }
        return shops;
    }
}