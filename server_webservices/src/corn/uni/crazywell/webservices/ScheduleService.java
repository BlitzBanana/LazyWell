package corn.uni.crazywell.webservices;

import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.dto.impl.ShowDTO;
import corn.uni.crazywell.services.CommunicationServiceLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanith on 15/06/2015.
 */
@WebService(serviceName = "ScheduleService", name = "schedule")
@Stateless
public class ScheduleService {

    @EJB
    private CommunicationServiceLocal communicationService;

    @WebMethod(operationName = "testWithoutResponse")
    public Boolean testWithoutResponse() {

        try {
            Bubble bubble = new Bubble();

            //bubble.addBodyElement("TagadaWithoutResponse1");
            communicationService.sendMessage(bubble);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

        @WebMethod(operationName = "testWithResponse")
    public List<ShowDTO> testWithResponse() {
        try {
            Bubble bubble = new Bubble();
            ShowDTO test = new ShowDTO();
            test.setId(1);
            ShowDTO test2 = new ShowDTO();
            test.setId(1);

            List<ShowDTO> list = new ArrayList<>();
            list.add(test);
            list.add(test2);

            return list;//communicationService.sendMessageWithResponse(bubble);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
