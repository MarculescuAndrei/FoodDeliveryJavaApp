package model;
import model.restaurant.Item;
import model.restaurant.Restaurant;
import model.user.Courier;
import model.user.Customer;
import java.util.ArrayList;

public class Order {
    private static int current_id=0;
    private int id;
    private Restaurant restaurant;
    private Customer customer;
    private Courier courier;
    private ArrayList<Item> items;

    public Order(Restaurant restaurant, Customer customer, Courier courier, ArrayList<Item> items) {
        current_id += 1;
        this.id = current_id;
        this.restaurant = restaurant;
        this.customer = customer;
        this.courier = courier;
        this.items = items;
    }

    @Override
    public String toString() {
        return "The order with ID: " + id + ", from " + restaurant + ", delivered by " + courier + ", for " + customer;
    }

    public double orderPayment(){
        double payment = 0;
        for (Item item : items){
            payment += item.getPrice();
        }
        return payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
