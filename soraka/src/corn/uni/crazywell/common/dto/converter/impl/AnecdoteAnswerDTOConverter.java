package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.AnecdoteAnswerDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.AnecdoteAnswerEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class AnecdoteAnswerDTOConverter implements DTOConverterLocal<AnecdoteAnswerEntity, AnecdoteAnswerDTO> {
    @Override
    public void convert(AnecdoteAnswerEntity source, AnecdoteAnswerDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
