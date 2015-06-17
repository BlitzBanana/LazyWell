package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.RestaurantEntity;

/**
 * Created by blacksheep on 16/06/15.
 */
public class RestaurantDAO extends AbstractGenericDAO<RestaurantEntity> {
    public RestaurantDAO(){
        super(RestaurantEntity.class);
    }
}
