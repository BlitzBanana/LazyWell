package corn.uni.crazywell.business.tasks.impl;

import corn.uni.crazywell.business.tasks.ReturnableTaskLocal;
import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.ShopDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.common.exception.TaskFailedException;
import corn.uni.crazywell.data.dao.AbstractGenericDAO;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.entities.ShopEntity;

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
public class GetShopsTaskLocal implements ReturnableTaskLocal {

    private GenericDAO<ShopEntity> shopDao;
    @Inject private DTOConverterLocal<ShopEntity, ShopDTO> shopDTOConverter;

    public GetShopsTaskLocal(){
        //Do nothing
    }

    @Inject
    public GetShopsTaskLocal(GenericDAO<ShopEntity> shopDao){
        if(shopDao!=null)
            this.shopDao = shopDao;
    }

    @Override
    public List<? extends DTO> run(Bubble bubble) throws TaskFailedException {
        try {
            List<ShopEntity> shopsList = shopDao.findAll();
            final List<ShopDTO> outputList = new ArrayList<>();
            ShopDTO shopDTO;

            for (ShopEntity shop : shopsList){
                shopDTO = new ShopDTO();
                shopDTOConverter.convert(shop, shopDTO);
                outputList.add(shopDTO);
            }
            return outputList;
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new TaskFailedException("CUSTOM - The getShopTask result in a fail");
        } catch (DAOException e) {
            e.printStackTrace();
            throw new TaskFailedException("CUSTOM - Fail to get shops from DB");
        }
    }

    public void setShopDao(AbstractGenericDAO<ShopEntity> shopDao) {
        this.shopDao = shopDao;
    }

    public void setShopDTOConverter(DTOConverterLocal<ShopEntity, ShopDTO> shopDTOConverter) {
        this.shopDTOConverter = shopDTOConverter;
    }
}
