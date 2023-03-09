package org.resitrack.controller;

import org.resitrack.entity.Person;
import org.resitrack.enums.Gender;
import org.resitrack.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static org.resitrack.util.CommonUtil.INITIAL_ID_VALUE;


public class PersonController {
    private static final AtomicInteger count = new AtomicInteger(INITIAL_ID_VALUE);
    private final List<Person> people;
    private final Scanner scanner;

    public PersonController(List<Person> people, Scanner scanner) {
        this.people = people;
        this.scanner = scanner;
        addPerson(new Person("P1", "giang", Gender.GAY));
        addPerson(new Person("P2", "giang", Gender.GAY));
        addPerson(new Person("P3", "giang", Gender.GAY));
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
        String name;
        do {
            System.out.print("> Enter name to search: ");
            name = scanner.nextLine();
        } while (CommonUtil.isNullOrBlank(name));
        List<Person> peopleWithName = null;
        if (!CommonUtil.isNullOrBlank(name)) {
            peopleWithName = findPersonByName(name);
        }

        if (peopleWithName != null) {
            if (peopleWithName.size() > 0)
                peopleWithName.forEach(this::printPersonInformation);
        }
    }

    public void deletePeopleByName() {
        String name;
        do {
            System.out.print("> Enter name to delete: ");
            name = scanner.nextLine();
        } while (CommonUtil.isNullOrBlank(name));
        boolean isDeleted = deletePerson(name);
        if (isDeleted)
            System.out.println("Delete success");
        else
            System.out.println("Delete false");
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

    private boolean deletePerson(String name) {
        boolean flag = false;
        List<Person> peopleWithName = null;
        if (!CommonUtil.isNullOrBlank(name)) {
            peopleWithName = findPersonByName(name);
        }
        if (peopleWithName != null) {
            if (peopleWithName.size() == 1) {
                people.remove(peopleWithName.get(0));
                flag = true;
            } else if (peopleWithName.size() > 0) {
                peopleWithName.forEach(this::printPersonInformation);
                System.out.print("Have " + peopleWithName.size() + " member " + name + " was found !!!" + "\n" +
                        "Please choose ID family member want delete or enter [ ALL ] to delete all:  ");
                String choose = scanner.nextLine();
                if (choose.equalsIgnoreCase("ALL")) {
                    people.removeAll(peopleWithName);
                } else {
                    List<Person> peopleWithId = null;
                    if (!CommonUtil.isNullOrBlank(choose)) {
                        peopleWithId = findPersonById(choose);
                    }
                    if (peopleWithId != null) {
                        people.remove(peopleWithId.get(0));
                        flag = true;
                    } else {
                        System.out.println("ID Invalid ");
                    }

                }
            }
        }
        return flag;
    }

    private List<Person> findPersonById(String id) {
        List<Person> peopleWithName = new ArrayList<>();

        for (Person person : people) {
            if (id.equalsIgnoreCase(person.getId())) {
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
