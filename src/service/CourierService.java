package service;

import model.App;
import model.Order;
import model.user.Courier;

import java.util.Scanner;

public class CourierService{
    private AppService appService = AppService.getInstance();
    private static CourierService instance = null;

    private CourierService(){}

    public static CourierService getInstance(){
        if (instance == null) {
            instance = new CourierService();
        }
        return instance;
    }

    public void showMenuCourier(Courier courier, App app){

        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("1.Check your orders");
            System.out.println("2.View your commision");
            System.out.println("3.Log Out");
            System.out.println("4.Delete this account");

            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("Your orders are: ");
                    for(Order order: app.getOrders()){
                        if (order.getCourier().getUsername().equals(courier.getUsername())){
                            System.out.println(order);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Your commision is "+ courier.getcommissionPerOrder());
                    break;
                case 3:
                    appService.showMenu(app);
                    break;
                case 4:
                    app.getCouriers().remove(courier);
                    System.out.println("Account deleted.");
                    appService.showMenu(app);
                default:
                    System.out.println("That is not a valid option.");
            }
        }
    }
}
