package model.restaurant;
import java.util.ArrayList;

public class Menu {
    private static int current_id=0;
    private int id;
    private ArrayList<Item> items;

    public Menu(ArrayList<Item> items) {
        current_id+=1;
        this.id=current_id;
        this.items = items;
    }
    @Override
    public String toString() {
        return "Menu : " + "ID = " + id + ", with items : " + items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
