package org.example.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "screens")
public class Screen extends BaseModel{
    private String name;
    @OneToMany
    private List<Seat> seatList;

    @ManyToOne
    private Theater theater;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<TheaterType> theaterTypes;
}
