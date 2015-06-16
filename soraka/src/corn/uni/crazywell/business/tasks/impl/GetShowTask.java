package corn.uni.crazywell.business.tasks.impl;

import corn.uni.crazywell.business.tasks.ReturnableTask;
import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.ShowDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.common.exception.TaskFailedException;
import corn.uni.crazywell.data.dao.impl.ShowDAO;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Named
@Stateless
public class GetShowTask implements ReturnableTask {
    @Inject private ShowDAO showDAO;
    @Inject private DTOConverterLocal<ShowEntity, ShowDTO> showConverter;

    @Override
    public List<? extends DTO> run() throws TaskFailedException {
        final List<ShowEntity> showsList = showDAO.findAll();
        final List<ShowDTO> outputList = new ArrayList<>();
        ShowDTO showDTO;
        try {
            for (ShowEntity show : showsList){
                showDTO = new ShowDTO();
                showConverter.convert(show, showDTO);
                outputList.add(showDTO);
            }
            return outputList;
        } catch (ConversionException e) {
            throw new TaskFailedException("The getShowTask result in a fail");
        }
    }
}
