package corn.uni.crazywell.webservices;

import corn.uni.crazywell.services.CommunicationServiceLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * Created by Thanith on 15/06/2015.
 */
@WebService(serviceName = "ScheduleService", name = "schedule")
@Stateless
public class ScheduleService {

    @EJB(beanName = "CommunicationService")
    private CommunicationServiceLocal communicationService;
}
