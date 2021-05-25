package model.restaurant;

public class Item {
    private static int current_id=0;
    private int id;
    private String name;
    private double price;

    public Item(String name, double price) {
        current_id += 1;
        this.id = current_id;
        this.name = name;
        this.price = price;
    }

    //Overrides
    @Override
    public boolean equals(Object x) {
        if (this == x) {
            return true;
        }
        if (x == null || x instanceof Item) {
            return false;
        }
        Item item = (Item) x;
        return Double.compare(item.price, price) == 0 && name.equals(item.name);
    }

    @Override
    public String toString(){
        return ("Item ID: " + this.id + " - " +this.name + " costs " + this.price + "RON");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
