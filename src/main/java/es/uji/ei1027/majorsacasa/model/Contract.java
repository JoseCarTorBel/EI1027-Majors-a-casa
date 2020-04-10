package es.uji.ei1027.majorsacasa.model;

import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Contract {
    private String cifcompany;
    private ServiceType service;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate initialDate;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate finalDate;
    private Float price;

    public Contract() {}

    public Contract(String cifcompany, ServiceType service, LocalDate initialDate, LocalDate finalDate, Float price) {
        this.cifcompany = cifcompany;
        this.service = service;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.price = price;
    }
    public String getCifcompany() {
        return cifcompany;
    }

    public void setCifcompany(String cifcompany) {
        this.cifcompany = cifcompany;
    }

    public ServiceType getService() {
        return service;
    }

    public void setService(ServiceType service) {
        this.service = service;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
