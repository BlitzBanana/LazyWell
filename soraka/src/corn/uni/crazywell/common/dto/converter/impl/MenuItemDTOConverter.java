package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.MenuItemDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.MenuItemEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by blacksheep on 16/06/15.
 */
public class MenuItemDTOConverter implements DTOConverterLocal<MenuItemEntity, MenuItemDTO> {
    @Override
    public void convert(MenuItemEntity source, MenuItemDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
