package ie.cit.soft8027.domain;

public class Player {
    private String fullName, password;
    private int id, balance;


    public Player(int id, String fullName, int balance, String password) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.balance = balance;
        this.password = password;
    }

    public Player() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Player{" +
                "fullName='" + fullName + '\'' +
                ", id=" + id +
                ", balance=" + balance +
                '}';
    }
}