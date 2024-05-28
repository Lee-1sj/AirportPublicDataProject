package Model;

public class FlightInfo {
    String airline; // 항공사
    String airport; // 공항
    String airportCode; // 공항코드
    String carousel; // 수하물수취대
    String estimatedDateTime; // 변경시간
    String exitnumber; // 출구
    String flightId; // 편명
    String gatenumber; // 게이트번호
    String remark; // 현황(출발,도착,결항,지연...etc)
    String scheduleDatetime; // 예정시간
    String terminalid; // 터미널

    public FlightInfo() {
    }

    public FlightInfo(String airline, String airport, String airportCode, String carousel, String estimatedDateTime,
            String exitnumber, String flightId, String gatenumber, String remark, String scheduleDatetime,
            String terminalid) {
        this.airline = airline;
        this.airport = airport;
        this.airportCode = airportCode;
        this.carousel = carousel;
        this.estimatedDateTime = estimatedDateTime;
        this.exitnumber = exitnumber;
        this.flightId = flightId;
        this.gatenumber = gatenumber;
        this.remark = remark;
        this.scheduleDatetime = scheduleDatetime;
        this.terminalid = terminalid;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCarousel() {
        return carousel;
    }

    public void setCarousel(String carousel) {
        this.carousel = carousel;
    }

    public String getEstimatedDateTime() {
        return estimatedDateTime;
    }

    public void setEstimatedDateTime(String estimatedDateTime) {
        this.estimatedDateTime = estimatedDateTime;
    }

    public String getExitnumber() {
        return exitnumber;
    }

    public void setExitnumber(String exitnumber) {
        this.exitnumber = exitnumber;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getGatenumber() {
        return gatenumber;
    }

    public void setGatenumber(String gatenumber) {
        this.gatenumber = gatenumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getScheduleDatetime() {
        return scheduleDatetime;
    }

    public void setScheduleDatetime(String scheduleDatetime) {
        this.scheduleDatetime = scheduleDatetime;
    }

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(
                "--------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-24s %-22s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
                "airline", "airport", "airportCode", "carousel", "estimatedDateTime", "exitNumber", "flightId",
                "gatenumber", "remark", "scheduleDatetime", "terminalId"));
        sb.append(
                "--------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        sb.append(String.format("%-24s %-22s %-11s %-11s %-16s %-10s %-10s %-10s %-10s %-16s %-10s\n",
                airline, airport, airportCode, carousel, estimatedDateTime, exitnumber, flightId, gatenumber, remark,
                scheduleDatetime, terminalid));
        sb.append(
                "--------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }

}
