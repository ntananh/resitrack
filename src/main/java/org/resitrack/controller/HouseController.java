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
        String optionTwo;
        if (townId == null) {
            System.out.println("Town is empty, could not create a new house. Please create a new town first");
            return;
        }
        System.out.println("Enter [o] to add one house , enter [m] to add multiple house:");
        optionTwo = scanner.nextLine();

        if (optionTwo.equals("o")) {
            String streetName = getStreetName(townId);
            String houseNumber = getHouseNumber(streetName, townId);

            this.houses.add(createHouse(houseNumber, streetName, townId));
            System.out.println("\nAdded house successful");
        }
        if (optionTwo.equals("m")){
            int numberOfHouse;
            System.out.println("Please enter the number of houses to add, attention: the number must be greater than 1: ");
            numberOfHouse = scanner.nextInt();
            scanner.nextLine();
            while (numberOfHouse > 1){
                for (int i = 1; i <= numberOfHouse; i++){
                    System.out.println("House " + i + ": ");
                    String streetName = getStreetName(townId);
                    String houseNumber = getHouseNumber(streetName, townId);

                    this.houses.add(createHouse(houseNumber, streetName, townId));
                    System.out.println("\nAdded house successful");
                    System.out.println();
                }
                break;
            }
        }
    }

    /**
     * retrieve the value of townId
     * @returns the neighborhood ID
     */
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
    /**
     * retrieve the value of HouseNumber
     * @returns the neighborhood number house
     */
    private String getHouseNumber(String streetName, String townId) {
        System.out.print("Enter house number: ");
        String houseNumber = scanner.nextLine();
        String houseNumberError = validateHouseNumber(houseNumber, streetName, townId);

        while (houseNumberError != null) {
            System.out.println(houseNumberError);
            System.out.print("Please enter house number again: ");
            houseNumber = scanner.nextLine();
            houseNumberError = validateHouseNumber(houseNumber, streetName, townId);
        }
        return houseNumber;
    }
    /**
     * retrieve the value of StreetName
     * @returns the neighborhood street name
     */
    private String getStreetName(String townId) {
        System.out.print("Enter street name:");
        String streetName = scanner.nextLine();
        String streetNameError = validateStreetName(streetName, townId);

        while (streetNameError != null){
            System.out.println(streetNameError);
            System.out.print("Please enter street name again:");
            streetName = scanner.nextLine();
            streetNameError = validateStreetName(streetName, townId);
        }
        return streetName;
    }
    /**
     * retrieve the value of HouseInfo
     * @returns house information like house ID, house number, street name, town name
     */
    public String getHouseInfo(House house) {
        Town town = townController.getTownById(house.getTownId());
        return "House{" +
                " id = '" + house.getId() + '\'' +
                ", house number = '" + house.getHouseNumber() + "', street name = '" + house.getStreetName() + "', '" + town.getName() + '\'' +
                '}';
    }
    /**
     * This function is used to print house information to the screen
     */
    public void printAllHouseInformation() {
        for (House house : houses){
            System.out.println(getHouseInfo(house));
        }
    }
    /**
     * This function is used to check the error of the house number entered by the user and give a message
     */
    private String validateHouseNumber(String houseNumber, String streetName, String townId) {
        if (CommonUtil.isNullOrBlank(houseNumber) || CommonUtil.isNullOrBlank(streetName)) {
            return "ERROR: House number cannot leave blank";
        }

        if (houseNumber.trim().length() < 2) {
            return "ERROR: House number cannot less than 2 characters, " +
                    "if your house number just have 1 character please add \"0\" before it";
        }

        if (isHouseInTownExist(houseNumber, townId)) {
            return "ERROR: House number " + houseNumber + " that exist in street '" + streetName +"' and town '" + townId + "'. Please enter another house number ";
        }

        return null;
    }
    /**
     * This function is used to check the error of the street name entered by the user and give a message
     */
    private String validateStreetName(String streetName, String townId) {
        if (CommonUtil.isNullOrBlank(streetName)) {
            return "ERROR: Street name cannot leave blank";
        }

        if (streetName.trim().length() < 1) {
            return "ERROR: Street name cannot less than 2 characters, " +
                    "if your house number just have 1 character please add \"0\" before it";
        }

        return null;
    }
    /**
     * This function is used to check if the town has the same house number
     */
    private boolean isHouseInTownExist(String houseNumber, String townId) {
        return houses.stream()
                .anyMatch(house -> townId.equalsIgnoreCase(house.getTownId()) &&
                        houseNumber.equalsIgnoreCase(house.getHouseNumber()));
    }
    /**
     * This function is used to transmit the necessary information of the house
     * @return home with attributes like: house number, street name, townId
     */
    private House createHouse(String houseNumber, String streetName, String townId) {
        return new House(houseNumber, streetName, townId);
    }
}
