package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.ShopDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.ShopEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by blacksheep on 16/06/15.
 */
public class ShopDTOConverter implements DTOConverterLocal<ShopEntity, ShopDTO> {
    @Override
    public void convert(final ShopEntity source, final ShopDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
