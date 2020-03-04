package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;

public class ElderlyPeople extends Person {

    private String justification;

    public ElderlyPeople(String dni, String name, String secondName, String phone, Date dateOfBirith, String postaddress, String email, String username, String passwd,String justification) {
        super(dni, name, secondName, phone, dateOfBirith, postaddress, email, username, passwd);
        this.justification=justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getJustification() {
        return justification;
    }
}
