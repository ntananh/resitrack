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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Member : " + name + "\n" + "Gender : " + sex + "\n"
                + "Birthday : " + dob + "\n" + "Citizen ID " + idNumber + "\n"
                + "Job : " + job;
    }
}
