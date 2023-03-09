package org.resitrack.entity;

import java.util.List;

public class House {
    private List<Person> members;
    private int numberHouse;
    private String streetName;

    public House(int numberHouse, String streetName) {
        this.numberHouse = numberHouse;
        this.streetName = streetName;
    }

    public int getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(int numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "House{" +
                "numberHouse=" + numberHouse +
                ", streetName='" + streetName + '\'' +
                '}';
    }
}
