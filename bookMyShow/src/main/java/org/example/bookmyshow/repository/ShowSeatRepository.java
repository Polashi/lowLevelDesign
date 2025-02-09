package org.example.bookmyshow.repository;

import org.example.bookmyshow.model.Show;
import org.example.bookmyshow.model.ShowSeat;
import org.example.bookmyshow.model.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> idList);

    ShowSeat save(ShowSeat showSeat);

    List<ShowSeatType> findAllByShow(Show show);
}
