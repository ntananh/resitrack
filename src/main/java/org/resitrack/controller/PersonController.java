package org.resitrack.controller;

import org.resitrack.entity.Person;
import org.resitrack.enums.Gender;
import org.resitrack.util.CommonUtil;

import static org.resitrack.util.CommonUtil.INITIAL_ID_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class PersonController {
    private static final AtomicInteger count = new AtomicInteger(INITIAL_ID_VALUE);
    private List<Person> people;
    private final Scanner scanner;

    public PersonController() {
        this.scanner = new Scanner(System.in);
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public void addNewPerson() {
        System.out.println("Adding new person");
        System.out.print("Enter person name: ");
        String name = scanner.nextLine();

        Gender gender = getGender();
        Person person = createPerson(name, gender);

        if (!addPerson(person)) {
            System.out.println("Failed to add a new person");
        } else {
            System.out.println("Added person: " + person);
        }
    }

    public void searchPeopleByName() {
        System.out.print("> Enter name to search: ");
        String name = scanner.nextLine();
        List<Person> peopleWithName = null;
        if (!CommonUtil.isNullOrBlank(name)) {
            peopleWithName = findPersonByName(name);
        }

        if (peopleWithName != null) {
            if (peopleWithName.size() > 0)
                peopleWithName.forEach(this::printPersonInformation);
        }
    }

    public void printPersonInformation(Person person) {
        System.out.println("Id: " + person.getId());
        System.out.println("Name: " + person.getName());
        System.out.println("Gender: " + person.getGender());
        System.out.println("Dob: " + person.getDob());
    }

    private List<Person> findPersonByName(String name) {
        List<Person> peopleWithName = new ArrayList<>();

        for (Person person : people) {
            if (name.equalsIgnoreCase(person.getName())) {
                peopleWithName.add(person);
            }
        }

        return peopleWithName;
    }

    private Person createPerson(String name, Gender gender) {
        if (CommonUtil.isNullOrBlank(name)) {
            return null;
        }
        return new Person(generatePersonId(), name, gender);
    }

    private Gender getGender() {
        String genderChoice;
        Gender gender = null;
        do {
            System.out.print("> Choose gender [ MALE(1) | FEMALE(2) | GAY(3) ]: ");
            genderChoice = scanner.nextLine();
            switch (genderChoice) {
                case "1":
                    gender = Gender.MALE;
                    break;
                case "2":
                    gender = Gender.FEMALE;
                    break;
                case "3":
                    gender = Gender.GAY;
                    break;
                default:
                    System.out.println("Wrong choice, type again!");
            }
        } while (gender == null);

        return gender;
    }

    private String generatePersonId() {
        return "P" + count.getAndIncrement();
    }

    private boolean addPerson(Person person) {
        if (person != null) {
            people.add(person);
            return true;
        }
        return false;
    }

}
