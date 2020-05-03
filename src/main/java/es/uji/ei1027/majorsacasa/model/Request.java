package es.uji.ei1027.majorsacasa.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;

public class Request {
    private String codRequest;
    private Character state;
    private ServiceType service;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate initialDate;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate approvedDate;
    private boolean rejected;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private Time serviceHour;
    private float price;
    private String dniElderlyPeople;
    private String codContract;


    public Request(){}

    public Request(String codRequest, Character state, ServiceType service, LocalDate initialDate, LocalDate approvedDate, boolean rejected, LocalDate endDate, Time serviceHour, float price, String dniElderlyPeople, String codContract) {
        this.codRequest = codRequest;
        this.state = state;
        this.service = service;
        this.initialDate = initialDate;
        this.approvedDate = approvedDate;
        this.rejected = rejected;
        this.endDate = endDate;
        this.serviceHour = serviceHour;
        this.price = price;
        this.dniElderlyPeople = dniElderlyPeople;
        this.codContract = codContract;
    }

    public Time getServiceHour() {
        return serviceHour;
    }

    public void setServiceHour(Time serviceHour) {
        this.serviceHour = serviceHour;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCodRequest() {
        return codRequest;
    }

    public void setCodRequest(String codRequest) {
        this.codRequest = codRequest;
    }

    public String getCodContract() {
        return codContract;
    }

    public void setCodContract(String codContract) {
        this.codContract = codContract;
    }

    public Character getState() {
        return state;
    }

    public int getService() {
        return service.getPosition();
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getAprovedDate() {
        return approvedDate;
    }

    public void setState(Character state) {
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
