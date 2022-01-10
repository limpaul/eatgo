package kr.co.fastcampus.eatgo.domain;

import java.util.*;

public class Restaurant {
    private final String name;
    private final String address;
    private final Long id;
    private List<MenuItem> menuItems = new ArrayList<>();

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

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        menuItems.forEach(it -> addMenuItem(it));
    }
}
