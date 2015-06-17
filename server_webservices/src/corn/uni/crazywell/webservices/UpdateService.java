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

@WebService(serviceName = "UpdateService", name = "update")
@Stateless
public class UpdateService {

    @EJB
    private CommunicationServiceLocal communicationService;

    @WebMethod(operationName = "checkUpdate")
    public Bubble checkUpdate(Bubble bubble) {
        try {

            bubble.setHeader(Bubble.Process.CHECK_UPDATE);
            return communicationService.sendMessageWithResponse(bubble);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
