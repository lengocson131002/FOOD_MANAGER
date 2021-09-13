/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * @author lengo
 */
public class Food implements Comparable<Food>{
 
    private String ID;
    private String name;
    private double weight;
    private String type;
    private String place;
    private String expiredDate;

    public Food() {
    }

    public Food(String ID, String name, double weight, String type, String place, String expiredDate) {
        this.ID = ID;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.expiredDate = expiredDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public void printFood() {
        System.out.format("%-20s%-20s%-20f%-20s%-20s%-20s\n", this.ID, this.name, this.weight, this.type, this.place, this.expiredDate);
    }   

    @Override
    public int compareTo(Food f) {
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
         int result = 0;
        try {
             result =  -(sdf.parse(this.getExpiredDate()).compareTo(sdf.parse(f.getExpiredDate())));
        } catch (ParseException ex) {
           ex.printStackTrace();
        }
        return result;
    }
  
}