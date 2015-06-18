package corn.uni.crazywell.webservices;

import corn.uni.crazywell.common.Bubble;
import corn.uni.crazywell.common.dto.impl.AnecdoteQuestionDTO;
import corn.uni.crazywell.common.dto.impl.ShowDTO;
import corn.uni.crazywell.services.CommunicationServiceLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanith on 16/06/2015.
 */

@WebService(serviceName = "ShowService", name = "show")
@Stateless
public class ShowService {

    @EJB
    private CommunicationServiceLocal communicationService;

    @WebMethod(operationName = "getShows")
    public List<ShowDTO> getShows() {
        try {
            Bubble bubble = new Bubble();

            bubble.setHeader(Bubble.Process.GET_SHOWS);
            Bubble response = communicationService.sendMessageWithResponse(bubble);

            List<ShowDTO> respList = new ArrayList<>();

            for(Object show : response.getBody()){
                respList.add((ShowDTO)show);
            }

            return respList;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @WebMethod(operationName = "getBestSchedule")
    public List<ShowDTO> getBestSchedule(Bubble bubble) {
        try {

            bubble.setHeader(Bubble.Process.GET_BEST_SCHEDULE);
            Bubble response = communicationService.sendMessageWithResponse(bubble);

            List<ShowDTO> respList = new ArrayList<>();

            for(Object show : response.getBody()){
                respList.add((ShowDTO)show);
            }

            return respList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @WebMethod(operationName = "getAnecdotes")
    public List<AnecdoteQuestionDTO> getAnecdotes(Bubble bubble) {
        try {

            bubble.setHeader(Bubble.Process.GET_ANECDOTES);
            Bubble response = communicationService.sendMessageWithResponse(bubble);

            List<AnecdoteQuestionDTO> respList = new ArrayList<>();

            for(Object show : response.getBody()){
                respList.add((AnecdoteQuestionDTO)show);
            }

            return respList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
