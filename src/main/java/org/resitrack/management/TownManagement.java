package org.resitrack.management;

import org.resitrack.entity.Town;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TownManagement {

    static Scanner sc = new Scanner(System.in);
    private static List<Town> towns = new ArrayList<>();

    public static void addTown(){
        int addNumberOfTown;
        String idTown, nameTown;
        long areaTown;

        System.out.println(" Please enter the number of towns you need to add: ");
        addNumberOfTown = sc.nextInt();
        for (int i = 1; i <= addNumberOfTown; i ++ ) {
            System.out.println("Town " + i + ": ");
            System.out.println("Id town: ");
            idTown = sc.nextLine();
            sc.nextLine();
            System.out.println("Name town: ");
            nameTown = sc.nextLine();
            System.out.println("area town: ");
            areaTown = sc.nextLong();

            Town town = new Town(idTown, nameTown, areaTown);
            towns.add(town);
        }
    }
}
