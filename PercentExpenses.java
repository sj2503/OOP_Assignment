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
public class PercentExpenses extends Expenses{

    public PercentExpenses(User paidBy, double amount, List<Splitting> splits) {
        super(paidBy, amount, splits);
        try{
        if(!(check())){
            throw new ExpenseErrorException("The values do not match, please check again!");
        }
        }
        catch(ExpenseErrorException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean check() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         if (!getSplits().stream().noneMatch(split -> (!(split instanceof PercentSplitting)))) {
            return false;
        }
         
        double overallPercentage = 100;
        double splitPercentSum = 0;
        for(Splitting split: getSplits()){
            PercentSplitting percentSplit = (PercentSplitting) split;
            splitPercentSum = splitPercentSum + percentSplit.getPercent();
        }
        
        return overallPercentage == splitPercentSum;
    }
    
    
    
}
