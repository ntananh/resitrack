package org.resitrack.entity;

import org.resitrack.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Person {
    private String id;
    private BigDecimal idNumber;
    private String name;
    private Gender gender;
    private LocalDate dob;

    public Person(){
    }

    public Person(String id, String name, Gender gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIdNumber() {
        if (idNumber == null)
            return "Not have";
        else
            return String.valueOf(idNumber);
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + gender +
                ", dob=" + dob +
                '}';
    }
}
