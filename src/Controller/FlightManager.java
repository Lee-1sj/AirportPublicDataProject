package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.FlightInfo;

public class FlightManager {
    public static Scanner sc = new Scanner(System.in);

    // 도착편 정보를 저장
    public void insertInfo(ArrayList<FlightInfo> flightInfoList) {
        FlightDAO fd = new FlightDAO();
        try {
            if (flightInfoList.size() < 1) {
                System.out.println();
                System.out.println("---------------------------------------");
                System.out.println("           There is no list.");
                System.out.println("---------------------------------------");
                return;
            }
            fd.deleteInitialInfo(); // 이전 데이터 삭제
            fd.saveFlightInfo(flightInfoList); // 저장
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end of insertArrivalInfo

    // 도착편 정보를 검색
    public void searchInfo() {
        FlightDAO fd = new FlightDAO();
        try {
            System.out.println();
            System.out.println("Enter the Flight ID you want to search for.");
            System.out.print("Flight ID >> ");
            String flightId = sc.nextLine();
            System.out.println();
            fd.getFlightInfo(flightId); // 해당 편명으로 정보 불러옴
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end of searchArrivalInfo()

    // db 삭제
    public void AllInfoDelete() {
        FlightDAO fd = new FlightDAO();

        try {
            System.out.println();
            System.out.println("-------------------------------------------------------");
            System.out.println("Are you sure you want to delete Arrival Info?");
            System.out.println("Press 'Y' to delete | Press any other letter to cancel");
            System.out.print("Press >> ");
            String answer = sc.nextLine();
            System.out.println("-------------------------------------------------------");
            System.out.println();

            if (answer.equalsIgnoreCase("Y")) {
                fd.eliminateFlightInfo();
            } else {
                System.out.println();
                System.out.println("The delete procedure has been canceled.");
                System.out.println("-------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
