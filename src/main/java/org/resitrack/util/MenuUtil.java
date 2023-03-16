package org.resitrack.util;

public class MenuUtil {
    public final static String MANAGE_PERSON = "1";
    public final static String MANAGE_HOUSE = "2";
    public final static String MANAGE_TOWN = "3";
    public final static String EXIT_PERSON_MENU = "6";
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

    public static void personMenu() {
        System.out.println("\n\n|----------------Person Menu--------------|");
        System.out.println("| 1. Add Person                           |");
        System.out.println("| 2. Show all person                      |");
        System.out.println("| 3. Search people                        |");
        System.out.println("| 4. Delete function                      |");
        System.out.println("| 6. Exit                                 |");
        System.out.println("|-----------------------------------------|");
    }

}
