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
                case ADD_PERSON:
                    System.out.println("Adding new person");
                    boolean personBeAdded = personController.addNewPerson();
                    if (personBeAdded)
                        System.out.println("Add success !");
                    else
                        System.out.println("Add false !");
                    break;
                case SHOW_ALL_PERSON:
                    System.out.println("Show all person ");
                    personController.showAll();
                    break;
                case SEARCH_PERSON:
                    personController.searchPeopleByName();
                    break;
                case DELETE_PERSON:
                    System.out.println("Delete function: ");
                    System.out.println("1. Delete person by ID");
                    System.out.println("2. Delete people by name");
                    System.out.println("> Enter your choose: ");
                    String choose = scanner.nextLine();
                    if (choose.equals("1")) {
                        System.out.print("Enter IdPerson want to delete: ");
                        String id = scanner.nextLine();
                        if(personController.deletePersonById(id)){
                            System.out.println("Delete success !");
                        }else {
                            System.out.println("Delete false !");
                        }
                    } else if (choose.equals("2")) {
                        System.out.print("Enter Name Person want to delete: ");
                        String name = scanner.nextLine();
                        if(personController.deletePeopleByName(name)){
                            System.out.println("Delete success !");
                        }else {
                            System.out.println("Delete false !");
                        }
                    }else {
                        System.out.println("Not found ");
                    }
                    break;
                default:
                    System.out.println("Wrong person manage choice!!!");
            }

        } while (!choice.equals(EXIT_PERSON_MENU));
    }
}