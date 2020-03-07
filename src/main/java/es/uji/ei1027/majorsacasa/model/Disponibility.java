package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;

public class Disponibility {
    private int dayOfWeek;
    private Date initialTime;
    private Date finalTime;
    private boolean open;

    public Disponibility(){}


    public Disponibility(int dayOfWeek, Date initialTime, Date finalTime, boolean open) {
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

    public Date getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(Date initialTime) {
        this.initialTime = initialTime;
    }

    public Date getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }
}
