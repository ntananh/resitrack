package org.resitrack.entity;
public class House {
    private int numberHouse;
    private String householdHead;
    public int getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(int numberHouse) {
        this.numberHouse = numberHouse;
    }

    public String getHouseholdHead() {
        return householdHead;
    }

    public void setHouseholdHead(String householdHead) {
        this.householdHead = householdHead;
    }

    @Override
    public String toString() {
        return "Number house: " + numberHouse + "\n"+
                "Name of householder: " + householdHead + "\n";
    }
}
