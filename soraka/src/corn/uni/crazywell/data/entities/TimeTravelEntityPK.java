package corn.uni.crazywell.data.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by blacksheep on 16/06/15.
 */
public class TimeTravelEntityPK implements Serializable {
    private int show1Id;
    private int show2Id;

    @Column(name = "show1_id")
    @Id
    public int getShow1Id() {
        return show1Id;
    }

    public void setShow1Id(int show1Id) {
        this.show1Id = show1Id;
    }

    @Column(name = "show2_id")
    @Id
    public int getShow2Id() {
        return show2Id;
    }

    public void setShow2Id(int show2Id) {
        this.show2Id = show2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeTravelEntityPK that = (TimeTravelEntityPK) o;

        if (show1Id != that.show1Id) return false;
        if (show2Id != that.show2Id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = show1Id;
        result = 31 * result + show2Id;
        return result;
    }
}
