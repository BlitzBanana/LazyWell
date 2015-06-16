package corn.uni.crazywell.data.entities;

import javax.persistence.*;

/**
 * Created by blacksheep on 16/06/15.
 */
@Entity
@Table(name = "restaurant", schema = "", catalog = "lazywell")
public class RestaurantEntity {
    private int id;
    private int coordinateId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "coordinate_id")
    public int getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(int coordinateId) {
        this.coordinateId = coordinateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantEntity that = (RestaurantEntity) o;

        if (id != that.id) return false;
        if (coordinateId != that.coordinateId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + coordinateId;
        return result;
    }
}
