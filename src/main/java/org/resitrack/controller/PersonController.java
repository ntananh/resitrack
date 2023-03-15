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
    private List<Person> people;
    Scanner scanner = new Scanner(System.in);

    public PersonController() {

    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    /**
     * This function is used to add new one person
     * @return true if add process success and vice versa
     */

    public boolean addNewPerson() {
        System.out.print("Enter person name: ");
        String name = scanner.nextLine();

        Gender gender = getGender();
        Person person = createPerson(name, gender);

        return addPerson(person);
    }

    /**
     * This function is used to show all person be added
     */
    public void showAll() {
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

    /**
     * This function is used to delete one person
     *
     * @param id
     * @return true if delete process success and vice versa
     */

    public boolean deletePersonById(String id) {
        Person personById = findPersonById(id);
        if (personById == null) {
            return false;
        }
        return deletePerson(personById);
    }

    /**
     * This function is used to search one or many people
     */

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

    /**
     * This function is used to delete one or many people
     *
     * @param name
     * @return true if delete process success and vice versa
     */
    public boolean deletePeopleByName(String name) {
        List<Person> peopleByName = findPersonByName(name);
        if (peopleByName.size() < 0) {
            return false;
        }
        return deletePeople(peopleByName);
    }

    /**
     * This function is used to show basic information of one person
     *
     * @param person
     */
    public void printPersonInformation(Person person) {
        System.out.println("Id: " + person.getId());
        System.out.println("Name: " + person.getName());
        System.out.println("Gender: " + person.getGender());
        System.out.println("Dob: " + person.getDob());
    }

    /**
     * This function is used to delete many people
     *
     * @param people
     * @return true if delete people success and vice versa
     */

    private boolean deletePeople(List<Person> people) {
        List<Person> deletePeople = new ArrayList<>();
        for (Person person : people) {
            if (!deletePerson(person)) {
                restorePeople(deletePeople);
                return false;
            }
        }
        return true;
    }

    /**
     * This function is used to restore list people if delete process false
     *
     * @param deletePeople
     */
    private void restorePeople(List<Person> deletePeople) {
        people.addAll(deletePeople);
    }

    /**
     * This function is used to delete one person
     *
     * @param person
     * @return true if delete person success and vice versa
     */
    private boolean deletePerson(Person person) {
        return people.remove(person);
    }

    /**
     * This function is used to find one person or many people by their name
     *
     * @param name
     * @return {@link List<Person>} List<Person> found
     */

    private List<Person> findPersonByName(String name) {
        List<Person> peopleWithName = new ArrayList<>();

        for (Person person : people) {
            if (name.equalsIgnoreCase(person.getName())) {
                peopleWithName.add(person);
            }
        }

        return peopleWithName;
    }

    /**
     * This function is used to find one person by their id
     *
     * @param id
     * @return {@link Person} Person found
     */

    private Person findPersonById(String id) {
        for (Person person : people) {
            if (id.equalsIgnoreCase(person.getId())) {
                return person;
            }
        }
        return null;
    }

    /**
     * This function is used to create one person
     *
     * @param gender
     * @param name
     * @return {@link Person} Person created
     */

    private Person createPerson(String name, Gender gender) {
        if (CommonUtil.isNullOrBlank(name)) {
            return null;
        }
        return new Person(generatePersonId(), name, gender);
    }

    /**
     * This function is used to enter gender for person in add process
     *
     * @return {@link Gender} Gender created
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

    private String generatePersonId() {
        return "P" + count.getAndIncrement();
    }

    /**
     * This function is used to add new person
     *
     * @param person
     * @return true if add person success and vice versa
     */

    private boolean addPerson(Person person) {
        if (person != null) {
            people.add(person);
            return true;
        }
        return false;
    }

}
