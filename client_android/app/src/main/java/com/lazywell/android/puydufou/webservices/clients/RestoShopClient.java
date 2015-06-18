package com.lazywell.android.puydufou.webservices.clients;

import android.content.Context;
import android.util.Log;

import com.lazywell.android.puydufou.entities.Restaurant;
import com.lazywell.android.puydufou.entities.Shop;
import com.lazywell.android.puydufou.entities.persistent.CoordinatesEntity;
import com.lazywell.android.puydufou.entities.persistent.SessionEntity;
import com.lazywell.android.puydufou.entities.persistent.ShowEntity;
import com.lazywell.android.puydufou.tools.WebServicesUtils;
import com.lazywell.android.puydufou.webservices.AsyncCall;
import com.lazywell.android.puydufou.webservices.WebServicesDirectory;
import com.lazywell.android.puydufou.webservices.deserializers.RestaurantDeserializer;
import com.lazywell.android.puydufou.webservices.deserializers.ShopDeserializer;
import com.lazywell.android.puydufou.webservices.deserializers.ShowDeserializer;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by victor on 19/06/2015.
 */
public class RestoShopClient {

    private final Context context;

    public RestoShopClient(Context context){
        this.context = context;
    }

    public List<Shop> getShops(){
        List<Shop> shops;

        AsyncCall asyncCall = new AsyncCall(
                context,
                WebServicesDirectory.NAMESPACE,
                WebServicesDirectory.Shops.Actions.GET_SHOPS,
                WebServicesDirectory.Shops.WSDL_URL,
                null);

        try {
            SoapObject response = asyncCall.execute().get();
            Log.d("SOAP WEBSERVICE", response.toString());
            shops = WebServicesUtils.getServiceResponseList(response, new ShopDeserializer());
            return shops;
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
    }

    public List<Restaurant> getRestaurants(){
        List<Restaurant> restaurants;

        AsyncCall asyncCall = new AsyncCall(
                context,
                WebServicesDirectory.NAMESPACE,
                WebServicesDirectory.Restaurants.Actions.GET_RESTAURANTS,
                WebServicesDirectory.Restaurants.WSDL_URL,
                null);

        try {
            SoapObject response = asyncCall.execute().get();
            Log.d("SOAP WEBSERVICE", response.toString());
            restaurants = WebServicesUtils.getServiceResponseList(response, new RestaurantDeserializer());
            return restaurants;
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
    }
}
