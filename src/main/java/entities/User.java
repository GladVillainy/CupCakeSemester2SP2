package entities;

public class User {
    private int id;
    private String email;
    private String password;
    private String role;
    private double balance;

    public User(int id, String email, String password, String role, double balance) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }

    public User(String email, String password, String role, double balance) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }
}