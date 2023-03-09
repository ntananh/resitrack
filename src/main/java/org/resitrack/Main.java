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

        PersonController personController;
        HouseController houseController;
        TownController townController;

        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            MenuUtil.mainMenu();
            System.out.print("> Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case MANAGE_PERSON:
                    personController = new PersonController(people, scanner);
                    managePerson(personController, scanner);
                    break;
                case MANAGE_HOUSE:
                    houseController = new HouseController();
                    System.out.println("Manage house");
                    break;
                case MANAGE_TOWN:
                    townController = new TownController();
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
                case "3":
                    personController.deletePeopleByName();
                    break;
                default:
                    System.out.println("Wrong person manage choice!!!");
            }

        } while (!choice.equals(EXIT));
    }
}