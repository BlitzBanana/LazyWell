package corn.uni.crazywell.webservices;

import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.services.CommunicationServiceLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Thanith on 15/06/2015.
 */
@WebService(serviceName = "ScheduleService", name = "schedule")
@Stateless
public class ScheduleService {

    @EJB
    private CommunicationServiceLocal communicationService;

    @WebMethod(operationName = "testWithoutResponse")
    public boolean testWithoutResponse() {

        try {
            Bubble bubble = new Bubble();
            bubble.getBody().add("TagadaWithoutResponse2");
            communicationService.sendMessage(bubble);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @WebMethod(operationName = "testWithResponse")
    public Bubble testWithResponse() {
        try {
            Bubble bubble = new Bubble();
            bubble.getBody().add("TagadaWithResponse2");

            return communicationService.sendMessageWithResponse(bubble);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
