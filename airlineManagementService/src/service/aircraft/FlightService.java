package service.aircraft;

import entity.aircraft.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FlightService {
    private final List<Flight> flightList;

    public FlightService(List<Flight> list){
        flightList = list;
    }

    public List<Flight> getFlightOptions(String from, String to, LocalDate date){
        return flightList.stream().filter(flight -> flight.getSource().equals(flight)
                && flight.getDestination().equals(to)
                && flight.getDepartureTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }
}
