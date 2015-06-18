package corn.uni.crazywell.business.internalcomponent;

/**
 * Created by blacksheep on 18/06/15.
 */
public class LocationPoint {
    private double lat;
    private double lon;

    public LocationPoint(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
