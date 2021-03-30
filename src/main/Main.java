package main;

import model.App;
import model.user.Courier;
import model.user.Customer;
import model.restaurant.Item;
import model.restaurant.Menu;
import model.restaurant.Restaurant;
import service.AppService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        App myApp = new App();
        AppService myAppService = new AppService();

        Courier x1 = new Courier("Vlad", "vlad@gmail.com", "M1", "1234");
        Courier x2 = new Courier("Cristian", "cristian@gmail.com", "M4", "1234");
        Courier x3 = new Courier("Bogdan", "bogdan@gmail.com", "M6", "1234");

        Customer y1 = new Customer("Denis", "denis@gmail.com", "M4", "1234");
        Customer y2 = new Customer("Andrei", "andrei@gmail.com", "M6", "1234");
        Customer y3 = new Customer("Stefan", "stefan@gmail.com", "M1", "1234");


        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Courier> couriers = new ArrayList<>();
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        ArrayList<Item> items1 = new ArrayList<>();
        items1.add(new Item("Cheeseburger", 20));
        items1.add(new Item("McFlurry", 30));
        items1.add(new Item("Big Mac", 40));

        ArrayList<Item> items2 = new ArrayList<>();
        items2.add(new Item("Taco", 20));
        items2.add(new Item("Burrito", 30));
        items2.add(new Item("Churros", 10));

        Menu menu1 = new Menu(items1);
        Menu menu2 = new Menu(items2);

        restaurants.add(new Restaurant("McDonald's", menu1, "M1", 1));
        restaurants.add(new Restaurant("Taco Bell", menu2, "M6", 2));

        myApp.setRestaurants(restaurants);

        customers.add(y1);
        customers.add(y2);
        customers.add(y3);

        couriers.add(x1);
        couriers.add(x2);
        couriers.add(x3);

        myApp.setCostumers(customers);
        myApp.setCouriers(couriers);
        myAppService.showMenu(myApp);
    }
}
