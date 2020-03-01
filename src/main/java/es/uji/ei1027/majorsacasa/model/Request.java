package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;

public class Request {
    private char state;
    private ServiceType service;
    private Date date;
    private Date aprovedDate;

    public Request(){}

    public Request(char state, ServiceType service, Date date, Date aprovedDate) {
        this.state = state;
        this.service = service;
        this.date = date;
        this.aprovedDate = aprovedDate;
    }

    public char getState() {
        return state;
    }

    public ServiceType getService() {
        return service;
    }

    public Date getDate() {
        return date;
    }

    public Date getAprovedDate() {
        return aprovedDate;
    }

    public void setState(char state) {
        this.state = state;
    }

    public void setService(ServiceType service) {
        this.service = service;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAprovedDate(Date aprovedDate) {
        this.aprovedDate = aprovedDate;
    }

}
