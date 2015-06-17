package corn.uni.crazywell.data.dao.impl;

import corn.uni.crazywell.common.dto.impl.ShowDTO;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.entities.ShopEntity;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class ShopDao extends AbstractGenericDAO<ShopEntity>{
    public ShopDao(){
        super(ShopEntity.class);
    }
}
