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
public class CalculateExpenses {
    public static Expenses createNewExpense(User paidBy, double amount, List<Splitting> splits, String expenseType) throws ExpenseErrorException{
        switch(expenseType){
            case "EQUAL" -> {
                int noOfSplits = splits.size();
                double splitAmount = ((double) Math.round(amount/noOfSplits));
                splits.forEach(split -> {
                    split.setAmount(splitAmount);
            });
                splits.get(0).setAmount(splitAmount + (amount - (splitAmount * noOfSplits)));
                return new EqualExpenses(paidBy, amount, splits);
            }
            case "EXACT" -> {
                return new ExactExpenses(paidBy, amount, splits);
            }
            case "PERCENT" -> {
                splits.forEach(split -> {
                    PercentSplitting percentSplit = (PercentSplitting) split;
                    split.setAmount((percentSplit.getPercent()*amount)/100);
            });
                return new PercentExpenses(paidBy, amount, splits);
            }
            default -> {
                return null;
            }
        }
    }
}
