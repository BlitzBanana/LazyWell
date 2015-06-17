package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.RestaurantDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.RestaurantEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by blacksheep on 16/06/15.
 */
public class RestaurantDTOConverter implements DTOConverterLocal<RestaurantEntity, RestaurantDTO> {
    @Override
    public void convert(RestaurantEntity source, RestaurantDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
