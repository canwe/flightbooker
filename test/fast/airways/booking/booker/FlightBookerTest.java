package fast.airways.booking.booker;

import org.junit.*;
import org.easymock.*;
import fast.airways.booking.BookingRepository;
import fast.airways.booking.BookingNotMadeException;
import fast.airways.booking.FlightBooking;
import fast.airways.customer.*;
import fast.airways.flight.*;

/**
 * Created by IntelliJ IDEA.
 * User: afzal.shaikh
 * Date: 22-Jul-2010
 * Time: 15:40:27
 * To change this template use File | Settings | File Templates.
 */
public class FlightBookerTest {
    private FlightBooker flightBooker;
    private BookingRepository mockBookingRepository;
    private CustomerRepository mockCustomerRepository;
    private FlightRepository mockFlightRepository;
    private Long existingCustomerID = 1l;
    private Long existingFlightID = 2l;
    private Long nonexistingCustomerID = 3l;
    private Long nonexistingFlightID = 4l;


    @Before
    public void setUp() {
        mockBookingRepository = EasyMock.createMock(BookingRepository.class);
        mockCustomerRepository = EasyMock.createMock(CustomerRepository.class);
        mockFlightRepository = EasyMock.createMock(FlightRepository.class);
        flightBooker = new FlightBooker(mockBookingRepository, mockCustomerRepository, mockFlightRepository);
    }

    @Test
    public void testBookingDetailsIsPersistedInBookingRepository() throws CustomerNotFoundException, FlightNotFoundException, BookingNotMadeException, FlightBookingException {
//    - You need persist a flight booking in the BookingRepository from the given booking details
        BookingDetails bookingDetails = new BookingDetails(existingCustomerID, existingFlightID);
        Customer customer = new Customer();
        Flight flight = new Flight();

        EasyMock.expect(mockCustomerRepository.get(bookingDetails.getCustomerId())).andReturn(customer);
        EasyMock.expect(mockFlightRepository.get(bookingDetails.getFlightId())).andReturn(flight);
        mockBookingRepository.book(new FlightBooking(customer, flight));

        EasyMock.replay(mockBookingRepository, mockCustomerRepository, mockFlightRepository);
        flightBooker.bookFlight(bookingDetails);
        EasyMock.verify(mockBookingRepository, mockCustomerRepository, mockFlightRepository);

    }

//    @Test (expected = FlightBookingException.class) -- This is JUNIT Assertion to check that the test does throw FlightBookinException exception
    @Test
    public void testThrowInvalidCustomerWhenCustomerCannotBeFound() throws CustomerNotFoundException {
//   - If the customer can not be found you need to report an INVALID_CUSTOMER to the caller
        Exception returnedException = new Exception();
        BookingDetails bookingDetails = new BookingDetails(nonexistingCustomerID, existingFlightID);
        EasyMock.expect(mockCustomerRepository.get(bookingDetails.getCustomerId())).andThrow(new CustomerNotFoundException());
        try {
            EasyMock.replay(mockCustomerRepository);
            flightBooker.bookFlight(bookingDetails);
            EasyMock.verify(mockCustomerRepository);
        }catch (FlightBookingException e) {
            returnedException = e;
            Assert.assertEquals(BookingProblem.INVALID_CUSTOMER, e.getBookingProblem());
        }finally{
            Assert.assertTrue(returnedException instanceof FlightBookingException);
        }
    }

    @Test
    public void testThrowInvalidFlightWhenFlightCannotBeFound() throws CustomerNotFoundException, FlightNotFoundException{
//        - If the flight can not be found you need to report an INVALID_FLIGHT to the caller
        Exception returnedException = new Exception();
        BookingDetails bookingDetails = new BookingDetails(existingCustomerID, nonexistingFlightID);
        EasyMock.expect(mockCustomerRepository.get(bookingDetails.getCustomerId())).andReturn(new Customer());
        EasyMock.expect(mockFlightRepository.get(bookingDetails.getFlightId())).andThrow(new FlightNotFoundException());

        try {
            EasyMock.replay(mockCustomerRepository, mockFlightRepository);
            flightBooker.bookFlight(bookingDetails);
            EasyMock.verify(mockCustomerRepository, mockFlightRepository);
        }catch (FlightBookingException e) {
            returnedException = e;
            Assert.assertEquals(BookingProblem.INVALID_FLIGHT, e.getBookingProblem());
        }finally{
            Assert.assertTrue(returnedException instanceof FlightBookingException);
        }
    }

    @Test
    public void testThrowBookingNotSavedWhenBookingCannotBePersisted() throws CustomerNotFoundException, FlightNotFoundException, BookingNotMadeException {
//        - If the booking can not be persisted you need to report a BOOKING_NOT_SAVED to the caller
        Exception returnedException = new Exception();
        Customer customer = new Customer();
        Flight flight = new Flight();
        BookingDetails bookingDetails = new BookingDetails(existingCustomerID, existingFlightID);
        EasyMock.expect(mockCustomerRepository.get(bookingDetails.getCustomerId())).andReturn(customer);
        EasyMock.expect(mockFlightRepository.get(bookingDetails.getFlightId())).andReturn(flight);
        mockBookingRepository.book(new FlightBooking(customer,flight));
        EasyMock.expectLastCall().andThrow(new BookingNotMadeException());

        try {
            EasyMock.replay(mockCustomerRepository, mockFlightRepository,mockBookingRepository);
            flightBooker.bookFlight(bookingDetails);
            EasyMock.verify(mockCustomerRepository, mockFlightRepository,mockBookingRepository);
        }catch (FlightBookingException e) {
            returnedException = e;
            Assert.assertEquals(BookingProblem.BOOKING_NOT_SAVED, e.getBookingProblem());
        }finally{
            Assert.assertTrue(returnedException instanceof FlightBookingException);
        }
    }

}