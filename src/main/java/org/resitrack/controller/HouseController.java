package org.resitrack.controller;

import org.resitrack.entity.House;
import org.resitrack.entity.Town;
import org.resitrack.util.CommonUtil;

import java.util.List;
import java.util.Scanner;

/**
 * House Controller used for managing houses, allow user to manipulate with house data.
 *
 * @author TuDucThinh
 * @since 13/3/2023
 */
public class HouseController {
    /**
     * Used to access Scanner.
     */
    private final Scanner scanner;
    /**
     * Used to access townController.
     */
    private TownController townController;
    /**
     * Used to create a list of houses.
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

        Town town = townController.getTownById(getTownId());
        String townId = town.getId();
        String townName = town.getName();

        int numberOfHouse = 1;
        String choice;
        if (townId == null) {
            System.out.println("Town is empty, could not create a new house. Please create a new town first");
            return;
        }

        do {
            System.out.println("Adding a new house");
            String houseNumber = getHouseNumber(townId);
            this.houses.add(createHouse(houseNumber, townId));

            System.out.println("\nAdded " + numberOfHouse + " house. The latest home address is: " + houseNumber + " " + townName);
            System.out.print(">Enter 0 to exit, or press any key to continue!");
            choice = scanner.nextLine();
            numberOfHouse++;

        } while (!choice.equalsIgnoreCase("0"));

    }

    /**
     * This function is used to retrieve the value of ID town
     *
     * @return The existing ID town
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

    private String idName(Town town) {

        return town.getName();
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
     * Validation function is used to check the error of the house number entered by the user and give a message
     *
     * @param houseNumber new house number
     * @param townId      town id to check if given house number exist or not
     * @return Error message when user enters an invalid house number
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
     * @return True if house with house number, tow ID already existed, otherwise false
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
     * @return {@link House} New created house
     */
    private House createHouse(String houseNumber, String townId) {

        return new House(houseNumber, townId);
    }
}
