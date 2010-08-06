package fast.airways.booking.booker;

/**
 * Created by IntelliJ IDEA.
 * User: Bradley.Hart
 * Date: 17-Jul-2010
 * Time: 17:56:35
 * To change this template use File | Settings | File Templates.
 */
public class BookingDetails {

    private long flightId;
    private long customerId;
    
    public BookingDetails(long customerId,long flightId){
        this.flightId = flightId;
        this.customerId = customerId;
    }
    public long getFlightId() {
        return flightId;
    }

    public long getCustomerId() {
        return customerId;
    }

}