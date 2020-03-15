package es.uji.ei1027.majorsacasa.model;

import java.time.LocalDate;

public class Disponibility {
    private int dayOfWeek;
    private LocalDate initialTime;
    private LocalDate finalTime;
    private boolean open;

    public Disponibility(){}


    public Disponibility(int dayOfWeek, LocalDate initialTime, LocalDate finalTime, boolean open) {
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

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
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
