package corn.uni.crazywell.common;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Thanith on 16/06/2015.
 */
public class Bubble implements Serializable {
    private Process header;
    private Object[] body;
    private String error;

    public Bubble()
    {
        body = new Object[0];
    }

    public void addBodyElement( Object obj )
    {
        Object[] bodyTemp = new Object[body.length + 1];
        int i = 0;

        if(body.length != 0)
        {
            for(Object temp : body)
            {
                bodyTemp[i] = temp;
                i++;
            }
        }

        bodyTemp[i] = obj;

        body = bodyTemp;
    }


    public Object[] getBody() {
        return body;
    }

    public void setBody(Object[] body) {
        this.body = body;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Process getHeader() {
        return header;
    }

    public void setHeader(Process header) {
        this.header = header;
    }

    public enum Process {
        process1,
        process2,
        process3,
        GET_SHOWS
    }
}