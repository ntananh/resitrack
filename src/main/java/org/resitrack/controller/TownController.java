package org.resitrack.controller;

import org.resitrack.entity.Town;

import javax.sql.rowset.spi.TransactionalWriter;
import java.util.List;

public class TownController {
    private List<Town> towns;

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }

    public Town getTownById(String townId) {
        return towns.stream()
                .filter(town -> townId.equalsIgnoreCase(town.getId()))
                .findAny()
                .get();
    }

    public void printAllTownInformation() {
        for (Town town: towns) {
            System.out.print("Id: " + town.getId() + " Name:" + town.getName() + "\n");
        }
        System.out.println();
    }

    public boolean isTownExisted(String id) {
        return towns.stream().anyMatch(town -> id.equalsIgnoreCase(town.getId()));
    }

    public int getNumberOfTown() {
        return towns.size();
    }
}
