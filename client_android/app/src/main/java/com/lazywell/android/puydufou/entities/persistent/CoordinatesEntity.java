package com.lazywell.android.puydufou.entities.persistent;

import com.orm.SugarRecord;

/**
 * Created by victor on 16/06/2015.
 */
public class CoordinatesEntity extends SugarRecord<CoordinatesEntity> {

    protected double latitude;
    protected double longitude;

    public CoordinatesEntity(){
    }

    public CoordinatesEntity(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
