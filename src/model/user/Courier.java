package model.user;

public class Courier extends Account{
    private int commission_per_order;

    public Courier(String username, String email, String sector, String password) {
        super(username, email, sector, password);
        this.commission_per_order=0;
    }

    public int getCommission_per_order() {
        return commission_per_order;
    }

    public void setCommission_per_order(int commission_per_order) {
        this.commission_per_order = commission_per_order;
    }

    @Override
    public String toString() {
        return username;
    }

}
