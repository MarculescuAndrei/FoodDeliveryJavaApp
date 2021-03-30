package model.restaurant;

public class Restaurant {
    private static int current_id;
    private int id;
    private String name;
    private Menu menu;
    private String sector;
    private int price_lvl; //1-4

    public Restaurant(String name, Menu menu,String sector, int price_lvl) {
        current_id+=1;
        this.id=current_id;
        this.menu = menu;
        this.name = name;
        this.sector=sector;
        this.price_lvl=price_lvl;
    }

    @Override
    public String toString() {
        return name + " Restaurant" + '\n' + "Sector: " + sector + " Price Level: " + price_lvl;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getPrice_lvl() {
        return price_lvl;
    }

    public void setPrice_lvl(int price_lvl) {
        this.price_lvl = price_lvl;
    }
}
