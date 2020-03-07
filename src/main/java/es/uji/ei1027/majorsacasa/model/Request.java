package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;

public class Request {
    private char state;
    private ServiceType service;
    private Date date;
    private Date approvedDate;
    private boolean rejected;
    private Date enddate;
    private String dniElderlyPeople;


    public Request(){}

    public Request(char state, ServiceType service, Date date, Date aprovedDate,boolean rejected,Date enddate,String dniElderlyPeople) {
        this.state = state;
        this.service = service;
        this.date = date;
        this.approvedDate = aprovedDate;
        this.rejected=rejected;
        this.enddate=enddate;
        this.dniElderlyPeople=dniElderlyPeople;
    }

    public char getState() {
        return state;
    }

    public String getService() {
        return service.toString();
    } //todo COMO GESTIONAR ESTO

    public Date getDate() {
        return date;
    }

    public Date getAprovedDate() {
        return approvedDate;
    }

    public void setState(char state) {
        this.state = state;
    }

    public void setService(ServiceType service) {
        this.service = service;
    }

    public Date getApprovedDate() { return approvedDate; }

    public void setApprovedDate(Date approvedDate) { this.approvedDate = approvedDate; }

    public boolean isRejected() { return rejected; }

    public void setRejected(boolean rejected) { this.rejected = rejected; }

    public Date getEnddate() { return enddate; }

    public void setEnddate(Date enddate) { this.enddate = enddate; }

    public String getDniElderlyPeople() { return this.dniElderlyPeople; }

    public void setDniElderlyPeople(String dniElderlyPeople) { this.dniElderlyPeople = dniElderlyPeople; }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAprovedDate(Date aprovedDate) {
        this.approvedDate = aprovedDate;
    }


}
