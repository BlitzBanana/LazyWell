package corn.uni.crazywell.common.dto.impl;

import corn.uni.crazywell.common.dto.DTO;

/**
 * Created by blacksheep on 16/06/15.
 */
public class ShopDTO implements DTO {
    private int id;
    private String name;
    private String description;
    private CoordinateDTO coordinate;
    private ShopScoreDTO scores;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShopScoreDTO getScores() {
        return scores;
    }

    public void setScores(ShopScoreDTO scores) {
        this.scores = scores;
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

    public CoordinateDTO getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CoordinateDTO coordinate) {
        this.coordinate = coordinate;
    }
}
