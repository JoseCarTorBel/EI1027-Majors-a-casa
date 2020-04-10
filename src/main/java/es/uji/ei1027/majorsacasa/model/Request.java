package es.uji.ei1027.majorsacasa.model;

import org.springframework.format.annotation.DateTimeFormat;

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
    private String dniElderlyPeople;
    private String cifCompany;


    public Request(){}

    public Request(String codRequest, Character state, ServiceType service, LocalDate initialDate, LocalDate approvedDate, boolean rejected, LocalDate endDate, String dniElderlyPeople, String cifCompany) {
        this.codRequest = codRequest;
        this.state = state;
        this.service = service;
        this.initialDate = initialDate;
        this.approvedDate = approvedDate;
        this.rejected = rejected;
        this.endDate = endDate;
        this.dniElderlyPeople = dniElderlyPeople;
        this.cifCompany = cifCompany;
    }

    public String getCodRequest() {
        return codRequest;
    }

    public void setCodRequest(String codRequest) {
        this.codRequest = codRequest;
    }

    public String getCifCompany() {
        return cifCompany;
    }

    public void setCifCompany(String cifCompany) {
        this.cifCompany = cifCompany;
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
