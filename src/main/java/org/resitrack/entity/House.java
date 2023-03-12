package org.resitrack.entity;

import java.util.List;

public class House {
    private String id;
    private String houseNumber;
    private String streetName;
    private String householdId;
    private String townId;
    private List<Person> members;

    public House(String houseNumber, String streetName, String townId) {
        this.id = "HID" + houseNumber;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.townId = townId;
    }

    public String getId() {
        return id;
    }

    public String getTownId() {
        return townId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    @Override
    public String toString() {
        return "House{" +
                "id='" + id + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", townId='" + townId + '\'' +
                '}';
    }
}
