package fast.airways.booking.booker;

import fast.airways.booking.*;
import fast.airways.flight.*;
import fast.airways.customer.*;

/*
    Your job is to implement this class.
    Requirements:
    - You need persist a flight booking in the BookingRepository from the given booking details
    - If the customer can not be found you need to report an INVALID_CUSTOMER to the caller
    - If the flight can not be found you need to report an INVALID_FLIGHT to the caller
    - If the booking can not be persisted you need to report a BOOKING_NOT_SAVED to the caller
 */
public class FlightBooker {
    private BookingRepository bookingRepository;
    private CustomerRepository customerRepository;
    private FlightRepository flightRepository;

    public FlightBooker(BookingRepository bookingRepository, CustomerRepository customerRepository, FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.flightRepository = flightRepository;
    }

    public void bookFlight(BookingDetails bookingDetails) throws FlightBookingException {
        try {
            Customer customer = customerRepository.get(bookingDetails.getCustomerId());
            Flight flight = flightRepository.get(bookingDetails.getFlightId());
            FlightBooking flightBooking = new FlightBooking(customer, flight);
            bookingRepository.book(flightBooking);
        } catch (CustomerNotFoundException e) {
            throw new FlightBookingException(BookingProblem.INVALID_CUSTOMER);
        } catch (FlightNotFoundException e) {
            throw new FlightBookingException(BookingProblem.INVALID_FLIGHT);
        } catch (BookingNotMadeException e) {
            throw new FlightBookingException(BookingProblem.BOOKING_NOT_SAVED);
        }
    }
}
