package kr.co.restaurant.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {


    private Long id;
    private String name;
    private String address;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant() {

    }

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getInformation() {
        return name+" in "+address;
    }

    public void addMenuItem(MenuItem menu) {
        menuItems.add(menu);
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }
    public void setMenuItems(List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems){
            addMenuItem(menuItem);
        }
    }


}
