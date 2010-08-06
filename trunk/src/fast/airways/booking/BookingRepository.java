package fast.airways.booking;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Bradley.Hart
 * Date: 17-Jul-2010
 * Time: 18:01:53
 * To change this template use File | Settings | File Templates.
 */
public interface BookingRepository {
    void book(FlightBooking booking) throws BookingNotMadeException;
    List<FlightBooking> getBookingList();
}
