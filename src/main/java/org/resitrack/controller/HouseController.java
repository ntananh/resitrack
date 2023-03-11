package org.resitrack.controller;

import org.resitrack.entity.House;
import org.resitrack.entity.Town;
import org.resitrack.util.CommonUtil;

import java.util.List;
import java.util.Scanner;

public class HouseController {

    private final Scanner scanner;
    private TownController townController;
    private List<House> houses;

    public HouseController() {
        this.scanner = new Scanner(System.in);
    }

    public void setTownController(TownController townController) {
        this.townController = townController;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    /**
     * This function use for create a new house inside a town.
     */
    public void createNewHouse() {

        String townId = getTownId();
        if (townId == null) {
            System.out.println("Town is empty, could not create a new house. Please create a new town first");
            return;
        }

        String houseNumber = getHouseNumber(townId);

        this.houses.add(createHouse(houseNumber, townId));
        System.out.println("\nAdded house successful");
    }

    private String getTownId() {
        int currentNumberOfTown = townController.getNumberOfTown();
        if (currentNumberOfTown <= 0) {
            return null;
        }

        System.out.println("List available town to add house into: ");
        townController.printAllTownInformation();

        System.out.print("Enter town id to add new house: ");
        String townId = scanner.nextLine();

        while (!townController.isTownExisted(townId)) {
            System.out.println("Town id:" + townId + "not found, please enter correct town id in list bellow: ");
            townController.printAllTownInformation();

            System.out.print("Please enter town id again here: ");
            townId = scanner.nextLine();
        }

        return townId;
    }

    private String getHouseNumber(String townId) {
        System.out.print("Enter house number: ");
        String houseNumber = scanner.nextLine();
        String houseNumberError = validateHouseNumber(houseNumber, townId);

        while (houseNumberError != null) {
            System.out.println(houseNumberError);
            System.out.print("Please enter house number again: ");
            houseNumber = scanner.nextLine();
            houseNumberError = validateHouseNumber(houseNumber, townId);
        }
        return houseNumber;
    }

    public String getHouseInfo(House house) {
        Town town = townController.getTownById(house.getTownId());
        return "House{" +
                "id='" + house.getId() + '\'' +
                ", houseNumber='" + house.getHouseNumber() + " " + town.getName() + '\'' +
                '}';
    }

    public void printAllHouseInformation() {
        // loop to print
        this.houses.forEach(house -> System.out.println(getHouseInfo(house)));
    }

    private String validateHouseNumber(String houseNumber, String townId) {
        if (CommonUtil.isNullOrBlank(houseNumber)) {
            return "ERROR: House number cannot leave blank";
        }

        if (houseNumber.trim().length() < 2) {
            return "ERROR: House number cannot less than 2 characters, " +
                    "if your house number just have 1 character please add \"0\" before it";
        }

        if (isHouseInTownExist(houseNumber, townId)) {
            return "ERROR: House number " + houseNumber +" is exist in town: " + townId + " please choose a another town";
        }

        return null;
    }

    private boolean isHouseInTownExist(String houseNumber, String townId) {
        return houses.stream()
                .anyMatch(house -> townId.equalsIgnoreCase(house.getTownId()) &&
                        houseNumber.equalsIgnoreCase(house.getHouseNumber()));
    }

    private House createHouse(String houseNumber, String townId) {
        return new House(houseNumber, townId);
    }
}
