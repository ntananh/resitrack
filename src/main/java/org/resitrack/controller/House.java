package org.resitrack.controller;

import java.util.List;

public class House {
    private int apartment_number;
    private String Street_names;
    private static List<House> houses;

    public House(int apartment_number, String street_names){
        this.apartment_number = apartment_number;
        this.Street_names = street_names;

    }

    public House() {

    }

    public int getApartment_number() {
        return apartment_number;
    }

    public void setApartment_number(int apartment_number) {
        this.apartment_number = apartment_number;
    }

    public String getStreet_names() {
        return Street_names;
    }

    public void setStreet_names(String street_names) {
        Street_names = street_names;
    }

    public List<House> getHouses() {
        return houses;
    }

//    public void setHouses(List<House> houses) {
//        House.houses = houses;
//    }


    @Override
    public String toString() {
        return "House{" +
                "apartment_number=" + apartment_number +
                ", Street_names='" + Street_names + '\'' +
                ", familyMember=" + houses +
                '}';
    }
}
