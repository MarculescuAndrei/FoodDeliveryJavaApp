package repository;
import config.DatabaseConnection;
import model.user.Courier;
import model.user.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourierRepository {

    private static CourierRepository instance = null;
    private CourierRepository(){}

    public static CourierRepository getInstance() {
        if(instance == null){
            instance = new CourierRepository();
        }
        return instance;
    }

    public void addCourier(Courier courier){

        String SQL = "insert into couriers values (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {

            statement.setInt(1, courier.getId());
            statement.setString(2, courier.getUsername());
            statement.setString(3, courier.getEmail());
            statement.setString(4, courier.getPassword());
            statement.setString(5, courier.getSector());
            statement.setInt(6, courier.getcommissionPerOrder());
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Courier getCourierById(int couId){
        String SQL = "select * from couriers where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setInt(1,couId);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                int id = result.getInt(1);
                String username = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                String sector = result.getString(5);
                int commissionperorder = result.getInt(6);
                Courier courier = new Courier(username,email,sector, password, commissionperorder);
                return courier;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Courier> getCouriers(){
        ArrayList<Courier> couriers = new ArrayList<>();
        String SQL = "select * from couriers";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            ResultSet result = statement.executeQuery();
            while(result.next()){
                int id = result.getInt(1);
                String username = result.getString("username");
                String email = result.getString("email");
                String password = result.getString("password");
                String sector = result.getString("sector");
                int commissionperorder = result.getInt("commissionperorder");
                couriers.add(new Courier(username,email,sector, password, commissionperorder));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return  couriers;
    }

    public void updateCourier(Courier courier){
        String SQL = "update couriers set username = ?, email = ?, password = ?, sector = ?, commissionperorder = ? where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setString(1, courier.getUsername());
            statement.setString(2, courier.getEmail());
            statement.setString(3, courier.getPassword());
            statement.setString(4, courier.getSector());
            statement.setInt(5, courier.getcommissionPerOrder());
            statement.setInt(6, courier.getId());
            statement.executeUpdate();


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteCourier(int id){
        String SQL = "delete from couriers where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setInt(1, id);
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}