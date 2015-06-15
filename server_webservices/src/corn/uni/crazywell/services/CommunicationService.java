package corn.uni.crazywell.services;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.*;
import java.lang.IllegalStateException;

/**
 * Created by Thanith on 15/06/2015.
 */
@Stateless
public class CommunicationService implements CommunicationServiceLocal{

    @Inject
    private JMSContext context;
    @Resource(lookup = "wellQueue")
    private Queue wellQueue;

    @Override
    public Message sendMessage(Message oMsg)
    {
        ObjectMessage msg = context.createObjectMessage(oMsg);

        context.createProducer().send(wellQueue, msg);
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Message sendMessageWithResponse(Message oMsg) {

        ObjectMessage msg = context.createObjectMessage(oMsg);
        TemporaryQueue responseQueue = context.createTemporaryQueue();
        context.createProducer()
                .setJMSReplyTo(responseQueue)
                .send(wellQueue, msg);

        try (JMSConsumer consumer = context.createConsumer(responseQueue)) {

            String response = consumer.receiveBody(String.class, 2000);

            if (response == null) {
                throw new IllegalStateException("Message processing timed out");
            } else {
                return null;
            }
        }
    }
}
