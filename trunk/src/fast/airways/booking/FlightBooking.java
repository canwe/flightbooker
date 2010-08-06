package fast.airways.booking;

import fast.airways.customer.Customer;
import fast.airways.flight.Flight;

/**
 * Created by IntelliJ IDEA.
 * User: Bradley.Hart
 * Date: 17-Jul-2010
 * Time: 18:02:34
 * To change this template use File | Settings | File Templates.
 */
public class FlightBooking {
    private Customer customer;
    private Flight flight;

    public FlightBooking(Customer customer, Flight flight){

        this.customer = customer;
        this.flight = flight;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightBooking that = (FlightBooking) o;

        if (!customer.equals(that.customer)) return false;
        if (!flight.equals(that.flight)) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = customer.hashCode();
        result = 31 * result + flight.hashCode();
        return result;
    }
}
