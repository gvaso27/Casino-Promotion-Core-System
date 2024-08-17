package service;

import objects.*;
import java.util.ArrayList;

public class appServices {

    private ArrayList<user> users = new ArrayList<>();
    private ArrayList<scenario> scenarios = new ArrayList<>();
    private boolean winLose = true;

    public appServices(){};

    public void register(String username){
        user newUser = new user(username, 0, 0,  null, 0);
        for (user existingUser : users) {
            if (existingUser.equals(newUser)) {
                return;
            }
        }
        users.add(newUser);
    }

    public void addScenario(int prize1, int prize2, int prize3){
        scenario newScenario = new scenario(prize1, prize2, prize3);
        scenarios.add(newScenario);
    }

    public int balance(String username){
        for (user existingUser : users) {
            if (existingUser.getUsername().equals(username)) {
                return existingUser.getBalance();
            }
        }
        return 0;
    }

    public void deposit(String username, int amount){
        for (user existingUser : users) {
            if (existingUser.getUsername().equals(username)) {
                int currentBalance = existingUser.getBalance();
                existingUser.setBalance(currentBalance + amount);
                if(existingUser.getMaxDeposit() < amount){
                    existingUser.setMaxDeposit(amount);
                }
            }
        }

    }

    public void bet(String username, int amount, boolean slot){
        for (user existingUser : users) {
            if (existingUser.getUsername().equals(username)) {
                if (existingUser.getBalance() >= amount) {
                    int currentBalance = existingUser.getBalance();
                    int currentTotalSlotBet = existingUser.getTotalSlotBet();
                    if (winLose) {
                        currentBalance += amount;
                    } else {
                        currentBalance -= amount;
                    }
                    if (slot) {
                        currentTotalSlotBet += amount;
                    }
                    existingUser.setTotalSlotBet(currentTotalSlotBet);

                    //assigning scenarios and adding first prize
                    if (existingUser.getScenario() == null && existingUser.getTotalSlotBet() >= 50 &&
                            existingUser.getMaxDeposit() >= 100 && !scenarios.isEmpty()) {
                        existingUser.setScenario(scenarios.getFirst());
                        scenarios.removeFirst();
                        currentBalance += existingUser.getScenario().getPrize1();
                        existingUser.getScenario().setPrize1(-1);
                    }else //adding prize2 or prize3 if the conditions are met
                        if(existingUser.getScenario() != null) {
                        if (existingUser.getScenario().getPrize1() == -1 && existingUser.getTotalSlotBet() >= 250 &&
                                existingUser.getMaxDeposit() >= 500 && existingUser.getScenario().getPrize2() != -1) {
                            currentBalance += existingUser.getScenario().getPrize2();
                            existingUser.getScenario().setPrize2(-1);
                        }else if (existingUser.getScenario().getPrize2() == -1 && existingUser.getTotalSlotBet() >= 500 &&
                                existingUser.getMaxDeposit() >= 1000 && existingUser.getScenario().getPrize3() != -1) {
                            currentBalance += existingUser.getScenario().getPrize3();
                            existingUser.getScenario().setPrize3(-1);
                        }
                    }


                    existingUser.setBalance(currentBalance);
                    winLose = !winLose;
                }
            }
        }
    }

    // Getters for test purposes
    public ArrayList<user> getUsers() {
        return users;
    }

    public ArrayList<scenario> getScenarios() {
        return scenarios;
    }

}