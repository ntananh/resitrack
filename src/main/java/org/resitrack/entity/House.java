package org.resitrack.entity;

import java.util.List;

public class House {
    private String id;
    private String houseNumber;
    private String householdId;
    private String townId;
    private List<Person> members;

    public House(String houseNumber, String townId) {
        this.id = "HID" + houseNumber;
        this.houseNumber = houseNumber;
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

    @Override
    public String toString() {
        return "House{" +
                "id='" + id + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", townId='" + townId + '\'' +
                '}';
    }
}
