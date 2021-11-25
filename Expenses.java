/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SourceCodes;

import java.util.List;

/**
 *
 * @author cools
 */
public abstract class Expenses {
    private User paidBy;
    private double amount;
    private String expenseType;
    private List<Splitting> splits;

    public Expenses(User paidBy, double amount, List<Splitting> splits) {
        this.paidBy = paidBy;
        this.amount = amount;
//        this.noOfUsers = noOfUsers;
        this.splits = splits;
    }
    
    public abstract boolean check();
    
    public List<Splitting> getSplits() {
        return splits;
    }

    public void setSplits(List<Splitting> splits) {
        this.splits = splits;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
    
}
