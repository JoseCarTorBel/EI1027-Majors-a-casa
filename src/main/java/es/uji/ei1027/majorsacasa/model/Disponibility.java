package es.uji.ei1027.majorsacasa.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Disponibility {
    private String dniVolunteer;
    private String dniElderlyPeople;
    private Integer dayOfWeek;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate initialTime;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate finalTime;
    private Character state;

    public Disponibility(){}


    public Disponibility(String dniVolunteer, String dniElderlyPeople, Integer dayOfWeek, LocalDate initialTime, LocalDate finalTime, Character state) {
        this.dniVolunteer = dniVolunteer;
        this.dniElderlyPeople = dniElderlyPeople;
        this.dayOfWeek = dayOfWeek;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.state=state;
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

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
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

    @Override
    public String toString() {
        return "Disponibility{" +
                "dniVolunteer='" + dniVolunteer + '\'' +
                ", dniElderlyPeople='" + dniElderlyPeople + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", initialTime=" + initialTime +
                ", finalTime=" + finalTime +
                ", state=" + state +
                '}';
    }
}
