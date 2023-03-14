package org.resitrack.util;

public class MenuUtil {
    public final static String MANAGE_PERSON = "1";
    public final static String MANAGE_HOUSE = "2";
    public final static String MANAGE_TOWN = "3";
    public final static String EXIT_MAIN_MENU = "4";
    public final static String ADD_PERSON = "1";
    public final static String SEARCH_PERSON = "2";
    public final static String DELETE_PERSON = "3";
    public final static String EDIT_PERSON = "4";
    public final static String EXIT_PERSON_MENU = "5";


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
        System.out.println("| 3. Delete people by name                |");
        System.out.println("| 4. Edit information of person           |");
        System.out.println("| 5. Exit                                 |");
        System.out.println("|-----------------------------------------|");
    }

}
