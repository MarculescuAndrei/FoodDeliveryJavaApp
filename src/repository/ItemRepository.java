package repository;

import config.DatabaseConnection;
import model.restaurant.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemRepository {


    private static ItemRepository instance = null;
    private ItemRepository(){}

    public static ItemRepository getInstance() {
        if(instance == null){
            instance = new ItemRepository();
        }
        return instance;
    }

    public void addItem(Item item){

        String SQL = "insert into items values (?, ?, ?)";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setInt(1, item.getId());
            statement.setString(2, item.getName());
            statement.setDouble(3, item.getPrice());
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Item> getItems(){
        ArrayList<Item> items = new ArrayList<>();
        String SQL = "select * from items";

        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            ResultSet result = statement.executeQuery();
            while(result.next()){
                int id = result.getInt(1);
                String name = result.getString(2);
                double price = result.getDouble(3);

                items.add(new Item(name, price));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return items;
    }

    public void updateItem(Item item){

        String SQL = "update items set name = ?, price = ? where id = ?";

        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {

            statement.setString(1, item.getName());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getId());
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteItem(int id){
        String SQL = "delete from items where id = ?";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(SQL))
        {
            statement.setInt(1, id);
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}