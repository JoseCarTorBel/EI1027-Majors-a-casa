package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;

public class Contract {

    private ServiceType service;
    private Date initialDate;
    private Date finalDate;
    private float price;

    public Contract() {
    }

    public Contract(ServiceType service, Date initialDate, Date finalDate, float price) {
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

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
