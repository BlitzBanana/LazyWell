package com.lazywell.android.puydufou.webservices.deserializers;

import com.lazywell.android.puydufou.entities.Restaurant;
import com.lazywell.android.puydufou.entities.Shop;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 19/06/2015.
 */
public class RestaurantDeserializer extends DeserializerBase<Restaurant> {
    @Override
    public Restaurant deserialize(SoapObject root) {
        return null;
    }

    @Override
    public List<Restaurant> deserializeList(SoapObject root) {
        List<Restaurant> restaurants = new ArrayList<>();
        for(int p=0; p < root.getPropertyCount(); p++){
            SoapObject restaurantSoap = (SoapObject) root.getProperty(p);
            String name = restaurantSoap.getProperty("name").toString();
            String description = restaurantSoap.getProperty("description").toString();
            double score = Double.parseDouble(((SoapObject) restaurantSoap.getProperty("scores")).getProperty("value").toString());
            String menu = restaurantSoap.getProperty("menu").toString();

            Restaurant restaurant = new Restaurant(name, description, score, menu);

            restaurants.add(restaurant);
        }
        return restaurants;
    }
}