package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.MenuItemEntity;

/**
 * Created by blacksheep on 16/06/15.
 */
public class MenuItemDAO extends AbstractGenericDAO<MenuItemEntity> {
    public MenuItemDAO(){
        super(MenuItemEntity.class);
    }
}
