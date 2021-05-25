package service;
import model.restaurant.Item;
import model.restaurant.Menu;
import model.restaurant.Restaurant;
import model.user.Courier;
import model.user.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CsvReader {
    private static CsvReader instance = null;
    ArrayList<Path> paths = new ArrayList<>();

    public static CsvReader getInstance() {
        if (instance == null) {
            instance = new CsvReader();
        }
        return instance;
    }

    private CsvReader() {
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

    public ArrayList<Item> readItems() throws IOException {
        ArrayList<Item> items = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths.get(2));
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] itm = line.split(",");
            String name = itm[0];
            double price = Double.parseDouble(itm[1]);

            items.add(new Item(name, price));
            line = bufferedReader.readLine();
            }
            return items;
    }

    public ArrayList<Courier> readCouriers() throws IOException {
        ArrayList<Courier> couriers = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths.get(1));
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] crrs = line.split(",");
            String username = crrs[0];
            String email = crrs[1];
            String sector = crrs[2];
            String password = crrs[3];
            couriers.add(new Courier(username, email, sector, password, 0));
            line = bufferedReader.readLine();
        }
        return couriers;
    }

    public ArrayList<Customer> readCustomers() throws IOException {
        ArrayList<Customer> customers = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths.get(0));
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] cstmrs = line.split(",");
            String username = cstmrs[0];
            String email = cstmrs[1];
            String sector = cstmrs[2];
            String password = cstmrs[3];
            customers.add(new Customer(username, email, sector, password));
            line = bufferedReader.readLine();
        }
        return customers;
    }

    public ArrayList<Restaurant> readRestaurants() throws IOException {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths.get(3));
        String line = bufferedReader.readLine();

        while (line != null) {

            String[] rstrnts = line.split(",");
            int len = rstrnts.length;
            String name = rstrnts[0];
            ArrayList<Item> lineItems = new ArrayList<>();
            for(int i = 1; i <= len -3 ; i += 2){
                Item item = new Item(rstrnts[i], Double.parseDouble(rstrnts[i+1]));
                lineItems.add(item);
            }

            Menu menu = new Menu(lineItems);
            String sector = rstrnts[len-2];
            int price_lvl = Integer.parseInt(rstrnts[len-1]);
            restaurants.add(new Restaurant(name, menu, sector, price_lvl));
            line = bufferedReader.readLine();
        }
        return restaurants;
    }
}
