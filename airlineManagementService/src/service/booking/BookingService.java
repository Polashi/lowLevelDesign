package service.booking;

import entity.aircraft.Flight;
import entity.booking.Booking;
import entity.seat.Seat;
import entity.user.Passenger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingService {
    private static BookingService instance;
    private final Map<String, Booking> bookingRecord;
    private final AtomicInteger bookingCounter = new AtomicInteger(0);
    private final Object lock = new Object();

    private BookingService(){
        bookingRecord = new HashMap<>();
    }

    public static synchronized BookingService getInstance(){
        if(instance == null){
            return new BookingService();
        }
        return instance;
    }
    public Booking createBooking(Flight flight, Passenger passenger, Seat seat, double price) {
        String bookingID = generateBookingNumber();
        Booking booking = new Booking(seat, bookingID, passenger, flight, price);
        synchronized (lock){
            bookingRecord.put(bookingID, booking);
        }
        return booking;
    }

    private String generateBookingNumber(){
        int id = bookingCounter.incrementAndGet();
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
        return "ABC" + timeStamp + String.format("%06d", id);
    }

    public void cancelBooking(String bookingId){
        synchronized (lock){
            Booking booking = bookingRecord.get(bookingId);
            if(booking != null){
                booking.cancel();
            }
        }
    }
}
