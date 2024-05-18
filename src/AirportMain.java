import java.util.ArrayList;
import java.util.InputMismatchException;

import Controller.APIUtil;
import Controller.FlightManager;
import Model.FlightInfo;
import View.SELECT_CHOICE;
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
        ArrayList<FlightInfo> flightInfoList = new ArrayList<>();
        FlightManager fm = new FlightManager();

        while (true) {
            try{
                MenuViewer.departMenuView();
                choice = MenuViewer.choice.nextInt();
                MenuViewer.choice.nextLine();
                switch (choice) {
                    case SELECT_CHOICE.LOAD:
                        flightInfoList = APIUtil.departConnection();
                        break;
                    case SELECT_CHOICE.SAVE:
                        fm.insertInfo(flightInfoList);
                        break;
                    case SELECT_CHOICE.SEARCH:
                        fm.searchInfo();
                        break;
                    case SELECT_CHOICE.DELETE:
                        fm.AllInfoDelete();
                        break;
                    case SELECT_CHOICE.BACK:
                        return;
                    default:
                        System.out.println("Please only enter the appropriate menu number.");
                } // end of switch
            }catch(InputMismatchException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    // 도착 항공편 메뉴
    public static void arrivalMenu() {
        int choice;
        ArrayList<FlightInfo> flightInfoList = new ArrayList<>();
        FlightManager fm = new FlightManager();

        while (true) {
            try{
                MenuViewer.arrivalMenuView();
                choice = MenuViewer.choice.nextInt();
                MenuViewer.choice.nextLine();
                switch (choice) {
                    case SELECT_CHOICE.LOAD:
                        flightInfoList = APIUtil.arriveConnection();
                        break;
                    case SELECT_CHOICE.SAVE:
                        fm.insertInfo(flightInfoList);
                        break;
                    case SELECT_CHOICE.SEARCH:
                        fm.searchInfo();
                        break;
                    case SELECT_CHOICE.DELETE:
                        fm.AllInfoDelete();
                        break;
                    case SELECT_CHOICE.BACK:
                        return;
                    default:
                        System.out.println("Please only enter the appropriate menu number.");
                } // end of switch
            } catch(InputMismatchException e){
                e.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
