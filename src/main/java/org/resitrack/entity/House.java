package org.resitrack.entity;

import java.util.ArrayList;
import java.util.List;

public class House {
    private String id;
    private String houseNumber;
    private String householdId;
    private String townId;
    private List<Person> members = new ArrayList<>();

    public House(String houseNumber, String townId) {
        this.id = "HID" + houseNumber;
        this.houseNumber = houseNumber;
        this.townId = townId;
    }

    public House() {
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

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    public List<Person> getMembers() {
        return members;
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
