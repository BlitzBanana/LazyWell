package corn.uni.crazywell.business.tasks.impl;

import corn.uni.crazywell.business.tasks.ReturnableTaskLocal;
import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.RestaurantDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.common.exception.TaskFailedException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.entities.RestaurantEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class GetRestaurantsTaskLocal implements ReturnableTaskLocal {

    private GenericDAO<RestaurantEntity> restaurantDao;
    @Inject private DTOConverterLocal<RestaurantEntity, RestaurantDTO> restaurantDTOConverter;

    public GetRestaurantsTaskLocal(){
        //Do nothing
    }

    @Inject
    public GetRestaurantsTaskLocal(GenericDAO<RestaurantEntity> restaurantDao){
        if(restaurantDao!=null)
            this.restaurantDao = restaurantDao;
    }

    @Override
    public List<? extends DTO> run(Bubble bubble) throws TaskFailedException {
        try {
            List<RestaurantEntity> restaurantsList = restaurantDao.findAll();
            final List<RestaurantDTO> outputList = new ArrayList<>();
            RestaurantDTO restaurantDTO;

            for (RestaurantEntity restaurant : restaurantsList){
                restaurantDTO = new RestaurantDTO();
                restaurantDTOConverter.convert(restaurant, restaurantDTO);
                outputList.add(restaurantDTO);
            }
            return outputList;
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new TaskFailedException("CUSTOM - The getRestaurantTask result in a fail");
        } catch (DAOException e) {
            e.printStackTrace();
            throw new TaskFailedException("CUSTOM - Fail to get restaurants from DB");
        }
    }

    public void setRestaurantDao(AbstractGenericDAO<RestaurantEntity> restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    public void setRestaurantDTOConverter(DTOConverterLocal<RestaurantEntity, RestaurantDTO> restaurantDTOConverter) {
        this.restaurantDTOConverter = restaurantDTOConverter;
    }
}
