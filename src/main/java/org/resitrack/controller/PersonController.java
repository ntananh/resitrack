package org.resitrack.controller;

import org.resitrack.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonController {

    /**
     * Used to create a list of people.
     * Helps users to store people that have just been saved to house
     */
    private List<Person> people;

    public PersonController() {

    }

    public void setPeople(List<Person> people) {
        this.people = people;
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
     * @param id come from value that user enter from keyboard, value of it is String
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
     * This function is used to delete one or many people
     *
     * @param name come from value that user enter from keyboard, value of it is String
     * @return true if delete process success and vice versa
     */
    public boolean deletePeopleByName(String name) {
        List<Person> peopleByName = findPersonByName(name);
        if (peopleByName.size() == 0) {
            return false;
        }
        return deletePeople(peopleByName);
    }

    /**
     * This function is used to delete many people
     *
     * @param people come from list people that user want to delete, value of it is List<Person>
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
     * @param deletePeople come from list people that user want to delete, value of it is List<Person>
     */
    private void restorePeople(List<Person> deletePeople) {
        people.addAll(deletePeople);
    }

    /**
     * This function is used to delete one person
     *
     * @param person come from Person object that user want to delete, value of it is Person
     * @return true if delete person success and vice versa
     */
    private boolean deletePerson(Person person) {
        return people.remove(person);
    }

    /**
     * This function is used to find one person or many people by their name
     *
     * @param name come from value that user enter from keyboard , Value of it is String
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
     * @param id come from value that user enter from keyboard , Value of it is String
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
}
