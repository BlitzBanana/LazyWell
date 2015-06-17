package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.MenuItemScoreEntity;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class ScoreMenuItemDao extends AbstractGenericDAO<MenuItemScoreEntity> {
    public ScoreMenuItemDao(){
        super(MenuItemScoreEntity.class);
    }
}
