package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.ShopScoreDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.ShopScoreEntity;

/**
 * Created by blacksheep on 16/06/15.
 */
public class ScoreShopDTOConverter implements DTOConverterLocal<ShopScoreEntity, ShopScoreDTO> {
    @Override
    public void convert(ShopScoreEntity source, ShopScoreDTO target) throws ConversionException {

    }
}
