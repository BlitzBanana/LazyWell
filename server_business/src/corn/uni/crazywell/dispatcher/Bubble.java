package corn.uni.crazywell.dispatcher;

import java.io.Serializable;

/**
 * Created by Thanith on 16/06/2015.
 */
public class Bubble implements Serializable {
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    private String body;
}
