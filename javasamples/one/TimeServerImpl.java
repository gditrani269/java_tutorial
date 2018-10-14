package javasamples.one; 
import java.util.Date;
import javax.jws.WebService;

/**
 *  The @WebService property endpointInterface links the
 *  SIB (this class) to the SEI (javasamples.one.TimeServer).
 *  Note that the method implementations are not annotated
 *  as @WebMethods.
*/

@WebService(endpointInterface = "javasamples.one.TimeServer")

public class TimeServerImpl implements TimeServer {

    public String getTimeAsString() { return new Date().toString(); }
    public long getTimeAsElapsed() { return new Date().getTime(); }

}