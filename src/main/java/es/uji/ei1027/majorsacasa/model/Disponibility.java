package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;

public class Disponibility {
    private Date dayOfWeek;
    private Date initialTime;
    private Date finalTime;

    public Disponibility(){}

    public Disponibility(Date dayOfWeek, Date initialTime, Date finalTime) {
        this.dayOfWeek = dayOfWeek;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
    }

    public Date getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Date dayOfWeek) {
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
