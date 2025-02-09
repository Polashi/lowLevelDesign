package org.example.bookmyshow.service;

import org.example.bookmyshow.model.ShowSeat;
import org.example.bookmyshow.model.ShowSeatType;
import org.example.bookmyshow.repository.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    private ShowSeatRepository showSeatRepository;

    public int calculatePrice(List<ShowSeat> showSeatList){
        int amount = 0;
        // for the entire list of showSeats, it's for the same single show which I need to pass in the below method
        List<ShowSeatType> showSeatTypes = showSeatRepository.findAllByShow(showSeatList.get(0).getShow());
        for(ShowSeat showSeat: showSeatList){
            for(ShowSeatType showSeatType: showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }

}
