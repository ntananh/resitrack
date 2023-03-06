package org.resitrack.controls;

import org.resitrack.entity.House;
import org.resitrack.entity.Person;
import org.resitrack.enums.Gender;

import java.util.Scanner;

public class PersonManagement {
    static Scanner sc = new Scanner(System.in);
    House house = new House();
    public Person addOnePeopleToHouse() {
        Person person;
        Gender gender = null;
        String name, genderoption;
        boolean flag = false;
        do {
            if (!flag) { // flag = true
                System.out.print("Pls enter name: ");
            } else {
                System.out.print("Pls re-enter new name: ");
            }
            name = sc.nextLine();
            if (isNotValidName(name)) {
                System.out.println("Invalid Name ! ");
            }
            if (searchPersonByName(name) != null) {
                flag = true;
            } else {
                flag = false;
            }
        } while (flag || isNotValidName(name) == true);
        System.out.println("Enter 1 [MEN]  |  2 [WOMAN]  |  3 [LESS] to choose sex: ");
        genderoption = sc.nextLine();
        if (Integer.parseInt(genderoption) == 1) {
            gender = Gender.MALE;
        } else if (Integer.parseInt(genderoption) == 2) {
            gender = Gender.FEMALE;
        } else if (Integer.parseInt(genderoption) == 3) {
            gender = Gender.GAY;
        } else {
            System.out.println("Error !!!");
        }
        return person = new Person(name, gender);
    }
    public Person searchPersonByName (String name){
        for (Person person : house.getFamilyMember())  {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }
    private boolean isValidatePerson (Person member){
        return member.getName() != null && member.getSex() != null;
    }
    private boolean isNotValidName (String name){
        if (name.isEmpty() || name.trim().length() < 1) {
            return true;
        }
        return false;
    }
    public void printInformation(){
        house.getFamilyMember().forEach(System.out::println);
    }
}
