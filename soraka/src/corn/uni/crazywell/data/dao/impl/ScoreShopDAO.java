package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.ShopScoreEntity;

/**
 * Created by blacksheep on 16/06/15.
 */
public class ScoreShopDAO extends AbstractGenericDAO<ShopScoreEntity> {
    public ScoreShopDAO(){
        super(ShopScoreEntity.class);
    }
}
