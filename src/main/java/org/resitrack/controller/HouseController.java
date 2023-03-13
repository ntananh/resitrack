package org.resitrack.controller;

import org.resitrack.entity.House;
import org.resitrack.entity.Town;
import org.resitrack.util.CommonUtil;

import java.util.List;
import java.util.Scanner;

/**
 * create a HouseController class with functions: add, delete, edit, search, display information about the house
 * @author TuDucThinh
 * @since 13/3/2023
 */

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
     * After entering, you will be notified as follows: Successfully entered the house whose address is the information you just entered.
     * If you want to add another house, press 1 if you want to exit, press 0
     */
    public void createNewHouse() {

        String townId = getTownId();
        int i = 1;
        int choice;
        if (townId == null) {
            System.out.println("Town is empty, could not create a new house. Please create a new town first");
            return;
        }

        do {
            scanner.nextLine();
            System.out.println("House " + i + ": ");
            String streetName = getStreetName(townId);
            String houseNumber = getHouseNumber(streetName, townId);

            this.houses.add(createHouse(houseNumber, streetName, townId));
            System.out.println("\nAdded "+ i + " house. The latest home address is: House number "
                    + houseNumber + ", " + streetName + " street");
            System.out.println();
            System.out.print("Enter 0 to exit, Enter 1 to continue: ");
            choice = scanner.nextInt();
            if (choice == 1){
                i++;
            }
        }while (choice != 0);


    }

    /**
     * this function is used to retrieve the value of ID town
     * @return the neighborhood ID town
     * @see String
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
     * this function is used to retrieve the value of house number
     * @param streetName pass in the street name as String
     * @param townId pass ID town in String
     * @return the neighborhood number house
     * @see String
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
     * this function is used to retrieve the value of street name
     * @param townId pass ID town in String
     * @return the neighborhood street name
     * @see String
     */
    private String getStreetName(String townId) {

        System.out.print("Enter street name: ");
        String streetName = scanner.nextLine();
        String streetNameError = validateStreetName(streetName, townId);

        while (streetNameError != null) {
            System.out.println(streetNameError);
            System.out.print("Please enter street name again:");
            streetName = scanner.nextLine();
            streetNameError = validateStreetName(streetName, townId);
        }
        return streetName;
    }

    /**
     * this function is used to retrieve the value of House Information
     * @param house infuse the value of house in a House style
     * @return house information like house ID, house number, street name, town name
     * @see String
     */
    public String getHouseInfo(House house) {

        Town town = townController.getTownById(house.getTownId());
        return "House{" +
                " id = '" + house.getId() + '\'' +
                ", house number = '" + house.getHouseNumber() + "', street name = '" + house.getStreetName() + "', '" + town.getName() + '\'' +
                '}';
    }

    /**
     * This function is used to print the information of the houses in the town to the screen
     * @return list of houses in town
     */
    public void printAllHouseInformation() {

        for (House house : houses) {
            System.out.println(getHouseInfo(house));
        }
    }

    /**
     * This function is used to check the error of the house number entered by the user and give a message
     * @param houseNumber new house number
     * @param streetName street name to check if given house number exist or not
     * @param townId town id to check if given house number exist or not
     * @return errors when user enters house number
     * @see String
     */
    private String validateHouseNumber(String houseNumber, String streetName, String townId) {

        if (CommonUtil.isNullOrBlank(houseNumber) || CommonUtil.isNullOrBlank(streetName)) {
            return "ERROR: House number cannot leave blank";
        }

        if (houseNumber.trim().length() < 2) {
            return "ERROR: House number cannot less than 2 characters, " +
                    "if your house number just have 1 character please add \"0\" before it";
        }

        if (isHouseInTownExist(houseNumber, streetName, townId)) {
            return "ERROR: House number " + houseNumber + " that exist in street '" + streetName + "' and town '" + townId + "'. Please enter another house number ";
        }

        return null;
    }

    /**
     * This function is used to check the street name error entered by the user and give a message
     * @param streetName new street name.
     * @param townId town id to check if given street name exist or not
     * @return errors when user enters street name
     * @see String
     */
    private String validateStreetName(String streetName, String townId) {

        if (CommonUtil.isNullOrBlank(streetName)) {
            return "ERROR: Street name cannot leave blank";
        }

        if (streetName.trim().length() < 2) {
            return "ERROR: Street name cannot less than 2 characters, ";
        }

        return null;
    }

    /**
     * This function is used to check if the house number you just entered already exists in the town and street
     * @param houseNumber pass the house number as String
     * @param streetName pass in the street name as String
     * @param townId pass ID town in String
     * @return true if the input value has the same house number, street name, tow ID already. if false return false
     */
    private boolean isHouseInTownExist(String houseNumber, String streetName, String townId) {

        for (House house : houses) {
            if (houseNumber.equalsIgnoreCase(house.getHouseNumber())
                    && streetName.equalsIgnoreCase(house.getStreetName()) && townId.equalsIgnoreCase(house.getTownId())) {
                return true;
            }
        }

        return false;
    }

    /**
     * This function is used to transmit the necessary information of the house
     * @param houseNumber pass the house number as String
     * @param streetName pass in the street name as String
     * @param townId pass ID town in String
     * @return Home with attributes like: house number, street name, townId
     * @see House
     */
    private House createHouse(String houseNumber, String streetName, String townId) {

        return new House(houseNumber, streetName, townId);
    }
}
