package es.uji.ei1027.majorsacasa.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Disponibility {
    private Integer dayOfWeek;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate initialTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate finalTime;
    private boolean open;

    public Disponibility(){}


    public Disponibility(Integer dayOfWeek, LocalDate initialTime, LocalDate finalTime, boolean open) {
        this.dayOfWeek = dayOfWeek;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.open=open;
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
