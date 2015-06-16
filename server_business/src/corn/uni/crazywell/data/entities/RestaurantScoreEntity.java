package corn.uni.crazywell.data.entities;

import javax.persistence.*;

/**
 * Created by blacksheep on 16/06/15.
 */
@Entity
@Table(name = "restaurant_score", schema = "", catalog = "lazywell")
public class RestaurantScoreEntity {
    private int id;
    private int value;
    private int date;
    private int restaurantId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value")
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Basic
    @Column(name = "date")
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Basic
    @Column(name = "restaurant_id")
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantScoreEntity that = (RestaurantScoreEntity) o;

        if (id != that.id) return false;
        if (value != that.value) return false;
        if (date != that.date) return false;
        if (restaurantId != that.restaurantId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + value;
        result = 31 * result + date;
        result = 31 * result + restaurantId;
        return result;
    }
}
