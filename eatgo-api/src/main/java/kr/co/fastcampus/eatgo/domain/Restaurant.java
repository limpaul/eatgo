package kr.co.fastcampus.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.*;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;

    @Transient // 임시로 처리되는거다 db로 저장하거나 처리하는것이 아니다. 단지 아이템들 받아서 리턴 해줄 애들이다.
    @JsonInclude(JsonInclude.Include.NON_NULL) // 값이 없을경우(null일 경우) json으로 내보내지 마라.
    private List<MenuItem> menuItems;

    public Restaurant(){

    }
    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

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
    public void setId(Long id){this.id = id;}
    public Long getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }
    public void addMenuItem(MenuItem item) {
        if(menuItems == null){
            menuItems = new ArrayList<>();
        }
        menuItems.add(item);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        if(this.menuItems == null){
            this.menuItems = new ArrayList<>();
        }
        menuItems.forEach(it -> addMenuItem(it));
    }

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
