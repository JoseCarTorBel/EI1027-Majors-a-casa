package es.uji.ei1027.majorsacasa.model;

import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;

public class Contract {

    private String codContract;

    private String cifcompany;
    private ServiceType service;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate initialDate;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate finalDate;
    private Float price;

    private String daysOfWeek;
    private Time hour_initial,  hour_final;

    public Contract() { }

    public Contract(String codContract,String cifcompany, ServiceType service, LocalDate initialDate, LocalDate finalDate,
                    Float price, String daysOfWeek, Time hour_initial, Time hour_final) {
        this.codContract = codContract;
        this.cifcompany = cifcompany;
        this.service = service;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.price = price;
        this.daysOfWeek = daysOfWeek;
        this.hour_final=hour_final;
        this.hour_initial=hour_initial;
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

    public String getCodContract(){return codContract;}

    public void setCodContract(String codContract){ this.codContract=codContract; }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public Time getHour_initial() {
        return hour_initial;
    }

    public void setHour_initial(Time hour_initial) {
        this.hour_initial = hour_initial;
    }

    public Time getHour_final() {
        return hour_final;
    }

    public void setHour_final(Time hour_final) {
        this.hour_final = hour_final;
    }

}
