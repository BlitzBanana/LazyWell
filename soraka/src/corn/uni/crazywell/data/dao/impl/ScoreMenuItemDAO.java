package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.MenuItemScoreEntity;

/**
 * Created by blacksheep on 16/06/15.
 */
public class ScoreMenuItemDAO extends AbstractGenericDAO<MenuItemScoreEntity> {
    public ScoreMenuItemDAO(){
        super(MenuItemScoreEntity.class);
    }
}
