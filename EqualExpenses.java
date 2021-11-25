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
public class EqualExpenses extends Expenses{

    public EqualExpenses(User paidBy, double amount, List<Splitting> splits) {
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
        
        return getSplits().stream().noneMatch(split -> (!(split instanceof EqualSplitting)));
    }
    
    
}
