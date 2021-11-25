/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SourceCodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author cools
 */
public class Driver{
        public static void main(String[] args) throws UserNotFoundException, ExpenseErrorException{
            new Menu().setVisible(true);
            Splitwise splitwiseManager = new Splitwise();

            splitwiseManager.addNewUser(new User("u1", "User1"));
            splitwiseManager.addNewUser(new User("u2", "User2"));
            splitwiseManager.addNewUser(new User("u3", "User3"));
            splitwiseManager.addNewUser(new User("u4", "User4"));

            Scanner in = new Scanner(System.in);
            while (true) {
                String command = in.nextLine();
                String[] commands = command.split(" ");
                String commandType = commands[0];
                String secondCommand = commands[1];

                switch (commandType) {
                    case "SHOW":
                        if(secondCommand.equals("ALL"))
                            splitwiseManager.showAllBalance();
                        else {
                            splitwiseManager.showBalance(commands[1]);
                        }
                        break;
                    case "EXPENSE":
                        String paidBy = commands[1];
                        Double amount = Double.parseDouble(commands[2]);
                        int noOfUsers = Integer.parseInt(commands[3]);
                        String expenseType = commands[4 + noOfUsers];
                        List<Splitting> splits = new ArrayList<>();
                        switch (expenseType) {
                            case "EQUAL":
                                for (int i = 0; i < noOfUsers; i++) {
                                    splits.add(new EqualSplitting(splitwiseManager.userMap.get(commands[4 + i])));
                                }
                                splitwiseManager.addExpense("EQUAL", amount, paidBy, splits);
                                break;
                            case "EXACT":
                                for (int i = 0; i < noOfUsers; i++) {
                                    splits.add(new ExactSplitting(splitwiseManager.userMap.get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                                }
                                splitwiseManager.addExpense("EXACT", amount, paidBy, splits);
                                break;
                            case "PERCENT":
                                for (int i = 0; i < noOfUsers; i++) {
                                    splits.add(new PercentSplitting(Double.parseDouble(commands[5 + noOfUsers + i]), splitwiseManager.userMap.get(commands[4 + i])));
                                }
                                splitwiseManager.addExpense("PERCENT", amount, paidBy, splits);
                                break;
                        }
                        break;
                }
            }
        }
}
