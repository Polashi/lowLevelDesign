package org.example.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "theater")
public class Theater extends BaseModel{
    private String name;
    private String address;

    @OneToMany
    private List<Screen> screenList;

}
