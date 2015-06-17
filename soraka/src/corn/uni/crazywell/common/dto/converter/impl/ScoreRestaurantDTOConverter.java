package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.RestaurantScoreDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.RestaurantScoreEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by blacksheep on 16/06/15.
 */
public class ScoreRestaurantDTOConverter implements DTOConverterLocal<RestaurantScoreEntity, RestaurantScoreDTO> {
    @Override
    public void convert(RestaurantScoreEntity source, RestaurantScoreDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
