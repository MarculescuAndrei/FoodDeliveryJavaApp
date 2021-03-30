package service;

import model.App;
import model.Order;
import model.user.Courier;

import java.util.Scanner;

public class CourierService extends AppService{
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
                    System.out.println("Your commision is "+ courier.getCommission_per_order());
                    break;
                case 3:
                    showMenu(app);
                    break;
                case 4:
                    app.getCouriers().remove(courier);
                    System.out.println("Account deleted.");
                    showMenu(app);
                default:
                    System.out.println("That is not a valid option.");
            }
        }
    }
}
