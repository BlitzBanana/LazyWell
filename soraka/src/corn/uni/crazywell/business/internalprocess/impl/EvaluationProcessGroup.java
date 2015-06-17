package corn.uni.crazywell.business.internalprocess.impl;

import corn.uni.crazywell.business.internalprocess.InternalProcess;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.entities.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
public class EvaluationProcessGroup implements InternalProcess {
    @Inject GenericDAO<MenuItemScoreEntity> menuItemScoreDAO;
    @Inject GenericDAO<ShowScoreEntity> showScoreDAO;
    @Inject GenericDAO<ShopScoreEntity> shopScoreDAO;
    @Inject GenericDAO<RestaurantEntity> restaurantScoreDAO;

    public List<MenuItemScoreEntity> getScoreInMenu(MenuItemEntity menuItemEntity){
        throw new NotImplementedException();
    }

    public ShopScoreEntity getShopScore(ShopEntity shopEntity){
        throw new NotImplementedException();
    }

    public RestaurantScoreEntity getScoreRestaurant(RestaurantEntity restaurantEntity){
        throw new NotImplementedException();
    }

    public ShowScoreEntity getScoreShow(ShowEntity showEntity){
        throw new NotImplementedException();
    }
}
