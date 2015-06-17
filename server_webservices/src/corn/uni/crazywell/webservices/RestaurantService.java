package corn.uni.crazywell.webservices;

import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.services.CommunicationServiceLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Thanith on 16/06/2015.
 */

@WebService(serviceName = "RestaurantService", name = "restaurant")
@Stateless
public class RestaurantService {

    @EJB
    private CommunicationServiceLocal communicationService;

    @WebMethod(operationName = "getRestaurants")
    public Bubble getRestaurants(Bubble bubble) {
        try {
            bubble.setHeader(Bubble.Process.GET_RESTAURANTS);
            return communicationService.sendMessageWithResponse(bubble);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
