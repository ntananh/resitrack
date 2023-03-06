package org.resitrack;

import org.resitrack.controls.HouseManagement;
import org.resitrack.controls.PersonManagement;
import org.resitrack.entity.House;
import org.resitrack.entity.Person;

public class Main {
    public static void main(String[] args) {
        House house = new House();
        HouseManagement houseManagement = new HouseManagement();
        houseManagement.addPersonToHouse();
        PersonManagement personManagement = new PersonManagement();
//        if(house.getFamilyMember() != null){
        personManagement.printInformation();
//        }
    }
}