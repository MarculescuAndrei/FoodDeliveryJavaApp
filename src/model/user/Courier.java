package model.user;

public class Courier extends Account{
    private int commissionPerOrder;

    public Courier(String username, String email, String sector, String password) {
        super(username, email, sector, password);
        this.commissionPerOrder=0;
    }

    public int getcommissionPerOrder() {
        return commissionPerOrder;
    }

    public void setcommissionPerOrder(int commissionPerOrder) {
        this.commissionPerOrder = commissionPerOrder;
    }

    @Override
    public String toString() {
        return "Courier with username: " + username;
    }

}
