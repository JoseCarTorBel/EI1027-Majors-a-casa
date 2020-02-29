package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;

public class ElderlyPeople extends Person {

    private String justification;

    public ElderlyPeople(String dni, String name, String secondName, String phone, Date dateOfBirith, String postaddress, char state, String mail, String username, String password,String justification) {
        super(dni, name, secondName, phone, dateOfBirith, postaddress, state, mail, username, password);
        this.justification=justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getJustification() {
        return justification;
    }
}
