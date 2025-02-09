import entity.aircraft.Aircraft;
import entity.aircraft.Flight;
import entity.booking.Booking;
import entity.seat.Seat;
import entity.seat.SeatType;
import entity.user.Gender;
import entity.user.Passenger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class AirlineManagementSystemApplication {
    public static void main(String[] args) {
        AirlineManagementSystem system = new AirlineManagementSystem();
        Passenger passenger1 = new Passenger("Polashi", "polashi123@gmail.com", "7777777777", 27, Gender.FEMALE);

        LocalDateTime departTime = LocalDateTime.now().plusDays(30);
        LocalDateTime arrivalTime = departTime.plusHours(3);


        Flight vistaraFlight = new Flight("UK-756", "Bangalore", "Guwahati", departTime, arrivalTime);
        Flight akasaAir = new Flight("UK-756", "Bangalore", "Guwahati", departTime, arrivalTime);
        system.addFlight((Flight) Arrays.asList(vistaraFlight, akasaAir));

        Aircraft aircraft1 = new Aircraft("Air001", "Boeing 707", 350);
        Aircraft aircraft2 = new Aircraft("Air002", "Airbus A109", 350);

        //Search Flights
        List<Flight> availableFlights = system.searchFlights("Bangalore", "Chennai", LocalDate.now().plusDays(10));
        System.out.println("Below are the available options:");
        for (Flight f: availableFlights){
            System.out.println("Flight: " + f.getId() + " Source: " + f.getSource() + " Destination: " + f.getDestination() + " Time: " + f.getDepartureTime());
        }

        Seat seat = new Seat("17A", SeatType.WINDOW);
        Booking booking = system.bookFlight(vistaraFlight, passenger1, seat, 5579);
        if(booking != null){
            System.out.println("Booking successful. Your booking ID is: " + booking.getBookingID());
        }else {
            System.out.println("Booking failed. Please try again");
        }
        system.cancelBooking(booking.getBookingID());
    }
}