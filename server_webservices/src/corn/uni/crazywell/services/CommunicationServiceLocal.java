package corn.uni.crazywell.services;

import javax.ejb.Local;

/**
 * Created by Thanith on 15/06/2015.
 */
@Local
public interface CommunicationServiceLocal {
    void sendMessage(Bubble bubble);
    Bubble sendMessageWithResponse(Bubble bubble);
}
