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
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        List<House> houses = new ArrayList<>();
        List<Town> towns = new ArrayList<>();

        PersonController personController = new PersonController();
        HouseController houseController = new HouseController();
        TownController townController = new TownController();

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
                    System.out.println("Manage house");
                    break;
                case MANAGE_TOWN:
                    townController = new TownController();
                    System.out.println("Manage town");
                    break;
                default:
                    System.out.println("Wrong choice, type again!");
            }

        } while (!choice.equals(EXIT_MAIN_MENU));
    }

    public static void managePerson(PersonController personController, Scanner scanner) {
        String choice;
        do {
            personMenu();
            System.out.print("> Enter your manage person choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case SHOW_ALL_PERSON:
                    System.out.println("Show all person ");
                    personController.showAll();
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
                default:
                    System.out.println("Wrong person manage choice!!!");
            }

        } while (!choice.equals(EXIT_PERSON_MENU));
    }
}