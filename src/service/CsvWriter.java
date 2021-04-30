package service;

import model.restaurant.Item;
import model.restaurant.Restaurant;
import model.user.Courier;
import model.user.Customer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CsvWriter {
    private static CsvWriter instance = null;
    ArrayList<Path> paths = new ArrayList<>();

    public static CsvWriter getInstance() {
        if (instance == null) {
            instance = new CsvWriter();
        }
        return instance;
    }

    private CsvWriter(){

        Path customerPath = Paths.get("files/customers.csv");
        Path courierPath = Paths.get("files/couriers.csv");
        Path itemPath = Paths.get("files/items.csv");
        Path restaurantPath = Paths.get("files/restaurants.csv");

        paths.add(customerPath);
        paths.add(courierPath);
        paths.add(itemPath);
        paths.add(restaurantPath);

        for (Path path : paths) {
            if (!Files.exists(path)) {
                try {
                    Files.createFile(path);
                } catch (Exception exc) {
                    System.out.println(exc);
                }
            }
        }
    }

    public void write(Customer customer) throws IOException {
        System.out.println("Writing a customer.");
        BufferedWriter bfWr = Files.newBufferedWriter(paths.get(0), StandardOpenOption.APPEND);
        String line = customer.getUsername() + "," +
                customer.getEmail() + "," +
                customer.getSector() + "," +
                customer.getPassword();
        System.out.println(line);
        bfWr.write(line + "\n");
        bfWr.flush();
        bfWr.close();
    }

    public void write(Courier courier) throws IOException {
        System.out.println("Writing a courier.");
        BufferedWriter bfWr = Files.newBufferedWriter(paths.get(1), StandardOpenOption.APPEND);
        String line = courier.getUsername() + "," +
                courier.getEmail() + "," +
                courier.getSector() + "," +
                courier.getPassword() + "," +
                courier.getcommissionPerOrder();
        System.out.println(line);
        bfWr.write(line + "\n");
        bfWr.flush();
        bfWr.close();
    }

    public void write(Item item) throws IOException {
        System.out.println("Writing an item.");
        BufferedWriter bfWr = Files.newBufferedWriter(paths.get(2), StandardOpenOption.APPEND);
        String line = item.getName() + "," +
                item.getPrice();
        System.out.println(line);
        bfWr.write(line + "\n");
        bfWr.flush();
        bfWr.close();
    }

    public void write(Restaurant restaurant) throws IOException {
        System.out.println("Writing a restaurant.");
        BufferedWriter bfWr = Files.newBufferedWriter(paths.get(3), StandardOpenOption.APPEND);
        StringBuilder itemLine = new StringBuilder();

        for (Item item : restaurant.getMenu().getItems()){
            itemLine.append(item.getName()).append(",").append(item.getPrice()).append(",");
        }

        String line = restaurant.getName() + "," +
                itemLine.toString() +
                restaurant.getSector() + "," +
                restaurant.getPriceLvl();
        System.out.println(line);
        bfWr.write(line + "\n");
        bfWr.flush();
        bfWr.close();
    }

}
