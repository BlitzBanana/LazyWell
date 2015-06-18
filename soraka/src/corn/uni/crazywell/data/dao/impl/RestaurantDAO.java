package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.RestaurantEntity;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class RestaurantDAO extends AbstractGenericDAO<RestaurantEntity> {
    public RestaurantDAO(){
        super(RestaurantEntity.class);
    }
}
