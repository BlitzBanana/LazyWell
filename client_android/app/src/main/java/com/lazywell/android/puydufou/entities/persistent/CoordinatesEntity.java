package com.lazywell.android.puydufou.entities.persistent;

import com.orm.SugarRecord;

/**
 * Created by victor on 16/06/2015.
 */
public class CoordinatesEntity extends SugarRecord<CoordinatesEntity> {

    private long remoteId;
    private double latitude;
    private double longitude;

    public CoordinatesEntity(){
    }

    public CoordinatesEntity(long remoteId, double latitude, double longitude){
        this.remoteId = remoteId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(long remoteId) {
        this.remoteId = remoteId;
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
