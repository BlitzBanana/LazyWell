package corn.uni.crazywell.webservices;

import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.services.CommunicationServiceLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Thanith on 16/06/2015.
 */

@WebService(serviceName = "ShopService", name = "shop")
@Stateless
public class ShopService {

    @EJB
    private CommunicationServiceLocal communicationService;

    @WebMethod(operationName = "getShops")
    public List<Object> getShops(Bubble bubble) {
        try {
            Bubble response = communicationService.sendMessageWithResponse(bubble);

            return null;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
