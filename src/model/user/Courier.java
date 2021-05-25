package model.user;

public class Courier extends Account{
    private int commissionPerOrder;

    public Courier(String username, String email, String sector, String password, int commissionPerOrder) {
        super(username, email, sector, password);
        this.commissionPerOrder=commissionPerOrder;
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
