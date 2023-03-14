package org.resitrack.controller;

import org.resitrack.entity.Person;
import org.resitrack.enums.Gender;
import org.resitrack.util.CommonUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static org.resitrack.util.CommonUtil.INITIAL_ID_VALUE;


public class PersonController {
    private static final AtomicInteger count = new AtomicInteger(INITIAL_ID_VALUE);
    private final List<Person> people;
    private final Scanner scanner;
    CommonUtil commonUtil = new CommonUtil();

    public PersonController(List<Person> people, Scanner scanner) {
        this.people = people;
        this.scanner = scanner;
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
            else
                System.out.println("Not found ! ");
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
        System.out.println("IdNumber: " + person.showIdNumber());
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
                    Person person = null;
                    if (!CommonUtil.isNullOrBlank(choose)) {
                        person = findPersonById(choose);
                    }
                    if (person != null) {
                        people.remove(person);
                        flag = true;
                    } else {
                        System.out.println("ID Invalid ");
                    }

                }
            }
        }
        return flag;
    }

    private Person findPersonById(String id) {
        for (Person person : people) {
            if (id.equalsIgnoreCase(person.getId())) {
                return person;
            }
        }
        return null;
    }

    public void updatePerson() {
        System.out.print("Enter idPerson need to Update: ");
        String id = scanner.nextLine();
        if (editPersonById(id)) {
            System.out.println("Update success !");
        } else
            System.out.println("Update false !");
    }

    private boolean editPersonById(String id) {
        Person person = null;
        if (!CommonUtil.isNullOrBlank(id)) {
            person = findPersonById(id);
        }
        if (person != null) {
            String name;
            System.out.println("Pls enter new Information, Empty to keep stable");
            do {
                System.out.print("Enter new name: ");
                name = scanner.nextLine();
            } while (CommonUtil.isNullOrBlank(name));
            person.setName(name);
            Gender gender = getGender();
            person.setGender(gender);
            System.out.print("Enter idNumber: ");
            BigDecimal idNumber = BigDecimal.valueOf(scanner.nextLong());
            person.setIdNumber(idNumber);
            person.setDob(getDate());
            return true;
        } else {
            System.out.println("Not found idPerson need to edit !!!");
        }
        return false;
    }

    private LocalDate getDate() {
        LocalDate newBirthDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (newBirthDate == null) {
            System.out.print("Enter new birth date (DD/MM/YYYY): ");
            scanner.nextLine();
            String newBirthDateStr = scanner.nextLine();
            try {
                newBirthDate = LocalDate.parse(newBirthDateStr, formatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in the format DD/MM/YYYY.");
            }
        }
        return newBirthDate;
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
