package corn.uni.crazywell.services;

import com.sun.xml.ws.developer.Stateful;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

/**
 * Created by Thanith on 15/06/2015.
 */
@Stateful
public class CommunicationService implements CommunicationServiceLocal{

    @Inject
    private JMSContext context;
    @Resource(lookup = "wellQueue")
    private Queue wellQueue;

    public boolean sendMessage(Message oMsg)
    {
        ObjectMessage msg = context.createObjectMessage(oMsg);

        context.createProducer().send(wellQueue, msg);
        return true;
    }

}
