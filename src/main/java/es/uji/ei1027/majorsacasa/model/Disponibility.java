package es.uji.ei1027.majorsacasa.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Disponibility {
    private String dniVolunteer;
    private String dniElderlyPeople;
    private Integer dayOfWeek;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate finalTime;
    private boolean open;

    public Disponibility(){}


    public Disponibility(String dniVolunteer, String dniElderlyPeople, Integer dayOfWeek, LocalDate initialTime, LocalDate finalTime, boolean open) {
        this.dniVolunteer = dniVolunteer;
        this.dniElderlyPeople = dniElderlyPeople;
        this.dayOfWeek = dayOfWeek;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.open=open;
    }

    public String getDniVolunteer() {
        return dniVolunteer;
    }

    public void setDniVolunteer(String dniVolunteer) {
        this.dniVolunteer = dniVolunteer;
    }

    public String getDniElderlyPeople() {
        return dniElderlyPeople;
    }

    public void setDniElderlyPeople(String dniElderlyPeople) {
        this.dniElderlyPeople = dniElderlyPeople;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalDate getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(LocalDate initialTime) {
        this.initialTime = initialTime;
    }

    public LocalDate getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(LocalDate finalTime) {
        this.finalTime = finalTime;
    }
}
