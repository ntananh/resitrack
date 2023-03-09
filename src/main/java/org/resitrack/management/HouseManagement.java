package org.resitrack.management;

import org.resitrack.entity.House;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseManagement {
    static Scanner sc = new Scanner(System.in);
    private static List<House> houses = new ArrayList<>();

    public static void addHouse() {
        int numberHouse;
        String streetNames;
        String optionTwo;
        House house;
        System.out.println("Enter [o] to add one person , enter [m] to add multiple people:");
        optionTwo = sc.nextLine();

        if (optionTwo.equals("o")) {

            System.out.println("\t Enter number house:");
            numberHouse = sc.nextInt();

            if (numberHouse > 0) {
                System.out.println("\t Enter street name:");
                streetNames = sc.nextLine();
                sc.nextLine();
                if (isNotValidStreetName(streetNames)) {
                    System.out.println(" Invalid street name");
                }else{
                    house = new House(numberHouse, streetNames);
                    houses.add(house);
                    System.out.println("Your home information has been saved.");
                }
            }else {
                System.out.println("Invalid apartment number. your home is not saved");
            }
        }if (optionTwo.equals("m")){
            int numberOfHouse;
            System.out.println("Please enter the number of houses to add, attention: the number must be greater than 1: ");
            numberOfHouse = sc.nextInt();
            if (numberOfHouse > 1){
                for (int i = 1; i <= numberOfHouse; i++){
                    System.out.println("House " + i + ": ");
                    System.out.print("\t Enter number House: ");
                    numberHouse = sc.nextInt();
                    if (numberHouse > 0){
                        System.out.print("\t Enter street name: ");
                        streetNames = sc.nextLine();
                        sc.nextLine();
                        if (isNotValidStreetName(streetNames)){
                            System.out.println(" Invalid street name");
                        }else{
                            house = new House(numberHouse, streetNames);
                            houses.add(house);
                            System.out.println("Your home information has been saved.");
                        }
                    }else {
                        System.out.println("Invalid apartment number. your home is not saved");
                    }
                }
            }else{
                System.out.println("");
            }

        }
    }
    private static boolean isNotValidStreetName(String streetNames) {

        if (streetNames.isEmpty() || streetNames.trim().length() < 1) {
            return false;
        }
        return true;
    }
}
