/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodmanagement;

import java.io.FileNotFoundException;
import java.text.ParseException;


/**
 *
 * @author lengo
 */
public class App {
    public static void main(String args[]) throws ParseException, FileNotFoundException {
        Refrigerator myRefrigerator = new Refrigerator();
        Menu menu = new Menu();
        int userChoice;
        menu.add("Add a new food");
        menu.add("Search a food by name");
        menu.add("Remove the food by ID");
        menu.add("Print the food list in the descending order of expired date");
        menu.add("Save to binary file");
        menu.add("Quit");
         do {
             userChoice = menu.getUserChoice();
             switch(userChoice) {
                 case 1: 
                     myRefrigerator.addFood();
                     break;
                 case 2:
                     myRefrigerator.searchByName();
                     break;
                 case 3:
                     myRefrigerator.removeByID();
                     break;
                 case 4: 
                     myRefrigerator.printAllFoods();
                     break;
                 case 5:
                     myRefrigerator.saveToBinaryFile();
                     break;
                 case 6:
                     System.out.println("Bye. See you later!!!");
                     break;
             }
         } while(userChoice >= 1 && userChoice < menu.size());
    }
}
