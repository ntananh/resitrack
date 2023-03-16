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
}
