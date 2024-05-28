package View;

import java.util.Scanner;

public class MenuViewer {
    public static Scanner choice = new Scanner(System.in);

    public static void mainMenuView() {
        System.out.println();
        System.out.println();
        System.out.println("=======================================");
        System.out.println("   Incheon International Airport Info ");
        System.out.println("---------------------------------------");
        System.out.println("        Please select an option");
        System.out.println();
        System.out.println("            1. Arrivals");
        System.out.println("            2. Departures");
        System.out.println("            3. Quit");
        System.out.println("=======================================");
        System.out.print("            Select >> ");
    }

    // 항공편 도착
    public static void arrivalMenuView() {
        System.out.println();
        System.out.println();
        System.out.println("=======================================");
        System.out.println("              Arrivals               ");
        System.out.println("---------------------------------------");
        System.out.println("        Please select an option");
        System.out.println();
        System.out.println("            1. Load                  ");
        System.out.println("            2. Save                  ");
        System.out.println("            3. Search                 ");
        System.out.println("            4. Delete                 ");
        System.out.println("            5. Go back                ");
        System.out.println("=======================================");
        System.out.print("            Select >> ");
    }

    public static void departMenuView() {
        System.out.println();
        System.out.println();
        System.out.println("=======================================");
        System.out.println("             Departures              ");
        System.out.println("---------------------------------------");
        System.out.println("        Please select an option");
        System.out.println();
        System.out.println("            1. Load                  ");
        System.out.println("            2. Save                  ");
        System.out.println("            3. Search                 ");
        System.out.println("            4. Delete                 ");
        System.out.println("            5. Go back                ");
        System.out.println("=======================================");
        System.out.print("Select >> ");
    }

}
