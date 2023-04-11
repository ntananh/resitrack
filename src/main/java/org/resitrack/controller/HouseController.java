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
     * User can delete multiple house by using DELETE_MULTIPLE_HOUSE
     */
    public final static String DELETE_MULTIPLE_HOUSE = "m";

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
     * When entering the program, you will be checked to see if any houses exist in the townId you just entered.
     * Otherwise, an announcement will be made.
     * If at least one house exists, there will be a message asking if the user wants to continue deleting the house,
     * If yes, then user will choose 1 of 2 options.
     * Option 1 will delete all houses that exist in the townId just entered.
     * Option 2 must enter the house number to delete the house.
     * Otherwise exit the house menu.
     */
    public void deleteHouse(String townId) {

        String yourChoice;

        if (findHouseByTownId(townId) == null) {
            System.out.println("\nNo house in town id: " + townId.toUpperCase());
            return;
        }

        System.out.println("Enter [m] to delete all houses in town whose town id is: " + townId.toUpperCase()
                + " or press any key to continue");
        yourChoice = scanner.nextLine();

        if (yourChoice.equalsIgnoreCase(DELETE_MULTIPLE_HOUSE)) {

            String yesOrNo;
            System.out.println("Are you sure you want to delete all houses in townId as: " + townId.toUpperCase() + "?");
            System.out.print("Press any key to continue or enter 0 to exit: ");
            yesOrNo = scanner.nextLine();

            if (yesOrNo.equalsIgnoreCase(EXIT)){
                System.out.println("\nNo successful house removal");
                return;
            }
            deleteHouseByTownId(townId);
            System.out.println("Successful deleted");
            return;
        }

        deleteOneHouse(townId);

    }

    /**
     * This function is used to check if a house exists or not. if not return null.
     * If a house exists, a list of houses will be displayed. and let the user enter the house number they want to delete.
     * If you enter it incorrectly, re-enter it.
     *
     * @param townId pass in a param named townId to do the job
     */
    private void deleteOneHouse(String townId) {

        int currentNumberOfHouse = getNumberOfHouse();
        String houseNumber;

        if (currentNumberOfHouse <= 0) {
            return;
        }

        System.out.print("Enter house number you want to delete, or enter 0 to exit: ");
        houseNumber = scanner.nextLine();

        if (houseNumber.equalsIgnoreCase(EXIT)) {
            System.out.println("\nNo successful house removal");
            return;
        }

        while (!isHouseExisted(houseNumber)) {
            System.out.println("House number: " + houseNumber + " not found, please enter the correct house number in the list below: ");
            findHouseByTownId(townId);

            System.out.print("Please enter house number again here: ");
            houseNumber = scanner.nextLine();

        }

        String yesOrNo;
        System.out.println("Are you sure you want to delete the house whose address is: " + houseNumber + " " + townId.toUpperCase() + "?");
        System.out.print("Press any key to continue or enter 0 to exit: ");
        yesOrNo = scanner.nextLine();

        if (yesOrNo.equalsIgnoreCase(EXIT)){
            System.out.println("\nNo successful house removal");
            return;
        }

        deleteHouseTownIdAndHouseNumber(houseNumber, townId);
        System.out.println("successful delete");
    }

    /**
     * This function is used to check if the house number you just entered already exists
     *
     * @param houseNumber Pass house number in String
     * @return true when a house exists, otherwise false
     */
    private boolean isHouseExisted(String houseNumber) {
        return houses.stream().anyMatch(house
                -> houseNumber.equalsIgnoreCase(house.getHouseNumber()));
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
     * This function is used to find houses by town id.
     * If there are no houses in town, return null.
     * If yes, it will be entered in the list and then display the houses in the town
     *
     * @param townId Pass the townId param to execute the function
     * @return house listings searched by townId
     */
    public String findHouseByTownId(String townId) {

        List<House> housesWithTownId = new ArrayList<>();

        for (House house : houses) {
            if (townId.equalsIgnoreCase(house.getTownId())) {

                housesWithTownId.add(house);
            }
        }

        if (housesWithTownId.size() <= 0) {
            return null;
        }
        System.out.println();
        System.out.println("Listings house found:");
        housesWithTownId.forEach(System.out::println);
        return "house listings searched by town id";
    }

    /**
     * This function is used to delete all houses with the same townId as entered
     *
     * @param townId Enter town id to delete all houses with town id same as entered town id
     */
    private void deleteHouseByTownId(String townId) {
        houses.removeIf(house ->
                house.getTownId().equalsIgnoreCase(townId));

    }

    /**
     * This function is used to delete all houses with the same house number and town id as entered
     *
     * @param houseNumber Pass in the house number to delete the houses
     * @param townId      Enter town id to delete house
     */
    private void deleteHouseTownIdAndHouseNumber(String houseNumber, String townId) {
        houses.removeIf(house ->
                house.getHouseNumber().equalsIgnoreCase(houseNumber)
                        && house.getTownId().equalsIgnoreCase(townId));
    }

}
