package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.MenuItemScoreDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.MenuItemScoreEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by blacksheep on 16/06/15.
 */
public class ScoreMenuItemDTOConverter implements DTOConverterLocal<MenuItemScoreEntity, MenuItemScoreDTO> {
    @Override
    public void convert(MenuItemScoreEntity source, MenuItemScoreDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
