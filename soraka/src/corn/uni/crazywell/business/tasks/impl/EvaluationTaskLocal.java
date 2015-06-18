package corn.uni.crazywell.business.tasks.impl;

import corn.uni.crazywell.business.tasks.UnreturnableTask;
import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.RestaurantScoreEntity;
import corn.uni.crazywell.data.entities.ShopScoreEntity;
import corn.uni.crazywell.data.entities.ShowScoreEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class EvaluationTaskLocal implements UnreturnableTask {
    @Inject
    AbstractGenericDAO<ShowScoreEntity> scoreShowDao;
    @Inject
    AbstractGenericDAO<ShopScoreEntity> scoreShopDao;
    @Inject
    AbstractGenericDAO<RestaurantScoreEntity> scoreRestaurantDao;

    @Override
    public void run(Bubble bubble) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        try {
            switch ((Bubble.Eval) bubble.getBody().get(0)) {

                case SHOW_EVAL:
                    ShowScoreEntity showScore = new ShowScoreEntity();
                    showScore.setValue((int) bubble.getBody().get(3));
                    showScore.setDate(Integer.parseInt(dateFormat.format(date)));
                    showScore.setUuid((String) bubble.getBody().get(1));
                    showScore.setShowId((int) bubble.getBody().get(2));
                    scoreShowDao.persist(showScore);
                    break;
                case SHOP_EVAL:
                    ShopScoreEntity shopScore = new ShopScoreEntity();
                    shopScore.setValue((int)bubble.getBody().get(3));
                    shopScore.setDate(Integer.parseInt(dateFormat.format(date)));
                    shopScore.setUuid((String)bubble.getBody().get(1));
                    shopScore.setShopId((int) bubble.getBody().get(2));
                    scoreShopDao.persist(shopScore);
                    break;
                case RESTAURANT_EVAL:
                    RestaurantScoreEntity restaurantScore = new RestaurantScoreEntity();
                    restaurantScore.setValue((int)bubble.getBody().get(3));
                    restaurantScore.setDate(Integer.parseInt(dateFormat.format(date)));
                    restaurantScore.setUuid((String)bubble.getBody().get(1));
                    restaurantScore.setRestaurantId((int) bubble.getBody().get(2));
                    scoreRestaurantDao.persist(restaurantScore);
                    break;
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
