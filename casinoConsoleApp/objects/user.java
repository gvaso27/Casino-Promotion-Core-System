package objects;

import java.util.Objects;

public class user {
    private String username;
    private int balance;
    private int totalSlotBet;
    private scenario scenario;
    private int maxDeposit;

    public user(){};

    public user(String username, int balance, int totalSlotBet, scenario scenario, int maxDeposit){
        this.username = username;
        this.balance = balance;
        this.totalSlotBet = totalSlotBet;
        this.scenario = scenario;
        this.maxDeposit = maxDeposit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getTotalSlotBet() {
        return totalSlotBet;
    }

    public void setTotalSlotBet(int totalSlotBet) {
        this.totalSlotBet = totalSlotBet;
    }

    public scenario getScenario() {
        return scenario;
    }

    public void setScenario(scenario scenario) {
        this.scenario = scenario;
    }

    public int getMaxDeposit() {
        return maxDeposit;
    }

    public void setMaxDeposit(int maxDeposit) {
        this.maxDeposit = maxDeposit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        user user = (user) o;
        return Objects.equals(username, user.username);
    }
}