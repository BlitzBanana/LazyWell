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

@WebService(serviceName = "EvalService", name = "eval")
@Stateless
public class EvalService {

    @EJB
    private CommunicationServiceLocal communicationService;

    @WebMethod(operationName = "setEval")
    public void setEval(Bubble bubble) {

        try {
            bubble.setHeader(Bubble.Process.SET_EVAL);
            communicationService.sendMessage(bubble);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
