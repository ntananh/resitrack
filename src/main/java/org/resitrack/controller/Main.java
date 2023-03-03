package org.resitrack.controller;


import java.lang.invoke.SwitchPoint;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choose;

        do {
            System.out.println("\n \tYour options: ");
            System.out.println("1. Add house to town.");
            System.out.println("2. Search house to town. ");
            System.out.println("0. Exit.");
            System.out.println();
            System.out.print("Your choose: ");
            choose = sc.nextInt();
            switch (choose){

                case 1:
                    HomeFunction.addHouse();
                    break;
                case 2:
                    break;
                default:
            }
        }while (choose != 0);
    }
}
