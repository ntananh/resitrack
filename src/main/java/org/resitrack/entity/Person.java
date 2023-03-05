package org.resitrack.entity;
import java.util.Scanner;
import org.resitrack.enums.Gender;
import java.util.Date;
public class Person {
    static Scanner sc = new Scanner(System.in);
    private String name;
    private Gender sex;
    private Date dob;
    private long idNumber;
    private String job;

    public Person() {
    }

    public Person(String name, Gender sex) {
        this.name = name;
        this.sex = sex;
    }
}
