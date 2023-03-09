package org.resitrack.entity;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Town {
    private String idTown;
    private String nameTown;
    private long areaTown;
    private List<House> houses;

    public Town (String id, String nameTown, long areaTown) {
        this.idTown = id;
        this.nameTown = nameTown;
        this.areaTown = areaTown;
        houses = new ArrayList<>();
    }

    public String getIdTown() {
        return idTown;
    }

    public String getNameTown() {
        return nameTown;
    }

    public long getAreaTown() {
        return areaTown;
    }

    public void setIdTown(String idTown) {
        this.idTown = idTown;
    }

    public void setNameTown(String nameTown) {
        this.nameTown = nameTown;
    }

    public void setAreaTown(long areaTown) {
        this.areaTown = areaTown;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
}
