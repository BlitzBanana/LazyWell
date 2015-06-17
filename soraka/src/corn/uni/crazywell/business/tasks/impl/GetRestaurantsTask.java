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

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named("restaurantTask")
@Stateless
public class GetRestaurantsTask implements ReturnableTask {
    private GenericDAO<RestaurantEntity> restaurantDAO;
    private DTOConverterLocal<RestaurantEntity, RestaurantDTO> restaurantConverter;

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
