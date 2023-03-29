package org.resitrack.controller;
import org.resitrack.entity.House;
import org.resitrack.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static org.resitrack.util.CommonUtil.INITIAL_ID_VALUE;


public class PersonController {

    /**
     * Used to create a list of people.
     * Helps users to store people that have just been saved to house
     */
    private List<Person> people;
    HouseController houseController;
    private static final AtomicInteger count = new AtomicInteger(INITIAL_ID_VALUE);
    private final Scanner scanner;

    public PersonController() {
        this.scanner = new Scanner(System.in);
    }

    public void setHouseController(HouseController houseController) {
        this.houseController = houseController;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    /**
     * This function is used to show all person be added
     */
    public void showAll(List<Person> people) {
        if (people.size() < 0 || people.size() == 0)
            System.out.println("List empty ! ");
        else {
            System.out.format("| %-4s | %-20s | %-10s | %-6s | %-12s |\n", "ID", "Name", "ID Number", "Sex", "Date of Birth");
            System.out.println("---------------------------------------------------------------------");
            for (Person person : people) {
                System.out.format("| %-4s | %-20s | %-10s | %-6s | %-12s  |\n",
                        person.getId(),
                        person.getName(),
                        person.getIdNumber(),
                        person.getGender(),
                        person.getDob().toString()
                );
            }
            System.out.println("---------------------------------------------------------------------");
        }
    }

    /**
     * This function is used to find one person by their id
     *
     * @param id come from value that user enter from keyboard , Value of it is String
     * @return {@link Person} Person found
     */
    public Person findPersonById(String id) {
        for (Person person : people) {
            if (id.equalsIgnoreCase(person.getId())) {
                return person;
            }
        }
        return null;
    }

    /**
     * This function is used to add one person or many people into house
     * @return true if adding process success or false if on the contrary
     */
    public boolean addPeopleToHouse() {
        House house = getHouse();
        if (house == null)
            return false;
        List<Person> peopleAdd = personAddHouse();
        if (peopleAdd == null)
            return false;
        else {
            for (Person newMember : peopleAdd) {
                if (house.getMembers().contains(newMember)) {
                    System.out.println("People existed ! ");
                    return false;
                }
            }
            house.getMembers().addAll(peopleAdd);
            return true;
        }
    }

    /**
     * This function is used to get people need to add house
     * @return {@link List<Person>} People found
     */
    private List<Person> personAddHouse() {
        if (people.size() == 0) {
            System.out.println("List not have anyone! Please add people before adding a house.");
            return null;
        }

        List<Person> personAdd = new ArrayList<>();
        String choice;
        Person person;

        System.out.println("List all available people:");
        showAll(people);

        do {
            String personId;
            do {
                System.out.print("Enter person ID: ");
                personId = scanner.nextLine();
                person = findPersonById(personId);

                if (person == null) {
                    System.out.println("No person found with ID " + personId + ". Please try again.");
                }
            } while (person == null);
            personAdd.add(person);
            System.out.println("> Enter 0 to exit or any key to add another person.");
            choice = scanner.nextLine();
        } while (!choice.equalsIgnoreCase("0"));

        return personAdd;
    }

    /**
     * This function is used to get House need to add people into
     * @return {@link House} House found
     */
    private House getHouse() {
        if (houseController.getAllNumberOfHouse() <= 0) {
            System.out.println("List house empty ");
            return null;
        }
        System.out.println("List all house available: ");
        houseController.printAllHouseInformation();
        House house;
        do {
            System.out.print("Enter ID House: ");
            String houseId = scanner.nextLine();
            house = houseController.findHouseById(houseId);
            if (house == null) {
                System.out.println("Not Found");
            }
        } while (house == null);
        return house;
    }

}
