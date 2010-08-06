package fast.airways.flight;

import fast.airways.flight.Flight;

/**
 * Created by IntelliJ IDEA.
 * User: Bradley.Hart
 * Date: 17-Jul-2010
 * Time: 17:57:52
 * To change this template use File | Settings | File Templates.
 */
public interface FlightRepository {
    Flight get(long flightId) throws FlightNotFoundException;
}
