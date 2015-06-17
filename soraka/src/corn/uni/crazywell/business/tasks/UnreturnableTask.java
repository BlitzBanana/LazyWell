package corn.uni.crazywell.business.tasks;

import javax.ejb.Remote;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by blacksheep on 16/06/15.
 */
public interface UnreturnableTask extends Serializable{
    void run();
}
