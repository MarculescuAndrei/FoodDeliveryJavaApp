package model;
import java.util.ArrayList;

import model.restaurant.Restaurant;
import model.user.Courier;
import model.user.Customer;

public class App {

    private ArrayList<Restaurant> restaurants = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Customer> costumers = new ArrayList<>();
    private ArrayList<Courier> couriers = new ArrayList<>();

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public ArrayList<Customer> getCostumers() {
        return costumers;
    }

    public void setCostumers(ArrayList<Customer> costumers) {
        this.costumers = costumers;
    }

    public ArrayList<Courier> getCouriers() {
        return couriers;
    }

    public void setCouriers(ArrayList<Courier> couriers) {
        this.couriers = couriers;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
