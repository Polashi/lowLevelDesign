import entity.aircraft.Aircraft;
import entity.aircraft.Flight;
import entity.booking.Booking;
import entity.payment.Payment;
import entity.seat.Seat;
import entity.user.Passenger;
import service.aircraft.FlightService;
import service.booking.BookingService;
import service.payment.PaymentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AirlineManagementSystem {
    private final List<Flight> flightList;
    private final List<Aircraft> aircraftList;
    private final FlightService flightService;
    private final BookingService bookingService;
    private final PaymentService paymentService;

    public AirlineManagementSystem() {
        this.flightList = new ArrayList<>();
        this.aircraftList = new ArrayList<>();
        this.flightService = new FlightService(flightList);
        this.bookingService = BookingService.getInstance();
        this.paymentService = PaymentService.getInstance();
    }

    public void addFlight(Flight flight){
        flightList.add(flight);
    }

    public void addAircraft(Aircraft aircraft){
        aircraftList.add(aircraft);
    }

    public List<Flight> searchFlights(String source, String destination, LocalDate date){
        return flightService.getFlightOptions(source, destination, date);
    }

    public Booking bookFlight(Flight flight, Passenger passenger, Seat seat, double price){
        return bookingService.createBooking(flight, passenger, seat, price);
    }

    public void cancelBooking(String bookingId){
        bookingService.cancelBooking(bookingId);
    }

    public void processPayment(Payment payment){
        paymentService.processPayment(payment);
    }
}
