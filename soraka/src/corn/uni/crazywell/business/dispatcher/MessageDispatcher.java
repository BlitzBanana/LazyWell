package corn.uni.crazywell.business.dispatcher;

import corn.uni.crazywell.business.tasks.ReturnableTaskLocal;
import corn.uni.crazywell.business.tasks.UnreturnableTask;
import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.dto.DTO;
import corn.uni.crazywell.common.exception.TaskFailedException;
import corn.uni.crazywell.common.exception.TaskNotFoundException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.inject.Named;
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
    @Inject
    private @Named("getShowTaskLocal") ReturnableTaskLocal getShowTask;


    @Override
    public void onMessage(Message message) {
        try {
            //avec retour
            if(message.getJMSReplyTo() != null) {
                //reception
                Bubble bubble = message.getBody(Bubble.class);
                //Dispatching
                ReturnableTaskLocal task = dispatch(bubble.getHeader());
                //Traitement & retour
                List<? extends DTO> result  = task.run();
                //reponse
                sendResponse(message.getJMSReplyTo(), result);
            }
            else {
                //sans retour
                Bubble bubble = message.getBody(Bubble.class);
                UnreturnableTask task = dispatchNoReturn(bubble.getHeader());
                task.run();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        } catch (TaskFailedException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(Destination replyTo, List<? extends DTO> responses){
        Bubble respBubble = new Bubble();
        for(Object obj : responses){
            respBubble.getBody().add(obj);
        }
        ObjectMessage response = context.createObjectMessage(respBubble);
        context.createProducer().send(replyTo, response);
    }

    private ReturnableTaskLocal dispatch(Bubble.Process processToRelease) throws TaskNotFoundException {
        switch (processToRelease){
            case GET_SHOWS:
                return getShowTask;
        }
        throw new TaskNotFoundException("Aucune tache n'a été trouvée pour la Bubble fournie");
    }

    private UnreturnableTask dispatchNoReturn(Bubble.Process processToRelease) throws TaskNotFoundException{
        throw new TaskNotFoundException("Aucune tache n'a été trouvée pour la Bubble fournie");
    }
}
