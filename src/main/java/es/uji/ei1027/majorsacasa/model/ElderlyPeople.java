package es.uji.ei1027.majorsacasa.model;

import java.time.LocalDate;
import java.util.List;

public class ElderlyPeople extends Person {

    private Character state;
    private String justification;
    private String dniSocialWorker;

    public ElderlyPeople() {

    }

    public ElderlyPeople(String dni, String name, String secondName, String phone, LocalDate dateOfBirth, String postAddress, String email, String username, String passwd, Character state, String justification, String dniSocialWorker) {
        super(dni, name, secondName, phone, dateOfBirth, postAddress, email, username, passwd);
        this.state = state;
        this.justification = justification;
        this.dniSocialWorker = dniSocialWorker;
    }

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }

    public String getDniSocialWorker() {
        return dniSocialWorker;
    }

    public void setDniSocialWorker(String dniSocialWorker) {
        this.dniSocialWorker = dniSocialWorker;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getJustification() {
        return justification;
    }
}
