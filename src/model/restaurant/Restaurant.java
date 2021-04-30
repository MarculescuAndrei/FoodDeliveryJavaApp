package model.restaurant;

public class Restaurant {
    private static int current_id;
    private int id;
    private String name;
    private Menu menu;
    private String sector;
    private int priceLvl; //1-4

    public Restaurant(String name, Menu menu,String sector, int priceLvl) {
        current_id+=1;
        this.id=current_id;
        this.menu = menu;
        this.name = name;
        this.sector=sector;
        this.priceLvl=priceLvl;
    }

    @Override
    public String toString() {
        return name + " Restaurant" + '\n' + "Sector: " + sector + " Price Level: " + priceLvl;
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

    public int getPriceLvl() {
        return priceLvl;
    }

    public void setPriceLvl(int priceLvl) {
        this.priceLvl = priceLvl;
    }
}
