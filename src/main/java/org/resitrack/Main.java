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
    private final static List<Town> towns = new ArrayList<>();

    /**
     * So that the manageHouse function can access House through houses
     */
    private final static List<House> houses = new ArrayList<>();

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
                    houseController.deleteHouse();
                    break;
                default:
                    System.out.println("Wrong house manage choice!!!");
            }

        } while (!choice.equals(EXIT));
    }
}