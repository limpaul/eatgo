package kr.co.fastcampus.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue
    private Long id;
    private Long restaurantId;
    private String name;
    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean destroy;
    public MenuItem(String name){
        this.name = name;
    }
}
