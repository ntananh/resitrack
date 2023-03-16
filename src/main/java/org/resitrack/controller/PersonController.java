package org.resitrack.controller;

import org.resitrack.entity.Person;
import org.resitrack.enums.Gender;
import org.resitrack.util.CommonUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
public class PersonController {
    private final List<Person> people;
    private final Scanner scanner;

    public PersonController(List<Person> people, Scanner scanner) {
        this.people = people;
        this.scanner = scanner;
    }

    private Person findPersonById(String id) {
        for (Person person : people) {
            if (id.equalsIgnoreCase(person.getId())) {
                return person;
            }
        }
        return null;
    }

    /**
     * Update information for a person with the given ID.
     *
     * @param id The ID of the person to update.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updatePerson(String id) {
        boolean flag = false;
        Person person = findPersonById(id);
        try {
            editPerson(person);
            flag = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    /**
     * Edit the information for the given person.
     *
     * @param person The person to edit.
     * @return {@link Person} The edited person.
     * @throws IllegalArgumentException If the person is null.
     */
    private Person editPerson(Person person) {
        if (person == null)
            throw new IllegalArgumentException("Not found person id need to edit !!!");
        enterNewPersonInfo(person);
        return person;
    }

    /**
     * Prompt the user to enter new information for the given person.
     *
     * @param person The person to update.
     * @return {@link Person} The updated person.
     */
    private Person enterNewPersonInfo(Person person) {
        String name;
        System.out.println("Please enter new information: ");
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
        return person;
    }

    /**
     * Prompt the user to enter a birthday in the format DD/MM/YYYY.
     *
     * @return {@link LocalDate} The birthday entered by the user.
     */
    private LocalDate getDate() {
        LocalDate newBirthDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            System.out.print("Enter new birth date (DD/MM/YYYY): ");
            String newBirthDateStr = scanner.nextLine();
            try {
                newBirthDate = LocalDate.parse(newBirthDateStr, formatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in the format DD/MM/YYYY.");
            }
        } while (newBirthDate == null);
        return newBirthDate;
    }

    /**
     * Prompts the user to select a gender from a list of options and returns the selected gender as a Gender object.
     *
     * @return the selected gender as a Gender object
     */
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
}
