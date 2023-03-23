package org.resitrack.controller;

import org.resitrack.entity.House;
import org.resitrack.entity.Town;
import org.resitrack.util.CommonUtil;

import java.util.ArrayList;
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
     * Users can choose to continue adding houses by ADDING_HOUSE
     */
    public final static String EXIT = "0";

    /**
     * User can delete 1 house by using DELETE_ONE_HOUSE
     */
    public final static String DELETE_ONE_HOUSE = "o";

    /**
     * User can delete multiple house by using DELETE_MULTIPLE_HOUSE
     */
    public final static String DELETE_MULTIPLE_HOUSE = "m";
    /**
     * User can back to house menu by using BACK_HOUSE_MENU
     */
    public final static String BACK_HOUSE_MENU = "Back to House Menu";

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

        String townId = getTownId();

        int numberOfHouse = 1;
        String choice;
        if (townId == null) {
            System.out.println("Town is empty, could not create a new house. Please create a new town first");
            return;
        }

        Town town = townController.getTownById(townId);
        String townName = town.getName();

        do {
            System.out.println("Adding a new house");
            String houseNumber = getHouseNumber(townId);
            this.houses.add(createHouse(houseNumber, townId));

            System.out.println("\nAdded " + numberOfHouse + " house. The latest home address is: " + houseNumber + " " + townName);
            System.out.println(">Enter 0 to exit, or press any key to continue!");
            choice = scanner.nextLine();
            numberOfHouse++;

        } while (!choice.equalsIgnoreCase(EXIT));

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
            return "ERROR: House number " + houseNumber + " that exist in town '" + townId.toUpperCase() + "'. Please enter another house number ";
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

    /**
     * This function is used to remove houses from town.
     * When you enter the program, you will be checked to see if there are any houses or not. If not, an announcement will be made.
     * If yes, then the user will choose 1 of 2 options.
     * Option 1 will delete all houses whose house number is the same as the house number entered.
     * Option 2 you will have to enter the town id to delete the house
     */
    public void deleteHouse() {

        String houseNumber = getHouseNumber();

        if (houseNumber == null) {
            System.out.println("There are no houses on the list. You cannot perform this operation");
            return;
        }

        if (houseNumber.equalsIgnoreCase(BACK_HOUSE_MENU)){
            System.out.println("\"Back to House Menu\"");
        }

    }

    /**
     * This function is used to check if a house exists or not. if not return null.
     * If a house exists, a list of houses will be displayed. and let the user enter the house number they want to delete.
     * If you enter it incorrectly, re-enter it.
     *
     * @return The House number
     */
    private String getHouseNumber() {

        int currentNumberOfHouse = getNumberOfHouse();

        if (currentNumberOfHouse <= 0) {
            return null;
        }

        String townId;
        String choice;
        String optionTwo;

        do {

            System.out.println(">Enter 0 to exit, or press any key to continue!");
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase(EXIT)) {
                break;
            }
            System.out.println("Existing home listings: ");
            houseInformation();

            System.out.print("Enter the house number you want to delete: "); // logic exit
            String houseNumber = scanner.nextLine();
            findHouseByHouseNumber(houseNumber);

            while (!isHouseExisted(houseNumber)) {
                System.out.println("House number: " + houseNumber + " not found, please enter the correct house number in the list below: ");
                houseInformation();

                System.out.print("Please enter house number again here: ");
                houseNumber = scanner.nextLine();

            }
            System.out.println("Enter [o] to delete one house , Enter [m] to delete all houses whose house numbers are: " + houseNumber);
            System.out.print("Your choice: ");

            do {
                optionTwo = scanner.nextLine();

                if (optionTwo.equalsIgnoreCase(DELETE_ONE_HOUSE)) {
                    System.out.print("Please enter your town id to delete: ");
                    townId = scanner.nextLine();
                    do {
                        System.out.println("Town id: " + townId.toUpperCase() + " not found, please enter the correct town id in the list below: ");

                        System.out.print("Please enter town id again here: ");
                        townId = scanner.nextLine();

                    } while (townController.isTownExisted(townId));

                    deleteHouseByTownId(houseNumber, townId);

                }
                if (optionTwo.equalsIgnoreCase(DELETE_MULTIPLE_HOUSE)) {
                    deleteHouseByHouseNumber(houseNumber);
                    System.out.println("Successfully deleted");
                }

            } while (optionTwo.equalsIgnoreCase(DELETE_ONE_HOUSE) && optionTwo.equalsIgnoreCase(DELETE_MULTIPLE_HOUSE));

            return houseNumber;

        } while (!choice.equalsIgnoreCase(EXIT));
        return BACK_HOUSE_MENU;
    }

    /**
     * This function is used to check if the house number you just entered already exists
     *
     * @param houseNumber pass ID house in String
     * @return true when a house exists, otherwise false
     */
    private boolean isHouseExisted(String houseNumber) {
        return houses.stream().anyMatch(house -> houseNumber.equalsIgnoreCase(house.getHouseNumber()));
    }

    /**
     * This function is used to return the number of houses
     *
     * @return The number of existing houses
     */
    private int getNumberOfHouse() {
        return houses.size();
    }

    /**
     * This function is used to display the list of houses
     */
    public void houseInformation() {

        houses.forEach(System.out::println);
    }

    /**
     * This function is used to find houses by house number.
     * Once found, it will display the houses with the same house number as the house number entered
     *
     * @param houseNumber Enter the house number to see if there are any houses with the same house number
     */
    private void findHouseByHouseNumber(String houseNumber) {

        List<House> housesWithHouseNumbers = new ArrayList<>();

        for (House house : houses) {
            if (houseNumber.equalsIgnoreCase(house.getHouseNumber())) {

                housesWithHouseNumbers.add(house);

            }
        }

        System.out.println();
        System.out.println("List of houses with house numbers are: " + houseNumber);
        housesWithHouseNumbers.forEach(System.out::println);

    }

    /**
     * This function is used to delete all houses with the same house number as entered
     *
     * @param houseNumber Pass in the house number to delete the houses whose house number is the same as the house number entered
     */
    private void deleteHouseByHouseNumber(String houseNumber) {

        houses.removeIf(house -> house.getHouseNumber().equalsIgnoreCase(houseNumber));

    }

    /**
     * This function is used to delete all houses with the same house number and town id as entered
     *
     * @param houseNumber Pass in the house number to delete the houses
     * @param townId      Enter town id to delete house
     */
    private void deleteHouseByTownId(String houseNumber, String townId) {

        houses.removeIf(house -> house.getHouseNumber().equalsIgnoreCase(houseNumber) && house.getTownId().equalsIgnoreCase(townId));
    }

}
