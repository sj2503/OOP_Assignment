/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SourceCodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cools
 */
public class Splitwise {
    Map<String,User> userMap;
    List<Expenses> expenses;
    Map<String, Map<String, Double> > balanceSheet;
    
    public Splitwise(){
        userMap = new HashMap<>();
        expenses = new ArrayList<>();
        balanceSheet = new HashMap<>();
    }
    
    public void addNewUser(User newUser){
        userMap.put(newUser.getUserId(), newUser);
        balanceSheet.put(newUser.getUserId(), new HashMap<>());
    }
    
    public void addExpense(String expenseType, double amount, String paidBy, List<Splitting> splits) throws ExpenseErrorException{
        Expenses expense = CalculateExpenses.createNewExpense(userMap.get(paidBy), amount, splits, expenseType);
        expenses.add(expense);
        
        expense.getSplits().forEach((Splitting split) -> {
            String paidTo = split.getUser().getUserId();
            Map<String, Double> balances = balanceSheet.get(paidBy);
            if (!(balances.containsKey(paidTo))) {
                balances.put(paidTo, 0.0);
            }
            balances.put(paidTo, balances.get(paidTo) + split.getAmount());

            balances = balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy)) {
                balances.put(paidBy, 0.0);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        });
    }
    
    public void showBalance(String userId) throws UserNotFoundException{
        try{
            User validateUser = userMap.get(userId);
            if(validateUser == null){
            throw new UserNotFoundException(userId + " does not exist!");
        }
        }
        catch(UserNotFoundException e){
            System.out.println(e.getMessage());
            return;
        }
        boolean invalid = true;
        for(Map.Entry<String, Double> balanceUser : balanceSheet.get(userId).entrySet()){
            if(balanceUser.getValue() != 0) {
                invalid = false;
                balancePrint(userId, balanceUser.getKey(), balanceUser.getValue());
            }
        }
        if(invalid){
        System.out.println("No balances");
    }
    }
    
    public void showAllBalance() {
        boolean invalid = true;
        for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<String, Double> balanceUser : allBalances.getValue().entrySet()) {
                if (balanceUser.getValue() > 0) {
                    invalid = false;
                    balancePrint(allBalances.getKey(), balanceUser.getKey(), balanceUser.getValue());
                }
            }
        }
        if(invalid){
        System.out.println("No balances");
    }
    }
    
    private void balancePrint(String firstUser, String secondUser, double amount){
        String firstUserName = userMap.get(firstUser).getUserName();
        String secondUserName = userMap.get(secondUser).getUserName();
        if (amount > 0) {
            System.out.println(secondUserName + " owes " + firstUserName + ": " + Math.abs(amount));
        }
        else if(amount < 0){
            System.out.println(firstUserName + " owes " + secondUserName + ": " + Math.abs(amount));
        }
        
    }
}
