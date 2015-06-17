package corn.uni.crazywell.business.tasks.impl;

import corn.uni.crazywell.business.tasks.ReturnableTask;
import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.RestaurantDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.common.exception.TaskFailedException;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.entities.RestaurantEntity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
public class GetRestaurantsTask implements ReturnableTask {
    @Inject private GenericDAO<RestaurantEntity> restaurantDAO;
    @Inject private DTOConverterLocal<RestaurantEntity, RestaurantDTO> restaurantConverter;

    @Override
    public List<? extends DTO> run() throws TaskFailedException {
        try {
            final List<RestaurantEntity> restaurants = restaurantDAO.findAll();
            final List<RestaurantDTO> outputList = new ArrayList<>();
            RestaurantDTO restaurantDTO;
            for(RestaurantEntity restaurant : restaurants){
                restaurantDTO = new RestaurantDTO();
                restaurantConverter.convert(restaurant, restaurantDTO);
                outputList.add(restaurantDTO);
            }
            return outputList;
        } catch (DAOException e) {
            e.printStackTrace();
            throw new TaskFailedException("CUSTOM - DAO failed to get the restaurant list");
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new TaskFailedException("CUSTOM - for some reason of restaurants conversion failed.");
        }
    }
}
