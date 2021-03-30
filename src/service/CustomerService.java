package service;

import model.App;
import model.Order;
import model.restaurant.Item;
import model.restaurant.Restaurant;
import model.user.Courier;
import model.user.Customer;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CustomerService extends AppService {
    public void showMenuCustomer(Customer customer, App app) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1.View Restaurants");
            System.out.println("2.Order Food");
            System.out.println("3.Log Out");
            System.out.println("4.Delete this account");
            int select = scanner.nextInt();

            ArrayList<Restaurant> restaurants = app.getRestaurants();
            switch (select) {
                case 1:
                    for (Restaurant restaurant : restaurants) {
                        System.out.println(restaurant.getName() + " Restaurant's Menu: ");
                        for (Item item : restaurant.getMenu().getItems()) {
                            System.out.println(item);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Choose a restaurant:" );
                    ArrayList<Restaurant> restaurantsList = new ArrayList<>(restaurants);

                    for (Restaurant restaurant : restaurantsList) {
                        System.out.println(restaurantsList.indexOf(restaurant) + " - " + restaurant.getName());
                    }

                    int selected = scanner.nextInt();
                    Restaurant selectedRestaurant = restaurantsList.get(selected);
                    ArrayList<Item> restaurantItems = selectedRestaurant.getMenu().getItems();

                    for (Item item : restaurantItems) {
                        System.out.println(restaurantItems.indexOf(item) + " - " + item.getName() + " costs " + item.getPrice());
                    }

                    ArrayList<Item> itemsToOrder = new ArrayList<>();
                    System.out.println("Select what items you want to order by choosing their respective number \n For example : 1,3,5 \n");
                    String response = scanner.next();
                    String[] choices = response.split(",");
                    for (String c : choices) {
                        int productNumber = parseInt(c);
                        itemsToOrder.add(restaurantItems.get(productNumber));
                    }

                    Courier designatedCourier = chooseCourier(selectedRestaurant, app);
                    Order order = new Order(selectedRestaurant, customer, designatedCourier, itemsToOrder);
                    app.getOrders().add(order);

                    //unitati de masura pe 10;
                    System.out.println("The order will arrive from " + selectedRestaurant.getSector() + ", delivered by " + designatedCourier.getUsername());
                    break;
                case 3:
                    showMenu(app);
                    break;
                case 4:
                    app.getCostumers().remove(customer);
                    System.out.println("Account deleted.");
                    showMenu(app);

                default:
                    System.out.println("That is not a valid option");
            }
        }
    }

    private Courier chooseCourier(Restaurant selectedRestaurant, App app) {
        Random rand = new Random();
        ArrayList<Courier> couriers = app.getCouriers();
        ArrayList<Courier> closeCouriers = new ArrayList<>();
        for (Courier courier : couriers) {
            if(selectedRestaurant.getSector().equals(courier.getSector())) {
                closeCouriers.add(courier);
                }
            }
        int index = rand.nextInt(closeCouriers.size());
        return closeCouriers.get(index);
        }

    }

