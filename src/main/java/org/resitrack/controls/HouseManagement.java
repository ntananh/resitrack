package org.resitrack.controls;

import org.resitrack.entity.House;
import org.resitrack.interfaces.IStorage;

public class HouseManagement {
    PersonManagement personManagement = new PersonManagement();
    private IStorage storage;
    private House house = new House();
    public HouseManagement(){
        this.storage = storage;
    }
    public void addPersonToHouse(){
        house.getFamilyMember().add(personManagement.addOnePeopleToHouse());
    }
    public void saveToFile(){storage.save();}
}
