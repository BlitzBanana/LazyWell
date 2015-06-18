package corn.uni.crazywell.business.tasks.impl;

import corn.uni.crazywell.business.dispatcher.StupidInterface;
import corn.uni.crazywell.business.tasks.ReturnableTaskLocal;
import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.ShowDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.common.exception.TaskFailedException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.entities.ShowEntity;

import javax.ejb.EJB;
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
public class GetShowTaskLocal implements ReturnableTaskLocal {

    @EJB
    private StupidInterface dummyPojo;
    private GenericDAO<ShowEntity> showDao;
    @Inject private DTOConverterLocal<ShowEntity, ShowDTO> showDTOConverter;

    public GetShowTaskLocal(){
        //Do nothing
    }

    @Inject
    public GetShowTaskLocal(GenericDAO<ShowEntity> showDao){
        if(showDao!=null)
        this.showDao = showDao;
    }

    @Override
    public List<? extends DTO> run(Bubble bubble) throws TaskFailedException {
        try {
            List<ShowEntity> showsList = showDao.findAll();
            final List<ShowDTO> outputList = new ArrayList<>();
            ShowDTO showDTO;

            for (ShowEntity show : showsList){
                showDTO = new ShowDTO();
                showDTOConverter.convert(show, showDTO);
                outputList.add(showDTO);
            }
            return outputList;
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new TaskFailedException("CUSTOM - The getShowTask result in a fail");
        } catch (DAOException e) {
            e.printStackTrace();
            throw new TaskFailedException("CUSTOM - Fail to get shows from DB");
        }
    }

    public void setShowDao(AbstractGenericDAO<ShowEntity> showDao) {
        this.showDao = showDao;
    }

    public void setShowDTOConverter(DTOConverterLocal<ShowEntity, ShowDTO> showDTOConverter) {
        this.showDTOConverter = showDTOConverter;
    }
}
