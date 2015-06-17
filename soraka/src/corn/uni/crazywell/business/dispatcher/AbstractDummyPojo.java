package corn.uni.crazywell.business.dispatcher;

import javax.ejb.Stateless;

/**
 * Created by blacksheep on 17/06/15.
 */
public abstract class AbstractDummyPojo implements StupidInterface {
    @Override
    public String test() {
        return "huhu";
    }
}
