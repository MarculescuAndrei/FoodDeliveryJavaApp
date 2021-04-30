package main;

import model.App;
import model.user.Courier;
import model.user.Customer;
import model.restaurant.Item;
import model.restaurant.Menu;
import model.restaurant.Restaurant;
import service.AppService;
import service.AuditService;
import service.CsvReader;
import service.CsvWriter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        // Aici testez ca merge si Read si Write-ul, de la ultima testare sunt deja informatii scrise in fisiere, deci daca se mai da run
        // inca odata o sa fie duplicate, cel mai bine se vede cand sunt sterse fisierele .csv pt ca programul le creeaza, scrie in ele,
        // citeste informatiile, si dupa lucreaza cu ele, am facut asa ca sa vad ca merge totul ok.

        App myApp = new App();
        AppService myAppService = AppService.getInstance();
        AuditService myAuditService = AuditService.getInstance();

        CsvWriter cvsw = CsvWriter.getInstance();
        CsvReader cvsr = CsvReader.getInstance();

        Courier x1 = new Courier("Vlad", "vlad@gmail.com", "M1", "1234");
        Courier x2 = new Courier("Cristian", "cristian@gmail.com", "M4", "1234");
        Courier x3 = new Courier("Bogdan", "bogdan@gmail.com", "M6", "1234");

        Customer y1 = new Customer("Denis", "denis@gmail.com", "M4", "1234");
        Customer y2 = new Customer("Andrei", "andrei@gmail.com", "M6", "1234");
        Customer y3 = new Customer("Stefan", "stefan@gmail.com", "M1", "1234");

        cvsw.write(x1);
        cvsw.write(x2);
        cvsw.write(x3);
        myAuditService.write("Wrote couriers. ");
        ArrayList<Courier> couriers = cvsr.readCouriers();
        myAuditService.write("Read couriers. ");

        cvsw.write(y1);
        cvsw.write(y2);
        cvsw.write(y3);

        myAuditService.write("Wrote customers. ");
        ArrayList<Customer> customers = cvsr.readCustomers();
        myAuditService.write("Read customers. ");

        ArrayList<Item> items1 = new ArrayList<>();
        items1.add(new Item("Cheeseburger", 20));
        items1.add(new Item("McFlurry", 30));
        items1.add(new Item("Big Mac", 40));

        Item i1 = new Item("Taco", 20);
        Item i2 = new Item("Burrito", 30);
        Item i3 = new Item("Churros", 10);

        cvsw.write(i1);
        cvsw.write(i2);
        cvsw.write(i3);

        myAuditService.write("Wrote items. ");
        ArrayList<Item> items2 = cvsr.readItems();
        myAuditService.write("Read items. ");

        Menu menu1 = new Menu(items1);
        Menu menu2 = new Menu(items2);
        Restaurant r1 = new Restaurant("McDonald's", menu1, "M1", 1);
        Restaurant r2 = new Restaurant("Taco Bell", menu2, "M6", 2);

        cvsw.write(r1);
        cvsw.write(r2);

        myAuditService.write("Wrote restaurants. ");
        ArrayList<Restaurant> restaurants = cvsr.readRestaurants();
        myAuditService.write("Read restaurants. ");

        myApp.setCostumers(customers);
        myApp.setCouriers(couriers);
        myApp.setRestaurants(restaurants);

        myAppService.showMenu(myApp);

    }
}
