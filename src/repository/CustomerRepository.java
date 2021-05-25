package repository;

import config.DatabaseConnection;
import model.user.Account;
import model.user.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static CustomerRepository instance = null;

    private CustomerRepository(){}

    public static CustomerRepository getInstance() {
        if(instance == null){
            instance = new CustomerRepository();
        }
        return instance;
    }

    public void addCustomer(Customer customer){

        String SQL = "insert into customers values (?, ?, ?, ?, ?)";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getUsername());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPassword());
            statement.setString(5, customer.getSector());
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Customer> getCustomers(){

        ArrayList<Customer> customers = new ArrayList<>();
        String SQL = "select * from customers";

        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            ResultSet result = statement.executeQuery();
            while(result.next()){
                String username = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                String sector = result.getString(5);
                customers.add(new Customer(username,email,password,sector));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return customers;
    }

    public Customer getCustomerById(int cusId){
        String SQL = "select * from customers where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setInt(1,cusId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                int id = result.getInt(1);
                String username = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                String sector = result.getString(5);
                Customer customer = new Customer(username,email,sector,password);
                return customer;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateCustomer(Customer customer){
        String SQL = "update customers set username = ?, email = ?, password = ?, sector = ? where id = ?";

        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {

            statement.setString(1, customer.getUsername());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPassword());
            statement.setString(4, customer.getSector());
            statement.setInt(5, customer.getId());
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteCustomer(int id){
        String SQL = "delete from customers where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setInt(1, id);
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}