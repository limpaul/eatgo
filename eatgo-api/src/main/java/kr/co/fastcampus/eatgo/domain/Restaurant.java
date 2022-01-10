package kr.co.fastcampus.eatgo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Restaurant {
    private final String name;
    private final String address;
    private final Long id;
    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }



    public String getName() {
        return name;
    }

    public String getInformation() {
        return name+" in "+address;
    }

    public Long getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }
}
