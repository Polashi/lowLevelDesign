package org.example.bookmyshow.service;

import org.example.bookmyshow.exception.ShowSeatNotFoundException;
import org.example.bookmyshow.exception.UserNotFoundException;
import org.example.bookmyshow.model.*;
import org.example.bookmyshow.repository.BookingRepository;
import org.example.bookmyshow.repository.ShowSeatRepository;
import org.example.bookmyshow.repository.UserRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * 1. Fetch the user from userId
 * 2. if user not found, throw exception.
 * 3. fetch the showSeat objects from the database.
 * 4. check if showSeats are available.
 * 5. if not, throw an exception.
 * ------- Take  a lock ---------
 * 6. check the showSeat status again.
 * 7. change the showSeat status to BLOCKED.
 * ------ Release the lock-------
 * 8. create the booking object with booking status PENDING
 * 9. Move to the payment page for calculate and process payment.
 * */

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;
    private BookingRepository bookingRepository;
    BookingService(UserRepository userRepository, ShowSeatRepository showSeatRepository,PriceCalculatorService priceCalculatorService, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookShow(Long userId, List<Long> showSeatIds) throws UserNotFoundException, ShowSeatNotFoundException{
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        User user = optionalUser.get();
        List<ShowSeat> showSeatList = showSeatRepository.findAllById(showSeatIds);
        for(ShowSeat showSeat : showSeatList){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new ShowSeatNotFoundException("Show seat with show id " + showSeat.getShow().getId() + " not found");
            }
        }

        List<ShowSeat> savedShowSeatList = new ArrayList<>();
        for(ShowSeat showSeat : showSeatList){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            savedShowSeatList.add(showSeatRepository.save(showSeat));
        }
        Booking booking = new Booking();
        booking.setShowSeats(savedShowSeatList);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setUser(user);
        booking.setPrice(priceCalculatorService.calculatePrice(savedShowSeatList));
        return bookingRepository.save(booking);
    }
}

