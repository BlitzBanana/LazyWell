package corn.uni.crazywell.common.dto.impl;

import corn.uni.crazywell.common.dto.DTO;

/**
 * Created by blacksheep on 16/06/15.
 */
public class RestaurantDTO implements DTO {
    private int id;
    private int CoordinateId;
    private String name;
    private String description;
    private String menu;
    private CoordinateDTO coordinate;
    private RestaurantScoreDTO scores;

    public CoordinateDTO getCoordinate() {
        return coordinate;
    }

    public RestaurantScoreDTO getScores() {
        return scores;
    }

    public void setScores(RestaurantScoreDTO scores) {
        this.scores = scores;
    }

    public void setCoordinate(CoordinateDTO coordinate) {
        this.coordinate = coordinate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoordinateId() {
        return CoordinateId;
    }

    public void setCoordinateId(int coordinateId) {
        CoordinateId = coordinateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
