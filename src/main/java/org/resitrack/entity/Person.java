package org.resitrack.entity;

import org.resitrack.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Person {
    private String id;
    private BigDecimal idNumber;
    private String name;
    private Gender sex;
    private LocalDate dob;
}
