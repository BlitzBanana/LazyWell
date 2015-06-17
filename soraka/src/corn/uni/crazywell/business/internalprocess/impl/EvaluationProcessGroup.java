package corn.uni.crazywell.business.internalprocess.impl;

import corn.uni.crazywell.business.internalprocess.InternalProcess;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.dao.impl.ScoreMenuItemDao;
import corn.uni.crazywell.data.dao.impl.ScoreRestaurantDao;
import corn.uni.crazywell.data.dao.impl.ScoreShopDao;
import corn.uni.crazywell.data.dao.impl.ScoreShowDao;
import corn.uni.crazywell.data.entities.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class EvaluationProcessGroup implements InternalProcess {
    @Inject
    AbstractGenericDAO<MenuItemScoreEntity> scoreMenuItemDao;
    @Inject
    AbstractGenericDAO<ShowScoreEntity> scoreShowDao;
    @Inject
    AbstractGenericDAO<ShopScoreEntity> scoreShopDao;
    @Inject
    AbstractGenericDAO<RestaurantScoreEntity> scoreRestaurantDao;

    public List<MenuItemScoreEntity> getScoreInMenu(final MenuItemEntity menuItemEntity){
        throw new NotImplementedException();
    }

    public ShopScoreEntity getShopScore(final ShopEntity shopEntity){
        throw new NotImplementedException();
    }

    public RestaurantScoreEntity getScoreRestaurant(final RestaurantEntity restaurantEntity){
        throw new NotImplementedException();
    }

    public ShowScoreEntity getScoreShow(final ShowEntity showEntity){
        throw new NotImplementedException();
    }
}
