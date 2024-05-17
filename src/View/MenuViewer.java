package View;

import java.util.Scanner;

public class MenuViewer {
    public static Scanner choice = new Scanner(System.in);

    public static void mainMenuView() {
        System.out.println();
        System.out.println("===== Incheon International Airport Info =====");
        System.out.println("Select the menu.");
        System.out.println("1. Arrivals");              //도착편
        System.out.println("2. Departures");            //출발편
        System.out.println("3. Quit");                  //종료
        System.out.print("select >> ");
    }

    //항공편 도착
    public static void arrivalMenuView() {
        System.out.println();
        System.out.println("================== Arrivals ==================");
        System.out.println("Select the menu.");
        System.out.println("1. Load");      //로드        
        System.out.println("2. Save");      //저장
        System.out.println("3. Search");    //검색
        System.out.println("4. Delete");    //삭제
        System.out.println("5. Go back");    //뒤로
        System.out.print("select >> ");
    }

    public static void departMenuView() {
        System.out.println();
        System.out.println("================== Departures ==================");
        System.out.println("Select the menu.");
        System.out.println("1. Load");      //로드        
        System.out.println("2. Save");      //저장
        System.out.println("3. Search");    //검색
        System.out.println("4. Delete");    //삭제
        System.out.println("5. Go back");    //뒤로
        System.out.print("select >> ");
    }



}
