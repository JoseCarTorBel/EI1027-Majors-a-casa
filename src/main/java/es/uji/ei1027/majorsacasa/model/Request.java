package es.uji.ei1027.majorsacasa.model;

import java.time.LocalDate;

public class Request {
    private char state;
    private ServiceType service;
    private LocalDate initialDate;
    private LocalDate approvedDate;
    private boolean rejected;
    private LocalDate endDate;
    private String dniElderlyPeople;


    public Request(){}

    public Request(char state, ServiceType service, LocalDate date, LocalDate aprovedDate,boolean rejected,LocalDate enddate,String dniElderlyPeople) {
        this.state = state;
        this.service = service;
        this.initialDate = date;
        this.approvedDate = aprovedDate;
        this.rejected=rejected;
        this.endDate =enddate;
        this.dniElderlyPeople=dniElderlyPeople;
    }

    public char getState() {
        return state;
    }

    public String getService() {
        return service.toString();
    } //todo COMO GESTIONAR ESTO

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getAprovedDate() {
        return approvedDate;
    }

    public void setState(char state) {
        this.state = state;
    }

    public void setService(ServiceType service) {
        this.service = service;
    }

    public LocalDate getApprovedDate() { return approvedDate; }

    public void setApprovedDate(LocalDate approvedDate) { this.approvedDate = approvedDate; }

    public boolean isRejected() { return rejected; }

    public void setRejected(boolean rejected) { this.rejected = rejected; }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getDniElderlyPeople() { return this.dniElderlyPeople; }

    public void setDniElderlyPeople(String dniElderlyPeople) { this.dniElderlyPeople = dniElderlyPeople; }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public void setAprovedDate(LocalDate aprovedDate) {
        this.approvedDate = aprovedDate;
    }


}
