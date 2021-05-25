package repository;

import config.DatabaseConnection;
import model.user.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AccountRepository {
    private static AccountRepository instance = null;
    private AccountRepository(){}

    public static AccountRepository getInstance() {
        if(instance == null){
            instance = new AccountRepository();
        }
        return instance;
    }

    public void addAccount(Account account){

        String SQL = "insert into accounts values (?, ?, ?, ?, ?)";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setInt(1, account.getId());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getEmail());
            statement.setString(4, account.getPassword());
            statement.setString(5, account.getSector());
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public ArrayList<Account> getAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        String SQL = "select * from accounts";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            ResultSet result = statement.executeQuery();
            while(result.next()){
                String username = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                String sector = result.getString(5);
                accounts.add(new Account(username,email,sector, password));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return  accounts;
    }

    public Account getAccountbyId(int accId){
        String SQL = "select * from accounts where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setInt(1,accId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                int id = result.getInt(1);
                String username = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                String sector = result.getString(5);
                Account account = new Account(username,email,sector,password);
                return account;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateAccount(Account account){
        String SQL = "update accounts set username = ?, email = ?, password = ?, sector = ? where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getPassword());
            statement.setString(4, account.getSector());
            statement.setInt(5, account.getId());
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteAccount(int id){
        String SQL = "delete from accounts where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setInt(1, id);
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}