import java.util.ArrayList;

import Controller.APIUtil;
import Controller.FlightManager;
import Model.FlightInfo;
import View.ARRIVAL_CHOICE;
import View.MENU_CHOICE;
import View.MenuViewer;

public class AirportMain {
    public static void main(String[] args) {
        mainMenu();
    } // end of main

    // 메인메뉴
    public static void mainMenu() {
        int choiceNum;

        while (true) {
            try {
                MenuViewer.mainMenuView();
                choiceNum = MenuViewer.choice.nextInt();
                MenuViewer.choice.nextLine();

                switch (choiceNum) {
                    case MENU_CHOICE.ARRIVE: // 1. 도착항공편
                        arrivalMenu();
                        break;
                    case MENU_CHOICE.DEPART: // 2. 출발항공편
                        departMenu();
                        break;
                    case MENU_CHOICE.EXIT: // 4. 종료
                        System.out.println("Exit the program.");
                        return;
                    default:
                        System.out.println("Please only enter the appropriate menu number.");
                        break;
                } // end of switch
            } catch (Exception e) {
                System.out.println(e.toString() + "\nPlease restart the program");
                return;
            }
        } // end of while
    }

    // 출발 항공편 메뉴
    public static void departMenu() {
        int choice;

        while (true) {
            MenuViewer.departMenuView();
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
            switch (choice) {
                case ARRIVAL_CHOICE.LOAD:
                    break;
                case ARRIVAL_CHOICE.SAVE:
                    break;
                case ARRIVAL_CHOICE.SEARCH:
                    break;
                case ARRIVAL_CHOICE.DELETE:
                    break;
                case ARRIVAL_CHOICE.BACK:
                    return;
                default:
                    System.out.println("Please only enter the appropriate menu number.");
            } // end of switch
        }
    }

    // 도착 항공편 메뉴
    public static void arrivalMenu() {
        int choice;
        ArrayList<FlightInfo> flightInfoList = new ArrayList<>();
        FlightManager fm = new FlightManager();

        while (true) {
            MenuViewer.arrivalMenuView();
            choice = MenuViewer.choice.nextInt();
            MenuViewer.choice.nextLine();
            switch (choice) {
                case ARRIVAL_CHOICE.LOAD:
                    flightInfoList = APIUtil.arriveConnection();
                    break;
                case ARRIVAL_CHOICE.SAVE:
                    fm.insertArrivalInfo(flightInfoList);
                    break;
                case ARRIVAL_CHOICE.SEARCH:
                    fm.searchArrivalInfo();
                    break;
                case ARRIVAL_CHOICE.DELETE:
                    fm.deleteArrivalInfo();
                    break;
                case ARRIVAL_CHOICE.BACK:
                    return;
                default:
                    System.out.println("Please only enter the appropriate menu number.");
            } // end of switch
        }

    }

}
