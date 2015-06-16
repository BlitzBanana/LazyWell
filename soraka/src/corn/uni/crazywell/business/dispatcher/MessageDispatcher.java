package corn.uni.crazywell.business.dispatcher;

import corn.uni.crazywell.business.tasks.ReturnableTask;
import corn.uni.crazywell.business.tasks.impl.GetShowTask;
import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.exception.TaskFailedException;
import corn.uni.crazywell.common.exception.TaskNotFoundException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;
import java.util.List;

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

                //Réaliser le traitement ici
                ReturnableTask task = dispatch(bubble.getHeader());
                List<? extends DTO> result  = task.run();


                Destination replyTo = message.getJMSReplyTo();
                Bubble respBubble = new Bubble();
                respBubble.addBodyElement(result);
                ObjectMessage response = context.createObjectMessage(respBubble);
                context.createProducer().send(replyTo, response);
            }
            else {
                Bubble bubble = message.getBody(Bubble.class);

                System.out.println(bubble.getBody());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (TaskNotFoundException e) {
            //TODO A GERER!!
            e.printStackTrace();
        } catch (TaskFailedException e) {
            e.printStackTrace();
        }
    }

    private ReturnableTask dispatch(Bubble.Process processToRelease) throws TaskNotFoundException {
        switch (processToRelease){
            case GET_SHOWS:
                return new GetShowTask();
            case process2:
                return null;
            case process3:
                return null;
        }
        throw new TaskNotFoundException("Aucune tache n'a été trouvée pour la Bubble fournie");
    }
}