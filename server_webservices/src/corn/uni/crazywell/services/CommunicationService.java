package corn.uni.crazywell.services;

import corn.uni.crazywell.common.Bubble;

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
    public void sendMessage(Bubble bubble)
    {
        ObjectMessage msg = context.createObjectMessage(bubble);

        context.createProducer().send(wellQueue, msg);
    }

    @Override
    //http://javaee.support/sample/jms-temp-destination/
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Bubble sendMessageWithResponse(Bubble bubble) {

        ObjectMessage msg = context.createObjectMessage(bubble);
        TemporaryQueue responseQueue = context.createTemporaryQueue();
        context.createProducer()
                .setJMSReplyTo(responseQueue)
                .send(wellQueue, msg);

        try (JMSConsumer consumer = context.createConsumer(responseQueue)) {

            Bubble response = consumer.receiveBody(Bubble.class, 5000);

            //System.out.println(response.getBody());

            if (response == null) {
                throw new IllegalStateException("CUSTOM - Message processing timed out");
            } else {
                return response;
            }
        }
    }
}
