package fast.airways.booking.booker;

import fast.airways.booking.booker.BookingProblem;

/**
 * Created by IntelliJ IDEA.
 * User: Bradley.Hart
 * Date: 17-Jul-2010
 * Time: 18:29:02
 * To change this template use File | Settings | File Templates.
 */
public class FlightBookingException extends Exception {
    private BookingProblem problem;

    FlightBookingException(BookingProblem problem){
        this.problem = problem;
    }

    public BookingProblem getBookingProblem(){
        return problem;
    }


}
