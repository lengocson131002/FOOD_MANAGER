/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodmanagement;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author lengo
 */
public class Menu extends ArrayList<String> {
    
    public int getUserChoice() {
        Scanner in = new Scanner(System.in);
        int userChoice = 0;
        boolean validInput = false;
        
        System.out.println("\n\nWelcome to Food Management - @ 2021 by SE160171 - LE NGOC SON");
        System.out.println("Select the options below:");
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + ". " + this.get(i));
        }
        while (!validInput) {
            try {
                System.out.print("Enter your choice " + 1 + " -> " + (this.size()) + ": ");
                
                userChoice = Integer.parseInt(in.nextLine());
                if (userChoice < 1 || userChoice > this.size()) {
                    validInput = false;
                } else {
                    validInput = true;
                }
            } catch (Exception e) {
                validInput = false;
            }
            
            if (!validInput) {
                System.out.println("Invalid choice. Enter again!");
            }
        }
        return userChoice;
    }
}
