package corn.uni.crazywell.common.dto.impl;

import corn.uni.crazywell.common.dto.DTO;

import java.math.BigDecimal;

/**
 * Created by blacksheep on 16/06/15.
 */
public class CoordinateDTO implements DTO{
    private int id;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
