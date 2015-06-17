package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.RestaurantScoreEntity;

/**
 * Created by blacksheep on 16/06/15.
 */
public class ScoreRestaurantDAO extends AbstractGenericDAO<RestaurantScoreEntity> {
    public ScoreRestaurantDAO(){
        super(RestaurantScoreEntity.class);
    }
}
