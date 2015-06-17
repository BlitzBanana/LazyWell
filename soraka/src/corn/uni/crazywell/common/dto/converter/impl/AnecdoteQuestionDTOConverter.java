package corn.uni.crazywell.common.dto.converter.impl;

import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.AnecdoteQuestionDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.data.entities.AnecdoteQuestionEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class AnecdoteQuestionDTOConverter implements DTOConverterLocal<AnecdoteQuestionEntity, AnecdoteQuestionDTO> {
    @Override
    public void convert(AnecdoteQuestionEntity source, AnecdoteQuestionDTO target) throws ConversionException {
        throw new NotImplementedException();
    }
}
