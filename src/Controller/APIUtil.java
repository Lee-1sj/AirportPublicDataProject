package Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Model.FlightInfo;

public class APIUtil {

    public static ArrayList<FlightInfo> arriveConnection() {
        ArrayList<FlightInfo> list = new ArrayList<>();
        String filePath = "src/key.properties";
        // 1. 요청 url 생성
        StringBuilder urlBuilder = new StringBuilder(
                "http://apis.data.go.kr/B551177/StatusOfPassengerWorldWeatherInfo/getPassengerArrivalsWorldWeather");
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(filePath));
            String key = properties.getProperty("key");

            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                    + key);
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
                    + URLEncoder.encode("100", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
                    + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("from_time", "UTF-8") + "="
                    + URLEncoder.encode("0000", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("to_time", "UTF-8") + "="
                    + URLEncoder.encode("2400", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "="
                    + URLEncoder.encode("E", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "="
                    + URLEncoder.encode("xml", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2.서버주소 Connection con
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(urlBuilder.toString()); // 웹서버주소 action
            conn = (HttpURLConnection) url.openConnection(); // 접속요청
            conn.setRequestMethod("GET"); // get방식
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println();
            System.out.println();
            System.out.println("Response code: " + conn.getResponseCode());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 3.요청내용을 전송 및 응답 처리
        BufferedReader br = null;
        try {
            // conn.getResponseCode() 서버에서 상태코드를 알려주는 값
            int statusCode = conn.getResponseCode();
            //System.out.println(statusCode);
            if (statusCode >= 200 && statusCode <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            Document doc = parseXML(conn.getInputStream());
            // a. field 태그객체 목록으로 가져온다.
            NodeList descNodes = doc.getElementsByTagName("item");
            // b. Corona19Data List객체 생성

            // c. 각 item 태그의 자식태그에서 정보 가져오기
            for (int i = 0; i < descNodes.getLength(); i++) {
                // item
                Node item = descNodes.item(i);
                FlightInfo flightInfo = new FlightInfo();
                // item 자식태그에 순차적으로 접근
                for (Node node = item.getFirstChild(); node != null; node = node.getNextSibling()) {
                    //System.out.println(node.getNodeName() + " : " + node.getTextContent());
                    switch (node.getNodeName()) {
                        case "airline":
                            flightInfo.setAirline(node.getTextContent());
                            break;
                        case "airport":
                            flightInfo.setAirport(node.getTextContent());
                            break;
                        case "airportCode":
                            flightInfo.setAirportCode(node.getTextContent());
                            break;
                        case "carousel":
                            flightInfo.setCarousel(node.getTextContent());
                            break;
                        case "estimatedDateTime":
                            flightInfo.setEstimatedDateTime(node.getTextContent());
                            break;
                        case "exitnumber":
                            flightInfo.setExitnumber(node.getTextContent());
                            break;
                        case "flightId":
                            flightInfo.setFlightId(node.getTextContent());
                            break;
                        case "gatenumber":
                            flightInfo.setGatenumber(node.getTextContent());
                            break;
                        case "remark":
                            flightInfo.setRemark(node.getTextContent());
                            break;
                        case "scheduleDatetime":
                            flightInfo.setScheduleDatetime(node.getTextContent());
                            break;
                        case "terminalid":
                            flightInfo.setTerminalid(node.getTextContent());
                            break;
                    }
                }
                // d. List객체에 추가
                list.add(flightInfo);
            }
            for (FlightInfo d : list) {
                System.out.println(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    // xml 태그를 Document 객체로 변환
    public static Document parseXML(InputStream inputStream) { // Document >> org.dom 으로 임포트
        DocumentBuilderFactory objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try {
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
            doc = objDocumentBuilder.parse(inputStream);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) { // Simple API for XML e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    } // end of parseXML

    // 출발편 연결
    public static ArrayList<FlightInfo> departConnection() {
        ArrayList<FlightInfo> list = new ArrayList<>();
        String filePath = "src/key.properties";
        // 1. 요청 url 생성
        StringBuilder urlBuilder = new StringBuilder(
                "http://apis.data.go.kr/B551177/StatusOfPassengerWorldWeatherInfo/getPassengerDeparturesWorldWeather");
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(filePath));
            String key = properties.getProperty("dkey");

            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
                    + key);
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
                    + URLEncoder.encode("100", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
                    + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("from_time", "UTF-8") + "="
                    + URLEncoder.encode("0000", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("to_time", "UTF-8") + "="
                    + URLEncoder.encode("2400", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("lang", "UTF-8") + "="
                    + URLEncoder.encode("E", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "="
                    + URLEncoder.encode("xml", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2.서버주소 Connection con
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(urlBuilder.toString()); // 웹서버주소 action
            conn = (HttpURLConnection) url.openConnection(); // 접속요청
            conn.setRequestMethod("GET"); // get방식
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println();
            System.out.println();
            System.out.println("Response code: " + conn.getResponseCode());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 3.요청내용을 전송 및 응답 처리
        BufferedReader br = null;
        try {
            // conn.getResponseCode() 서버에서 상태코드를 알려주는 값
            int statusCode = conn.getResponseCode();
            //System.out.println(statusCode);
            if (statusCode >= 200 && statusCode <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            Document doc = parseXML(conn.getInputStream());
            // a. field 태그객체 목록으로 가져온다.
            NodeList descNodes = doc.getElementsByTagName("item");
            // b. Corona19Data List객체 생성

            // c. 각 item 태그의 자식태그에서 정보 가져오기
            for (int i = 0; i < descNodes.getLength(); i++) {
                // item
                Node item = descNodes.item(i);
                FlightInfo flightInfo = new FlightInfo();
                // item 자식태그에 순차적으로 접근
                for (Node node = item.getFirstChild(); node != null; node = node.getNextSibling()) {
                    //System.out.println(node.getNodeName() + " : " + node.getTextContent());
                    switch (node.getNodeName()) {
                        case "airline":
                            flightInfo.setAirline(node.getTextContent());
                            break;
                        case "airport":
                            flightInfo.setAirport(node.getTextContent());
                            break;
                        case "airportCode":
                            flightInfo.setAirportCode(node.getTextContent());
                            break;
                        case "carousel":
                            flightInfo.setCarousel(node.getTextContent());
                            break;
                        case "estimatedDateTime":
                            flightInfo.setEstimatedDateTime(node.getTextContent());
                            break;
                        case "exitnumber":
                            flightInfo.setExitnumber(node.getTextContent());
                            break;
                        case "flightId":
                            flightInfo.setFlightId(node.getTextContent());
                            break;
                        case "gatenumber":
                            flightInfo.setGatenumber(node.getTextContent());
                            break;
                        case "remark":
                            flightInfo.setRemark(node.getTextContent());
                            break;
                        case "scheduleDatetime":
                            flightInfo.setScheduleDatetime(node.getTextContent());
                            break;
                        case "terminalid":
                            flightInfo.setTerminalid(node.getTextContent());
                            break;
                    }
                }
                // d. List객체에 추가
                list.add(flightInfo);
            }
            for (FlightInfo d : list) {
                System.out.println(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
