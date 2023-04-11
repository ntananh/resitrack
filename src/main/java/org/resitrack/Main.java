package org.resitrack;

import org.resitrack.controller.HouseController;
import org.resitrack.controller.PersonController;
import org.resitrack.controller.TownController;
import org.resitrack.entity.House;
import org.resitrack.entity.Person;
import org.resitrack.entity.Town;
import org.resitrack.util.MenuUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.resitrack.util.MenuUtil.*;

public class Main {

    /**
     * So that the manageHouse function can access Town through towns
     */
    private final static List<Town> towns = new ArrayList<>(Arrays.asList(new Town("T1", "HCM"), new Town("T2", "HN"), new Town("T3", "DN")));

    /**
     * So that the manageHouse function can access House through houses
     */
    private final static List<House> houses = new ArrayList<>(Arrays.asList(new House("12", "T1"),
            new House("12", "T2"), new House("12", "T3"),
            new House("13", "T2"), new House("14", "T3"), new House("13", "T3")));

    /**
     * So that the manageHouse function can access TownController through townController
     */
    private final static TownController townController = new TownController();

    /**
     * So that the manageHouse function can access HouseController through houseController
     */
    private final static HouseController houseController = new HouseController();



    public static void main(String[] args) {

        List<Person> people = new ArrayList<>();
        PersonController personController = new PersonController();

        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            MenuUtil.mainMenu();
            System.out.print("> Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case MANAGE_PERSON:
                    personController.setPeople(people);
                    managePerson(personController, scanner);
                    break;
                case MANAGE_HOUSE:
                    manageHouse(scanner);
                    break;
                case MANAGE_TOWN:
                    townController.setTowns(towns);
                    System.out.println("Manage town");
                    break;
                default:
                    System.out.println("Wrong choice, type again!");
            }

        } while (!choice.equals(EXIT));
    }

    public static void managePerson(PersonController personController, Scanner scanner) {
        String choice;
        do {
            personMenu();
            System.out.print("> Enter your manage person choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    personController.addNewPerson();
                    break;
                case "2":
                    personController.searchPeopleByName();
                    break;
                default:
                    System.out.println("Wrong person manage choice!!!");
            }

        } while (!choice.equals(EXIT));
    }

    public static void manageHouse(Scanner scanner) {
        String choice;

        houseController.setHouses(houses);
        townController.setTowns(towns);
        houseController.setTownController(townController);

        do {
            houseMenu();
            System.out.print("> Enter your manage house choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case ADD_HOUSE:
                    houseController.createNewHouse();
                    break;
                case DELETE_HOUSE:

                    String townId;

                    System.out.println("> Enter 0 to exit, or press any key to continue!.");
                    choice = scanner.nextLine();

                    if (choice.equalsIgnoreCase(EXIT)) {
                        break;
                    }

                    if (towns.size() <= 0){
                        System.out.println("The town is empty, this function cannot be performed." +
                                " Please create a new town first");
                    }

                    System.out.println("List of town id:");
                    townController.printAllTownInformation();

                    System.out.print("Enter town id, or enter 0 to exit: ");
                    townId = scanner.nextLine();

                    if (townId.equalsIgnoreCase(EXIT)){
                        System.out.println("\nNo successful house removal");
                        return;
                    }

                    while (!townController.isTownExisted(townId)) {
                        System.out.println("Town id: " + townId + " not found, please enter correct town id in list bellow: ");
                        townController.printAllTownInformation();

                        System.out.print("Please enter town id again here: ");
                        townId = scanner.nextLine();
                    }

                    houseController.deleteHouse(townId);

                    break;
                default:
                    System.out.println("Wrong house manage choice!!!");
            }
        } while (!choice.equals(EXIT));
    }
}