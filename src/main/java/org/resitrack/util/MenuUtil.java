package org.resitrack.util;

public class MenuUtil {
    public final static String MANAGE_PERSON = "1";
    public final static String MANAGE_HOUSE = "2";
    public final static String MANAGE_TOWN = "3";
    public final static String EXIT = "4";
    public final static String ADD_HOUSE = "1";
    public final static String DELETE_HOUSE = "2";

    public static void mainMenu() {
        System.out.println("|-----------Resident Management-----------|");
        System.out.println("| 1. Manage Person                        |");
        System.out.println("| 2. Manage House                         |");
        System.out.println("| 3. Manage Town                          |");
        System.out.println("| 4. Exit                                 |");
        System.out.println("|-----------------------------------------|");
    }

    public static void personMenu() {
        System.out.println("\n\n|----------------Person Menu--------------|");
        System.out.println("| 1. Add Person                           |");
        System.out.println("| 2. Search people by name                |");
        System.out.println("| 4. Exit                                 |");
        System.out.println("|-----------------------------------------|");
    }

    /**
     * This is a function so that users can choose the functions they want
     */
    public static void houseMenu() {
        System.out.println("\n\n|----------------House Menu---------------|");
        System.out.println("| 1. Add House                            |");
        System.out.println("| 2. Delete House                         |");
        System.out.println("| 4. Exit                                 |");
        System.out.println("|-----------------------------------------|");
    }

}
