package es.uji.ei1027.majorsacasa.model;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class Contract {

    private ServiceType service;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private float price;

    public Contract() {}

    public Contract(ServiceType service, LocalDate initialDate, LocalDate finalDate, float price) {
        this.service = service;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.price = price;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
