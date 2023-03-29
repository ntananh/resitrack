package org.resitrack;

import org.resitrack.controller.HouseController;
import org.resitrack.controller.PersonController;
import org.resitrack.controller.TownController;
import org.resitrack.entity.House;
import org.resitrack.entity.Person;
import org.resitrack.entity.Town;
import org.resitrack.util.MenuUtil;

import java.util.ArrayList;
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
    private final static List<Person> people = new ArrayList<>();
    private final static PersonController personController = new PersonController();
    public static void main(String[] args) {
        House house = new House("6B", "T1");
        houses.add(house);
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            MenuUtil.mainMenu();
            System.out.print("> Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case MANAGE_PERSON:
                    personController.setPeople(people);
                    managePerson(scanner);
                    break;
                case MANAGE_HOUSE:
                    manageHouse(scanner);
                    break;
                case MANAGE_TOWN:
                    townController.setTowns(towns);
                    System.out.println("Manage town");
                    break;
                case EXIT_MAIN_MENU:
                    break;
                default:
                    System.out.println("Wrong choice, type again!");
            }

        } while (!choice.equals(EXIT_MAIN_MENU));
    }

    public static void managePerson(Scanner scanner) {
        personController.setHouseController(houseController);
        personController.setPeople(people);
        houseController.setHouses(houses);
        String choice;
        do {
            System.out.println("Manage Person");
            System.out.println("1. Manage all people");
            System.out.println("2. Manage members family");
            System.out.println("3. Exit function ");
            System.out.print("> Enter your choice: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    String subChoice;
                    do {
                        allPersonMenu();
                        System.out.print("> Enter your all manage person choice: ");
                        subChoice = scanner.nextLine();

                        switch (subChoice) {
                            case ADD_PERSON:
                                personController.addNewPerson();
                                break;
                            case SHOW_ALL_PERSON:
                                System.out.println("Show all person ");
                                personController.showAll(people);
                                break;
                            case SEARCH_PERSON:
                                personController.searchPeopleByName();
                                break;
                            case DELETE_PERSON:
                                String choose;
                                do {
                                    System.out.println("Delete function: ");
                                    System.out.println("1. Delete person by ID");
                                    System.out.println("2. Delete people by name");
                                    System.out.println("3. Exit function ");
                                    System.out.print("> Enter your choice: ");
                                    choose = scanner.nextLine();
                                    switch (choose) {
                                        case DELETE_PERSON_BY_ID:
                                            System.out.print("Enter person id want to delete: ");
                                            String id = scanner.nextLine();
                                            boolean deletedPerson = personController.deletePersonById(id);
                                            String logPerson = deletedPerson ? "Delete success !" : "Delete False";
                                            System.out.println(logPerson);
                                            break;
                                        case DELETE_PERSON_BY_NAME:
                                            System.out.print("Enter Name Person want to delete: ");
                                            String name = scanner.nextLine();
                                            boolean deletedPeople = personController.deletePeopleByName(name);
                                            String logPeople = deletedPeople ? "Delete success !" : "Delete False";
                                            System.out.println(logPeople);
                                            break;
                                        case EXIT_DELETE_PERSON_MENU:
                                            break;
                                        default:
                                            System.out.println("Wrong choose !!!");
                                    }
                                } while (!choose.equals(EXIT_DELETE_PERSON_MENU));
                                break;
                            case EXIT_ALL_PERSON_MENU:
                                break;
                            default:
                                System.out.println("Wrong all person manage choice!!!");
                        }

                    } while (!subChoice.equals(EXIT_ALL_PERSON_MENU));
                    break;
                case "2":
                    String subChoiceTwo;
                    do {
                        housePersonMenu();
                        System.out.print("> Enter your house manage person choice: ");
                        subChoiceTwo = scanner.nextLine();
                        switch (subChoiceTwo) {
                            case "1":
                                String logMessage = personController.addPeopleToHouse() ? "Add people to house success !" : "Add people to house false";
                                System.out.println(logMessage);
                                break;
                            case "2":
                                String logMessageDelete = personController.removePeopleToHouse() ? "Delete success !" : "Delete false";
                                System.out.println(logMessageDelete);
                                break;
                            case "3":
                                personController.showPeopleInHouse();
                                break;
                            case EXIT_HOUSE_PERSON_MENU:
                                break;
                            default:
                                System.out.println("Wrong house person manage choice!!!");
                        }
                    } while (!subChoiceTwo.equals(EXIT_HOUSE_PERSON_MENU));
                case "3":
                    break;
                default:
                    System.out.println("Wrong person manage choice!!!");
            }
        } while (!choice.equals("3"));
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
                case EXIT_HOUSE_MENU:
                    break;
                default:
                    System.out.println("Wrong person manage choice!!!");
            }

        } while (!choice.equals(EXIT_HOUSE_MENU));
    }
}