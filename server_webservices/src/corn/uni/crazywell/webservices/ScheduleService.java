package corn.uni.crazywell.webservices;

import corn.uni.crazywell.services.CommunicationServiceLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by Thanith on 15/06/2015.
 */
@WebService(serviceName = "ScheduleService", name = "schedule")
@Stateless
public class ScheduleService {

    @EJB
    private CommunicationServiceLocal communicationService;

    @WebMethod(operationName = "paymentOperation")
    public boolean testService(@WebParam(name = "cardNumber")String
                                          ccNumber, @WebParam(name = "amountPaid") Long amount) {
        if (ccNumber.length() == 10) {

            return true;
        } else {
            return false;
        }
    }
}
