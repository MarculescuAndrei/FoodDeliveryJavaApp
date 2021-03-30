package model.user;

public abstract class Account {
    protected static int current_id=0;
    protected int id;
    protected String username;
    protected String email;
    protected String password;
    protected String sector;

    public Account(String username, String email,String sector, String password) {
        current_id+=1;
        this.id = current_id;
        this.username = username;
        this.email=email;
        this.password = password;
        this.sector=sector;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
