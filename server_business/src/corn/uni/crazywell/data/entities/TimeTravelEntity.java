package corn.uni.crazywell.data.entities;

import javax.persistence.*;

/**
 * Created by blacksheep on 16/06/15.
 */
@Entity
@Table(name = "time_travel", schema = "", catalog = "lazywell")
@IdClass(TimeTravelEntityPK.class)
public class TimeTravelEntity {
    private int show1Id;
    private int show2Id;
    private long value;

    @Id
    @Column(name = "show1_id")
    public int getShow1Id() {
        return show1Id;
    }

    public void setShow1Id(int show1Id) {
        this.show1Id = show1Id;
    }

    @Id
    @Column(name = "show2_id")
    public int getShow2Id() {
        return show2Id;
    }

    public void setShow2Id(int show2Id) {
        this.show2Id = show2Id;
    }

    @Basic
    @Column(name = "value")
    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeTravelEntity that = (TimeTravelEntity) o;

        if (show1Id != that.show1Id) return false;
        if (show2Id != that.show2Id) return false;
        if (value != that.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = show1Id;
        result = 31 * result + show2Id;
        result = 31 * result + (int) (value ^ (value >>> 32));
        return result;
    }
}
