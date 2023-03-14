package org.resitrack.controller;

import org.resitrack.entity.House;
import org.resitrack.entity.Town;
import org.resitrack.util.CommonUtil;

import java.util.List;
import java.util.Scanner;

/**
 * Create a HouseController class with functions: add, delete, edit, search, display information about the house
 *
 * @author TuDucThinh
 * @since 13/3/2023
 */
public class HouseController {
    /**
     * This function makes the Scanner unchangeable
     */
    private final Scanner scanner;
    /**
     * This function has access scope in the class.
     * Data type is TownController variable name townController
     */
    private TownController townController;
    /**
     * This function is used to create a list of houses.
     * Helps users to store houses that have just been saved to town
     */
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
     * After entering, you will be notified as follows: Successfully add the house
     * If you want to add another house, press 1 if you want to exit, press 0
     */
    public void createNewHouse() {

        String townId = getTownId();
        String nameTown = townController.getTownById(townId).getName();
        int count = 1;
        int choice;
        if (townId == null) {
            System.out.println("Town is empty, could not create a new house. Please create a new town first");
            return;
        }

        do {
            System.out.println("House " + count + ": ");
            String houseNumber = getHouseNumber(townId);

            this.houses.add(createHouse(houseNumber, townId));
            System.out.println("\nAdded " + count + " house. The latest home address is: " + houseNumber + " " + nameTown);
            System.out.println();
            System.out.print("Enter 0 to exit, Enter 1 to continue: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                count++;
            }
        } while (choice != 0);


    }

    /**
     * This function is used to retrieve the value of ID town
     *
     * @return The ID town
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
            System.out.println("Town id: " + townId + " not found, please enter correct town id in list bellow: ");
            townController.printAllTownInformation();

            System.out.print("Please enter town id again here: ");
            townId = scanner.nextLine();
        }

        return townId;
    }

    /**
     * This function is used to retrieve the value of house number
     *
     * @param townId pass ID town in String
     * @return The neighborhood number house
     */
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

    /**
     * This function is used to retrieve the value of House Information
     *
     * @param house infuse the value of house in a House style
     * @return House information like house ID, house number, town name
     */
    public String getHouseInfo(House house) {

        Town town = townController.getTownById(house.getTownId());
        return "House{" +
                " id = '" + house.getId() + '\'' +
                ", house number = '" + house.getHouseNumber() + "', '" + town.getName() + '\'' +
                '}';
    }

    /**
     * This function is used to check the error of the house number entered by the user and give a message
     *
     * @param houseNumber new house number
     * @param townId      town id to check if given house number exist or not
     * @return Errors when user enters house number
     */
    private String validateHouseNumber(String houseNumber, String townId) {

        if (CommonUtil.isNullOrBlank(houseNumber)) {
            return "ERROR: House number cannot leave blank";
        }

        if (houseNumber.trim().length() < 2) {
            return "ERROR: House number cannot less than 2 characters, " +
                    "if your house number just have 1 character please add \"0\" before it";
        }

        if (isHouseInTownExist(houseNumber, townId)) {
            return "ERROR: House number " + houseNumber + " that exist in town '" + townId + "'. Please enter another house number ";
        }

        return null;
    }

    /**
     * This function is used to check if the house number you just entered already exists in the town
     *
     * @param houseNumber pass the house number as String
     * @param townId      pass ID town in String
     * @return True if the input value has the same house number, tow ID already. if false return false
     */
    private boolean isHouseInTownExist(String houseNumber, String townId) {

        for (House house : houses) {
            if (houseNumber.equalsIgnoreCase(house.getHouseNumber()) && townId.equalsIgnoreCase(house.getTownId())) {
                return true;
            }
        }

        return false;
    }

    /**
     * This function is used to transmit the necessary information of the house
     *
     * @param houseNumber pass the house number as String
     * @param townId      pass ID town in String
     * @return Home with attributes like: house number, townId
     * @see House
     */
    private House createHouse(String houseNumber, String townId) {

        return new House(houseNumber, townId);
    }
}
