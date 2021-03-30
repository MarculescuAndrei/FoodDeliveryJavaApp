package model.user;

public class Customer extends Account {

    public Customer(String username, String email, String sector, String password) {
        super(username, email, sector, password);
    }

    @Override
    public String toString(){
        return "customer with username:  " + username;
    }
}
