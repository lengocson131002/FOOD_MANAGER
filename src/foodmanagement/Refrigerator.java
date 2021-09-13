/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodmanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author lengo
 */
public class Refrigerator extends ArrayList<Food> {

    public static Scanner in = new Scanner(System.in);

    private int isExisted(String ID) {
        for (Food food : this) {
            if (food.getID().equals(ID)) {
                return this.indexOf(food);
            }
        }
        return -1;
    }

    private String getConfirmMessage(String message) {
        String response = "";
        boolean validInput;
        do {
            System.out.print(message);
            response = in.nextLine().toLowerCase();
            validInput = true;
            if (response.trim().length() != 1) {
                validInput = false;
            }
        } while (!validInput);
        return response;
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            if(sdf.parse(date) == null) {
                throw new Exception();
            } else {
                return true;
            }
        } catch(Exception ex) {
            return false;
        }
    }
    
    public void addFood() throws ParseException {
        String ID, name, type;
        String place = null;
        String expiredDate = null;
        double weight = 0;
        boolean validInput;
        boolean continueAdding = true;

     
        //ADDING FOOD
        do {
            System.out.println("\nADD A NEW FOOD");
            System.out.println("--------------------");
            
            //ENTER ID
            do {
                System.out.print("Enter ID: ");
                ID = in.nextLine();
                validInput = true;
                if (isExisted(ID) >= 0) {
                    System.out.println("ID can not be dupplicated. Enter again");
                    validInput = false;
                }
                if (ID.trim().equals("")) {
                    System.out.println("ID can't be blank");
                    validInput = false;
                }
            } while (!validInput);

            //ENTER NAME
            do {
                System.out.print("Enter name: ");
                name = in.nextLine();
                validInput = true;
                if (name.trim().equals("")) {
                    System.out.println("Name can't be blank");
                    validInput = false;
                }

            } while (!validInput);

            //ENTER WEIGHT (positive real number)
            do {
                System.out.print("Enter weight: ");
                try {
                    weight = Double.parseDouble(in.nextLine());
                    validInput = true;
                    if (weight <= 0) {
                        System.out.println("Weight must be a positive real number");
                        validInput = false;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid weight!");
                    validInput = false;
                }
            } while (!validInput);

            //ENTER TYPE (must be string)
            do {
                System.out.print("Enter type: ");
                type = in.nextLine();
                validInput = true;
                if (type.trim().equals("") || type.matches(".*[1-9].*")) {
                    System.out.println("Invalid type. Type must be a string and not be a blank");
                    validInput = false;
                }
            } while (!validInput);

            System.out.println("Choose place : ");
            System.out.println("\t1. Freezing shelf");
            System.out.println("\t2. Cooling shelf");
            System.out.println("\t3. Vegetable shelf");
            do {
                try {
                    validInput = true;
                    int responseOption = Integer.parseInt(getConfirmMessage("\tEnter 1 -> 3: "));
                    switch (responseOption) {
                        case 1:
                            place = "freezing shelf";
                            break;
                        case 2:
                            place = "cooling shelf";
                            break;
                        case 3:
                            place = "vegatable shelf";
                            break;
                        default:
                            validInput = false;
                            break;
                    }

                } catch (Exception e) {
                    validInput = false;
                }
            } while (!validInput);

            do {
                    System.out.print("Enter exprire date (dd/MM/yyyy): ");
                    expiredDate = in.nextLine();
                    validInput = true;
                    if(!isValidDate(expiredDate)) {
                        validInput = false;
                        System.out.println("Invalid date");
                    }
            } while (!validInput);

            Food newFood = new Food(ID, name, weight, type, place, expiredDate);
            this.add(newFood);
            System.out.println("ADDED SUCCESSFULLY");

            //CONTINUE 
            do {
                validInput = true;
                String response = getConfirmMessage("Do you want to add more foods (Y/N)? ");
                if (response.equalsIgnoreCase("y")) {
                    continueAdding = true;
                    validInput = true;
                } else if (response.equalsIgnoreCase("N")){
                    continueAdding = false;
                    validInput = true;
                } else {
                    validInput = false;
                }
            } while(!validInput);

        } while (continueAdding);
    }

    public void searchByName() {
        if (this.isEmpty()) {
            System.out.println("There is no food in the refrigirator");
            return;
        }

        String searchedName;
        boolean continueSearching = true;
        do {
            int count = 0;
            do {
                System.out.print("Enter name to search: ");
                searchedName = in.nextLine();
            } while (searchedName.trim().equals(""));

            for (Food food : this) {
                if (food.getName().toLowerCase().contains(searchedName.toLowerCase())) {
                    food.printFood();
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("The food does not exist!");
            }
            
            boolean validInput = true;
            do {
                validInput = true;
                String response = getConfirmMessage("Do you want to continue (Y/N)? ");
                if (response.equalsIgnoreCase("y")) {
                    continueSearching = true;
                    validInput = true;
                } else if (response.equalsIgnoreCase("N")){
                    continueSearching = false;
                    validInput = true;
                } else {
                    validInput = false;
                }
            } while(!validInput);

        } while (continueSearching);
    }

    public void removeByID() {
        if (this.isEmpty()) {
            System.out.println("There is no food in the refrigirator");
            return;
        }
        String removedID;
        do {
            System.out.print("Enter ID to remove: ");
            removedID = in.nextLine();
        } while (removedID.trim().equals(""));

        int index = isExisted(removedID);
        if (index >= 0) {
            boolean validInput = true;
            do {
                String confirm = getConfirmMessage("Are you sure to remove this food (Y/N)?");
                if (confirm.equalsIgnoreCase("y")) {
                    this.remove(index);
                    validInput = true;
                    System.out.println("Succeeded");
                } else if(confirm.equalsIgnoreCase("n")){
                    System.out.println("Failed");
                    validInput = true;
                } else {
                    validInput = false;
                }
            } while(!validInput);
        } else {
            System.out.println("The food doesn't exist");
        }

    }

    public void saveToBinaryFile() {
        PrintWriter pw = null;
        File file = null;
        try {
            file = new File("foods.txt");
            pw = new PrintWriter(file);
            for (Food food : this) {
                String line = food.getID() + ", "
                        + food.getName() + ", "
                        + food.getWeight() + ", "
                        + food.getType() + ", "
                        + food.getPlace() + ", "
                        + food.getExpiredDate();
                pw.println(line);
            }
            System.out.println("Succeeded");
        } catch (FileNotFoundException ex) {
            System.out.println("Failed");
        } finally {
            pw.close();
        }

    }

    public void printAllFoods() {
        if (this.isEmpty()) {
            System.out.println("There is no food to display.");
        } else {
            Collections.sort(this);
            System.out.format("\n%-20s%-20s%-20s%-20s%-20s%-20s\n", "ID", "|NAME", "|WEIGHT", "|TYPE", "|PLACE", "|EXPIRED DATE");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            for(Food food : this) {
                food.printFood();
            }
        }

    }
}
