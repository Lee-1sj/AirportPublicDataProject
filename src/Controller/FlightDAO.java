package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.FlightInfo;

public class FlightDAO {
    // 데이터 삭제
    public void deleteInitialInfo() {
        String sql = "{CALL delete_flight_info}";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    } // end of deleteFlightInfo()

    // 데이터 수
    public int getCountFlightInfo() {
        int count = 0;
        String sql = "select count(*) as cnt from flight";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt("cnt");
            }

        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
            }
        }
        return count;

    } // end of getCountFlightInfo()

    // 항공편 정보 저장
    public void saveFlightInfo(ArrayList<FlightInfo> flightInfoList) {
        Connection con = null;
        PreparedStatement pstmt = null;
        boolean success = true;

        try {
            con = DBUtil.getConnection();
            for (FlightInfo data : flightInfoList) {
                String sql = "{call insert_flight_info(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, data.getAirline());
                pstmt.setString(2, data.getAirport());
                pstmt.setString(3, data.getAirportCode());
                pstmt.setString(4, data.getCarousel());
                pstmt.setString(5, data.getEstimatedDateTime());
                pstmt.setString(6, data.getExitnumber());
                pstmt.setString(7, data.getFlightId());
                pstmt.setString(8, data.getGatenumber());
                pstmt.setString(9, data.getRemark());
                pstmt.setString(10, data.getScheduleDatetime());
                pstmt.setString(11, data.getTerminalid());

                int value = pstmt.executeUpdate();
                if (value != 1) {
                    success = false;
                    break;
                }
            } // end of for

            if (success) {
                System.out.println("All Flight Info Insert Success");
            } else {
                System.out.println("Flight Info Insert Failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    } // end of saveFlightInfo()

    // 해당 항공편명이 존재하는지 체크하고, 존재하면 정보를 불러옴
    public void getFlightInfo(String flightId) {
        String sql = "select * from flight where flightId = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FlightInfo flightInfo = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, flightId);
            rs = pstmt.executeQuery();

            
           
            if(!rs.next()){
                System.out.println("The flight ID does not exist.");
            } else {
                do{
                    flightInfo = new FlightInfo();
                    flightInfo.setAirline(rs.getString("airline"));
                    flightInfo.setAirport(rs.getString("airport"));
                    flightInfo.setAirportCode(rs.getString("airportCode"));
                    flightInfo.setCarousel(rs.getString("carousel"));
                    flightInfo.setEstimatedDateTime(rs.getString("estimatedDateTime"));
                    flightInfo.setExitnumber(rs.getString("exitnumber"));
                    flightInfo.setFlightId(rs.getString("flightId"));
                    flightInfo.setGatenumber(rs.getString("gatenumber"));
                    flightInfo.setRemark(rs.getString("remark"));
                    flightInfo.setScheduleDatetime(rs.getString("scheduleDatetime"));
                    flightInfo.setTerminalid(rs.getString("terminalid"));

                    System.out.println(String.format("%-24s %-22s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s",
            "airline", "airport", "airportCode", "carousel", "estimatedDateTime", "exitNumber", "flightId",
            "gatenumber", "remark", "scheduleDatetime", "terminalId"));

                    System.out.println(String.format("%-24s %-22s %-11s %-11s %-16s %-10s %-10s %-10s %-10s %-16s %-10s",
                    flightInfo.getAirline(), flightInfo.getAirport(), flightInfo.getAirportCode(), flightInfo.getCarousel(), 
                    flightInfo.getEstimatedDateTime(), flightInfo.getExitnumber(), flightInfo.getFlightId(), 
                    flightInfo.getGatenumber(), flightInfo.getRemark(), flightInfo.getScheduleDatetime(), flightInfo.getTerminalid()));
                } while(rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }

    } // end of flightIdOverlap()

    public void eliminateFlightInfo() {
        int count = getCountFlightInfo();
        if (count == 0) {
            System.out.println("There is no air info history.");
            return;
        }
        String sql = "{CALL delete_flight_info}";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
           
            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println("Flight Info delete failed.");
            } else {    
                System.out.println("Flight Info delete success.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    } // end of deleteFlightInfo()
}