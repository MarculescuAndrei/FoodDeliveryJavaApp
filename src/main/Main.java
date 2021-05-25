package main;

import model.App;
import model.user.Account;
import model.user.Courier;
import model.user.Customer;
import model.restaurant.Item;
import model.restaurant.Menu;
import model.restaurant.Restaurant;
import repository.AccountRepository;
import repository.CourierRepository;
import repository.CustomerRepository;
import repository.ItemRepository;
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

        ////////////////////////////////////////
        ItemRepository itemRepository = ItemRepository.getInstance();
        CourierRepository courierRepository = CourierRepository.getInstance();
        CustomerRepository customerRepository = CustomerRepository.getInstance();
        AccountRepository accountRepository = AccountRepository.getInstance();
        ////////////////////////////////////////

        CsvWriter cvsw = CsvWriter.getInstance();
        CsvReader cvsr = CsvReader.getInstance();

        Courier x1 = new Courier("Vlad", "vlad@gmail.com", "M1", "1234", 2);
        Courier x2 = new Courier("Cristian", "cristian@gmail.com", "M4", "1234", 3);
        Courier x3 = new Courier("Bogdan", "bogdan@gmail.com", "M6", "1234", 4);

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

        ////////////////////////////////////////
        //   Aici demonstrez CRUD-ul pe BD   //

        //Create
        Account accountx = new Account("Terry", "terry@email.com", "1234", "M6");
        Account accounty = new Account("Crews", "crews@email.com", "1234", "M6");
        accountRepository.addAccount(accountx);
        accountRepository.addAccount(accounty);
        //Read
        System.out.println(accountRepository.getAccounts());
        //Update
        Account accountz = new Account("CHANGED", "CHANGED@email.com", "M6", "1234");
        accountz.setId(accountx.getId());
        accountRepository.updateAccount(accountz);
        //Delete
        accountRepository.deleteAccount(accounty.getId());

        ////////////////////////////////////////

        //Create
        Courier courierx = new Courier("The", "the@email.com", "1234", "M6", 7);
        Courier couriery = new Courier("Rock", "crews@email.com", "1234", "M6", 8);
        courierRepository.addCourier(courierx);
        courierRepository.addCourier(couriery);
        //Read
        System.out.println(courierRepository.getCouriers());
        //Update
        Courier courierz = new Courier("CHANGED", "CHANGED@email.com","M6", "1234", 8);
        courierz.setId(courierx.getId());
        courierRepository.updateCourier(courierz);
        //Delete
        courierRepository.deleteCourier(couriery.getId());

        ////////////////////////////////////////

        //Create
        Customer customerx = new Customer("Harry", "harry@email.com", "M6", "1234");
        Customer customery = new Customer("Potter", "potter@email.com", "M6", "1234");
        customerRepository.addCustomer(customerx);
        customerRepository.addCustomer(customery);
        //Read
        System.out.println(customerRepository.getCustomers());
        //Update
        Customer customerz = new Customer("CHANGED", "CHANGED@email.com","M6","1234");
        customerz.setId(customerx.getId());
        customerRepository.updateCustomer(customerz);
        //Delete
        customerRepository.deleteCustomer(customery.getId());

        ////////////////////////////////////////

        //Create
        Item itemx = new Item("CousCous", 25);
        Item itemy = new Item("Turkey", 400);
        itemRepository.addItem(itemx);
        itemRepository.addItem(itemy);
        //Read
        System.out.println(itemRepository.getItems());
        //Update
        Item itemz = new Item("CHANGED", 200);
        itemz.setId(itemx.getId());
        itemRepository.updateItem(itemz);
        //Delete
        itemRepository.deleteItem(itemy.getId());

        ////////////////////////////////////////

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
