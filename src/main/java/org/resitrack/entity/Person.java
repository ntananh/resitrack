package org.resitrack.entity;
import org.resitrack.enums.Gender;
import java.util.Date;
public class Person {
    private String name;
    private Gender sex;
    private Date dob;
    private long idNumber;
    private String job;
    private House house;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Person() {
    }

    public Person(String name, Gender sex, House house) {
        this.name = name;
        this.sex = sex;
        this.house = house;
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
                + "Birthday : " + dob + "\n" + "Citizen ID: " + idNumber + "\n"
                + "Job : " + job + "\n"+"House Number: " + house.getNumberHouse() + "\n";
    }
}
