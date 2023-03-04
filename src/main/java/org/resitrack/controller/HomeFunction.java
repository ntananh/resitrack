package org.resitrack.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeFunction {

    static Scanner sc = new Scanner(System.in);
    public List<House> Houses;

    public List<House> getHouses() {
        return Houses;
    }

    public static void addHouse(){

        String optionTwo;
        System.out.println("Enter [o] to add one person , enter [m] to add multiple people:");
        optionTwo = sc.nextLine();

        if (optionTwo.equals("o")) {
            House house;
            int apartment_number;
            String street_names;
            System.out.println("Enter apartment number:");
            apartment_number = sc.nextInt();

            if (apartment_number > 0) {
                System.out.println("Enter street name:");
                street_names = sc.nextLine();
                sc.nextLine();
                if (isNotValidStreetName(street_names)) {
                    System.out.println(" Invalid street name");
                }else{
                    System.out.println("Your home information has been saved.");
                }
            }else {
                System.out.println("Invalid apartment number.");
            }
        }
//        if (optionTwo.equals("m")){
//
//        }
    }
    private static boolean isNotValidStreetName(String street_names){

        if (street_names.isEmpty() || street_names.trim().length() < 1){
            return false;
        }
        return true;
    }
}
