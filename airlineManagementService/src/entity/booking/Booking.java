package entity.booking;

import entity.aircraft.Flight;
import entity.seat.Seat;
import entity.user.Passenger;

public class Booking {
    private final Seat seat;
    private final String bookingID;
    private final Passenger passenger;
    private final Flight flight;
    private final double price;
    private BookingStatus status;

    public Booking(Seat seat, String bookingID, Passenger passenger, Flight flight, double price) {
        this.seat = seat;
        this.bookingID = bookingID;
        this.passenger = passenger;
        this.flight = flight;
        this.price = price;
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel(){
        this.status = BookingStatus.CANCELLED;
    }
    public String getBookingID(){
        return this.bookingID;
    }
}
