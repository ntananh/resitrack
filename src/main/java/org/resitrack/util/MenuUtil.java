package org.resitrack.util;

public class MenuUtil {
    public final static String MANAGE_PERSON = "1";
    public final static String MANAGE_HOUSE = "2";
    public final static String MANAGE_TOWN = "3";
    public final static String EXIT = "4";
    public final static String ADD_HOUSE = "1";
    public final static String EXIT_ALL_PERSON_MENU = "6";
    public final static String EXIT_HOUSE_PERSON_MENU = "4";
    public final static String EXIT_HOUSE_MENU = "6";
    public final static String EXIT_DELETE_PERSON_MENU = "3";
    public final static String EXIT_MAIN_MENU = "4";
    public final static String ADD_PERSON = "1";
    public final static String SEARCH_PERSON = "3";
    public final static String DELETE_PERSON = "4";
    public final static String DELETE_PERSON_BY_ID = "1";
    public final static String DELETE_PERSON_BY_NAME = "2";
    public final static String SHOW_ALL_PERSON = "2";


    public static void mainMenu() {
        System.out.println("|-----------Resident Management-----------|");
        System.out.println("| 1. Manage Person                        |");
        System.out.println("| 2. Manage House                         |");
        System.out.println("| 3. Manage Town                          |");
        System.out.println("| 4. Exit                                 |");
        System.out.println("|-----------------------------------------|");
    }

    public static void allPersonMenu() {
        System.out.println("\n\n|----------------All Person Menu--------------|");
        System.out.println("| 1. Add Person                           |");
        System.out.println("| 2. Show all person                      |");
        System.out.println("| 3. Search people                        |");
        System.out.println("| 4. Delete function                      |");
        System.out.println("| 6. Exit                                 |");
        System.out.println("|-----------------------------------------|");
    }
    public static void housePersonMenu() {
        System.out.println("\n\n|---------------- House Person Menu--------------|");
        System.out.println("| 1. Add people to house                  |");
        System.out.println("| 2. Delete person from house             |");
        System.out.println("| 3. Show people in house                 |");
        System.out.println("| 4. Exit                                 |");
        System.out.println("|-----------------------------------------|");
    }

    /**
     * This is a function so that users can choose the functions they want
     */
    public static void houseMenu() {
        System.out.println("\n\n|----------------House Menu---------------|");
        System.out.println("| 1. Add House                            |");
        System.out.println("| 6. Exit                                 |");
        System.out.println("|-----------------------------------------|");
    }

}
