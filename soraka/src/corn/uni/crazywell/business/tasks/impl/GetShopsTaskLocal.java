package corn.uni.crazywell.business.tasks.impl;

import corn.uni.crazywell.business.tasks.ReturnableTaskLocal;
import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.dto.converter.DTOConverterLocal;
import corn.uni.crazywell.common.dto.impl.ShopDTO;
import corn.uni.crazywell.common.exception.ConversionException;
import corn.uni.crazywell.common.exception.DAOException;
import corn.uni.crazywell.common.exception.TaskFailedException;
import corn.uni.crazywell.data.dao.GenericDAO;
import corn.uni.crazywell.data.entities.ShopEntity;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by blacksheep on 16/06/15.
 */
@Stateless
public class GetShopsTaskLocal implements ReturnableTaskLocal {


    private GenericDAO<ShopEntity> shopDAO;
    private DTOConverterLocal<ShopEntity, ShopDTO> shopConverter;

    @Override
    public List<? extends DTO> run() throws TaskFailedException {
        try {
            final List<ShopEntity> shops = shopDAO.findAll();
            final List<ShopDTO> outputList = new ArrayList<>();
            ShopDTO shopDTO;
            for(ShopEntity shop : shops){
                shopDTO = new ShopDTO();
                shopConverter.convert(shop, shopDTO);
                outputList.add(shopDTO);
            }
            return outputList;
        } catch (DAOException e) {
            e.printStackTrace();
            throw new TaskFailedException("CUSTOM - DAO failed to get the shops list");
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new TaskFailedException("CUSTOM - for some reason of shops conversion failed.");
        }
    }
}
