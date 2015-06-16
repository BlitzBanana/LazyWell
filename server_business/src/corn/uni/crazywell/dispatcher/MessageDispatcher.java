package corn.uni.crazywell.dispatcher;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;

/**
 * Created by Thanith on 16/06/2015.
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue =
                "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue =
                "wellQueue")
})
public class MessageDispatcher implements MessageListener {

    @Inject
    private JMSContext context;

    @Override
    public void onMessage(Message message) {
        try {
            if(message.getJMSReplyTo() != null) {
                Bubble bubble = message.getBody(Bubble.class);

                System.out.println(bubble.getBody());


                Destination replyTo = message.getJMSReplyTo();
                Bubble respBubble = new Bubble();
                respBubble.setBody("TagadaResp");
                ObjectMessage response = context.createObjectMessage(respBubble);
                context.createProducer().send(replyTo, response);
            }
            else {
                Bubble bubble = message.getBody(Bubble.class);

                System.out.println(bubble.getBody());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
