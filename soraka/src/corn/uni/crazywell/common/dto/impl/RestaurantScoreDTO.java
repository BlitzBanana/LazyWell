package corn.uni.crazywell.common.dto.impl;

import corn.uni.crazywell.common.dto.DTO;

/**
 * Created by blacksheep on 16/06/15.
 */
public class RestaurantScoreDTO implements DTO{
    private int id;
    private double value;
    private int date;
    private int showId;
    private String uuid;


    public RestaurantScoreDTO(int id, double value, int date, int showId, String uuid) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.showId = showId;
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
