package service;
import model.user.Account;
import model.user.Customer;
import model.user.Courier;
import model.App;

import java.util.ArrayList;
import java.util.Scanner;

public class AppService {
    protected static Scanner scanner = new Scanner(System.in);
    private static AppService instance = null;
    int select;

    private AppService(){}

    public static AppService getInstance(){
        if (instance == null) {
            instance = new AppService();
        }
        return instance;
    }

    public void showMenu(App app){
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Sign up as Courier");
            System.out.println("3. Sign up as Customer");
            System.out.println("4. Exit");
            select=scanner.nextInt();

            switch (select) {
                case 1:
                    System.out.println("What do you login as? Write your answer (Courier/Customer): "); // Customer/Courier
                    String loginChoice = scanner.next();
                    System.out.println("Username:");
                    String username = scanner.next();
                    System.out.println("Password:");
                    String password = scanner.next();

                    if(loginChoice.equals("Customer")){
                        Account account = this.findCustomer(username,password, app.getCostumers());
                        CustomerService customerService= CustomerService.getInstance();
                        customerService.showMenuCustomer((Customer) account, app);
                    }
                    else if(loginChoice.equals("Courier")){
                        Account account = this.findCourier(username,password, app.getCouriers());
                        CourierService courierService = CourierService.getInstance();
                        courierService.showMenuCourier((Courier) account, app);
                    }
                    else {
                        System.out.println("Login credentials are invalid");
                    }

                    break;
                case 2:
                    signUpCourier(app);
                    break;
                case 3:
                    signUpCustomer(app);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("That is not a valid option.");
            }
        }

    }

    private Account findCustomer(String username,String password, ArrayList<Customer> customers){
        for (Customer customer : customers){
            if (customer.getPassword().equals(password) && customer.getUsername().equals(username)){ //
                return customer;
            }
        }
        return null;
    }

    private Account findCourier(String username,String password, ArrayList<Courier> couriers){
        for (Courier courier : couriers){
            if (courier.getPassword().equals(password) && courier.getUsername().equals(username)){
                return courier;
            }
        }
        return null;
    }

    private void signUpCustomer(App app) {
        System.out.println("Username:");
        String username = scanner.next();
        System.out.println("Email:");
        String email = scanner.next();
        System.out.println("Password ");
        String password = scanner.next();
        System.out.println("Sector:");
        String sector = scanner.next();

        Customer customer = new Customer(username,email,sector,password);
        app.getCostumers().add(customer);
    }

    private void signUpCourier(App app ) {
        System.out.println("Username:");
        String username = scanner.next();
        System.out.println("Email:");
        String email = scanner.next();
        System.out.println("Password");
        String password = scanner.next();
        System.out.println("Sector :");
        String sector = scanner.next();

        Courier courier = new Courier(username,email,sector,password, 0);
        app.getCouriers().add(courier);
    }

}
